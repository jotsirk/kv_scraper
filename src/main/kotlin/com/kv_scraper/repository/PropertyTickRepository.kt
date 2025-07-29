package com.kv_scraper.repository

import com.kv_scraper.model.PropertyTick
import org.springframework.data.jpa.repository.JpaRepository

interface PropertyTickRepository : JpaRepository<PropertyTick, Long>

