package com.kv_scraper.model

import jakarta.persistence.CascadeType.ALL
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import jakarta.validation.constraints.NotBlank

@Entity
@Table(
  name = "property_ticks",
  uniqueConstraints = [UniqueConstraint(columnNames = ["origin", "property_key"])],
)
data class PropertyTick(
  @Id
  @GeneratedValue(strategy = IDENTITY)
  val id: Long? = null,
  @NotBlank
  @Enumerated(STRING)
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
  @NotBlank
  @Column
  val isFinished: Boolean = false,
)
