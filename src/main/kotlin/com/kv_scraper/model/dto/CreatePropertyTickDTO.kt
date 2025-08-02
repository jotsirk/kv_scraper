package com.kv_scraper.model.dto

import com.kv_scraper.model.PropertyOriginType
import com.kv_scraper.model.PropertyTick

data class CreatePropertyTickDTO(
  val origin: PropertyOriginType,
  val propertyKey: String,
  val imageUrl: String,
) {

  fun toModel() = PropertyTick(
    origin = origin,
    propertyKey = propertyKey,
    url = "${origin.url}/${propertyKey}",
    imageUrl = imageUrl,
  )
}
