package com.kv_scraper.service

import com.kv_scraper.repository.PropertyTickRepository
import java.util.concurrent.ThreadLocalRandom
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class PropertyScraperJob {

  @Autowired
  private lateinit var log: Logger

  @Autowired
  private lateinit var propertyTickRepository: PropertyTickRepository

  @Autowired
  private lateinit var scraperService: KvScraperService

  @Scheduled(cron = "0 30 10 * * *")
  fun runDailyScrape() {
    log.info("Starting daily scrape")

    val propertyTicks = propertyTickRepository.findAll()
    val driver = scraperService.createDriver()

    try {
      propertyTicks.forEach { tick ->
        log.info("starting scrape for : ${tick.propertyKey}")

        try {
          scraperService.scrapeAndSavePropertyLog(tick, driver)
        } catch (e: Exception) {
          log.error("Exception while creating property tick", e)
        }

        val waitMillis = ThreadLocalRandom.current().nextLong(3 * 60_000, 5 * 60_000)
        Thread.sleep(waitMillis)
      }
    } finally {
      log.info("Finished scraping")
      driver.quit()
    }
  }
}
