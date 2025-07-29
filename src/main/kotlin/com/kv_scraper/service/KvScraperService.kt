package com.kv_scraper.service

import com.kv_scraper.model.PropertyTick
import com.kv_scraper.model.StatusType.SUCCESS
import com.kv_scraper.model.dto.KvPropertyDataDTO
import com.kv_scraper.repository.PropertyLogRepository
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class KvScraperService {
  @Autowired
  private lateinit var propertyLogRepository: PropertyLogRepository

  private val httpClient = OkHttpClient()

  fun scrapeAndSavePropertyLog(propertyTick: PropertyTick) {
    val document = scrapeDocumentWithOkHttp(propertyTick.url) ?: return
    val kvPropertyDataDTO = parseDocument(document)

    val propertyLog = kvPropertyDataDTO.toModel(propertyTick, SUCCESS)
    propertyLogRepository.save(propertyLog)
  }

  private fun scrapeDocumentWithOkHttp(url: String): Document? {
    val request =
      Request.Builder().url(url)
        .header(
          "User-Agent",
          "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/135.0.0.0 Safari/537.36",
        )
        .header("Accept", "*/*").header(
          "Cookie",
          "__cf_bm=Wbx6dafFa2yfacQ4G.8jxIoIWjKUEMO0C9dN12b83Ro-1753796970-1.0.1.1-I3appQ0iE5l_zRWxMxK0v1Up8_cra1fQAXSXa6fEC3GkOFcp6ZYi_ynKyqy4YQs6klDDEblj.fK8qYVUeeECv0JQUHRXn7SLD7OTDCzPM18",
        )
        .header("User-Agent", "PostmanRuntime/7.29.4")
        .header("Connection", "keep-alive")
        .build()

    return try {
      httpClient.newCall(request).execute().use { response ->
        if (!response.isSuccessful) {
          println("HTTP error scraping $url: ${response.code}")
          return null
        }
        val body = response.body?.string()
        if (body != null) Jsoup.parse(body) else null
      }
    } catch (e: Exception) {
      println("Error scraping page $url: ${e.message}")
      null
    }
  }

  private fun parseDocument(document: Document): KvPropertyDataDTO {
    val price = document.selectFirst("div.label.campaign")?.attr("data-price")
    val thAction = document.selectFirst(".meta-table .table-lined th")?.text()
    val isReserved = thAction?.contains("broneeritud", true) == true

    return KvPropertyDataDTO(
      price = price?.toDouble() ?: 0.0,
      isReserved = isReserved,
    )
  }

  companion object {
  }
}
