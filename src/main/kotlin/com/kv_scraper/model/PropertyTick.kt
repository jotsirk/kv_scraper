package com.kv_scraper.model

import jakarta.persistence.CascadeType.ALL
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.SEQUENCE
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "property_ticks")
data class PropertyTick(
  @Id
  @GeneratedValue(strategy = SEQUENCE)
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
  @OneToMany(mappedBy = "tick", cascade = [ALL], orphanRemoval = true)
  val logs: List<PropertyLog> = mutableListOf(),
)
