package com.kv_scraper.model.dto

import com.kv_scraper.model.PropertyOriginType

data class PropertyTickDTO(
  val id: Long,
  val origin: PropertyOriginType,
  val propertyKey: String,
  val url: String,
  val logs: List<PropertyLogDTO>,
  val isFinished: Boolean,
  val isReserved: Boolean,
  val imageUrl: String,
)
