package com.kv_scraper.model.dto

import java.time.LocalDateTime

data class PropertyLogDTO(
  val id: Long,
  val price: Double,
  val createdAt: LocalDateTime,
)
