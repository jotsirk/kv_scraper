package com.kv_scraper.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime
import org.hibernate.validator.constraints.Length

@Entity
@Table(name = "property_logs")
data class PropertyLog(
  @Id
  @GeneratedValue(strategy = IDENTITY)
  val id: Long = -1,
  @Column
  val price: Double = 0.0,
  @Column
  val isReserved: Boolean = false,
  @Column
  val createdAt: LocalDateTime = LocalDateTime.now(),
  @Column
  val status: StatusType,
  @Column
  @Length(max = 500)
  val description: String? = null,
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "property_tick_id", nullable = false)
  val tick: PropertyTick,
)
