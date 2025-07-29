package com.kv_scraper.service

import com.kv_scraper.repository.PropertyTickRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class PropertyScraperJob {

  @Autowired
  private lateinit var propertyTickRepository: PropertyTickRepository

  @Autowired
  private lateinit var scraperService: KvScraperService

  @Scheduled(cron = "0 0 12 * * *")
  fun runDailyScrape() {
    val propertyTicks = propertyTickRepository.findAll()

    propertyTicks.forEach {
      try {
        scraperService.scrapeAndSavePropertyLog(it)
      } catch (e: Exception) {

      }
    }
  }
}
