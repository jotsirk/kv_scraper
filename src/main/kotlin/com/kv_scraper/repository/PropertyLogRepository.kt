package com.kv_scraper.repository

import com.kv_scraper.model.PropertyLog
import org.springframework.data.jpa.repository.JpaRepository

interface PropertyLogRepository : JpaRepository<PropertyLog, Long>
