package com.kv_scraper.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "todo_item")
data class TodoItem(
  @Id
  @GeneratedValue
  val id: Long? = null,
  @NotBlank
  @Column(length = 500)
  val task: String,
  @NotBlank
  @Column
  val isFinished: Boolean = false,
)
