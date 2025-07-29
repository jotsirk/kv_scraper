package com.kv_scraper.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "property_ticks")
data class PropertyTick(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,

    @NotBlank
    @Column
    val origin: PropertyOriginType,

    @NotBlank
    @Column(length = 50)
    val propertyKey: String,

    @NotBlank
    @Column(length = 250)
    val url: String,
)