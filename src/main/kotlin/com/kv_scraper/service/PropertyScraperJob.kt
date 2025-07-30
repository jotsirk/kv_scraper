package com.kv_scraper.service

import com.kv_scraper.repository.PropertyTickRepository
import java.util.concurrent.ThreadLocalRandom
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class PropertyScraperJob {

  @Autowired
  private lateinit var propertyTickRepository: PropertyTickRepository

  @Autowired
  private lateinit var scraperService: KvScraperService

  @Scheduled(cron = "0 30 12 * * *")
  fun runDailyScrape() {
    val propertyTicks = propertyTickRepository.findAll()
    propertyTicks.forEach { tick ->
      try {
        scraperService.scrapeAndSavePropertyLog(tick)
      } catch (e: Exception) {
      }
      val waitMillis = ThreadLocalRandom.current().nextLong(3 * 60_000, 5 * 60_000)
      Thread.sleep(waitMillis)
    }
  }
}
