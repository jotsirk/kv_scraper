package com.kv_scraper.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "property_logs")
data class PropertyLog(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long = -1,
)
