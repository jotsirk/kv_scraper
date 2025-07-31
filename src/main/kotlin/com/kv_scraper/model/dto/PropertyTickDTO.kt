package com.kv_scraper.model.dto

import com.kv_scraper.model.PropertyOriginType
import com.kv_scraper.model.PropertyTick

data class PropertyTickDTO(
  val origin: PropertyOriginType,
  val propertyKey: String,
) {

  fun toModel() = PropertyTick(
    origin = origin,
    propertyKey = propertyKey,
    url = "${origin.url}/${propertyKey}"
  )
}
