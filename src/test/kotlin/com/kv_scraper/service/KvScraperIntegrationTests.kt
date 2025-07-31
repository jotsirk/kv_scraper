package com.kv_scraper.service

import com.kv_scraper.model.PropertyOriginType
import com.kv_scraper.model.PropertyTick
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class KvScraperIntegrationTests {
  @Autowired
  private lateinit var service: KvScraperService

  @Test
  fun runScraperTest() {
    // given
    val propertyTick =
      PropertyTick(
        id = 0,
        origin = PropertyOriginType.KV,
        propertyKey = "3757253",
        url = "https://www.kv.ee/3757253",
      )

    // when
//    service.scrapeAndSavePropertyLog(propertyTick)

    // then
    println("done")
  }
}
