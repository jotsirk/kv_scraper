package com.kv_scraper.service

import com.kv_scraper.model.DocumentResponse
import com.kv_scraper.model.PropertyTick
import com.kv_scraper.model.StatusType.SUCCESS
import com.kv_scraper.model.dto.KvPropertyDataDTO
import com.kv_scraper.repository.PropertyLogRepository
import com.kv_scraper.repository.PropertyTickRepository
import java.net.URL
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.RemoteWebDriver
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class KvScraperService {

  @Autowired
  private lateinit var log: Logger

  @Autowired
  private lateinit var propertyLogRepository: PropertyLogRepository

  @Autowired
  private lateinit var propertyTickRepository: PropertyTickRepository

  fun createDriver(): WebDriver {
    val options = ChromeOptions().apply {
      setAcceptInsecureCerts(true)
      addArguments(
        "--disable-blink-features=AutomationControlled",
        "--start-maximized",
      )
      setExperimentalOption("excludeSwitches", listOf("enable-automation"))
      setExperimentalOption("useAutomationExtension", false)
      setCapability("se:recordVideo", false)
    }

    return RemoteWebDriver(URL(System.getenv("SELENIUM_URL")), options).apply {
      (this as JavascriptExecutor).executeScript(
        "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})",
      )
    }
  }

  fun scrapeAndSavePropertyLog(propertyTick: PropertyTick, driver: WebDriver) {
    val response = scrapeDocument(propertyTick.url, driver)

    when (response.responseCode) {
      200 -> persistProperty(propertyTick, response.document!!)
      404, 500 -> finishPropertyTick(propertyTick)
      403 -> return
      else -> return
    }
  }

  private fun scrapeDocument(url: String, driver: WebDriver): DocumentResponse {
    return try {
      driver.get(url)
      val html = driver.pageSource

      if (html == null) {
        log.info("pagesource is empty : $html")
        return DocumentResponse(null, 500)
      }

      if (html.contains("EI ELA ENAM", true)) {
        DocumentResponse(null, 404)
      } else if (html.contains("Just a moment", true)) {
        DocumentResponse(null, 500)
      } else {
        DocumentResponse(Jsoup.parse(html), 200)
      }
    } catch (e: Exception) {
      log.error("Selenium failed for $url", e)
      DocumentResponse(null, 500)
    }
  }

  private fun persistProperty(propertyTick: PropertyTick, document: Document) {
    val price = document.selectFirst("div.label.campaign")?.attr("data-price")
    val thAction = document.selectFirst(".meta-table .table-lined th")?.text()
    val isReserved = thAction?.contains("broneeritud", true) == true
    val kvPropertyDataDTO = KvPropertyDataDTO(
      price = price?.toDouble() ?: 0.0,
      isReserved = isReserved,
    )

    val propertyLog = kvPropertyDataDTO.toModel(propertyTick, SUCCESS)
    propertyLogRepository.save(propertyLog)
  }

  private fun finishPropertyTick(propertyTick: PropertyTick) {
    val updatedPropertyTick = propertyTick.copy(isFinished = true)
    propertyTickRepository.save(updatedPropertyTick)
  }

  companion object {
  }
}
