package com.kv_scraper.model.dto

import com.kv_scraper.model.PropertyLog
import com.kv_scraper.model.PropertyTick

data class KvPropertyDataDTO(
  val price: Double,
  val isReserved: Boolean = false,
) {
  fun toModel(
    tick: PropertyTick,
  ): PropertyLog =
    PropertyLog(
      price = price,
      isReserved = isReserved,
      tick = tick,
    )
}
