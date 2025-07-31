package com.kv_scraper.service

import com.kv_scraper.model.PropertyTick
import com.kv_scraper.repository.PropertyTickRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PropertyTickService {

  @Autowired
  private lateinit var propertyTickRepository: PropertyTickRepository

  fun findAll(): List<PropertyTick> {
    return propertyTickRepository.findAll()
  }

  fun save(propertyTick: PropertyTick) {
    propertyTickRepository.save(propertyTick)
  }
}
