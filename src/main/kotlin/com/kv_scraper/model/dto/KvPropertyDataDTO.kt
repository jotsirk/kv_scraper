package com.kv_scraper.model.dto

import com.kv_scraper.model.PropertyLog
import com.kv_scraper.model.PropertyTick
import com.kv_scraper.model.StatusType

data class KvPropertyDataDTO(
  val price: Double,
  val isReserved: Boolean = false,
) {
  fun toModel(
    tick: PropertyTick,
    status: StatusType,
    description: String? = null,
  ): PropertyLog =
    PropertyLog(
      price = price,
      isReserved = isReserved,
      status = status,
      description = description,
      tick = tick,
    )
}
