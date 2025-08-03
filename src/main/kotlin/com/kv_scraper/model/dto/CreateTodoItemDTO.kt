package com.kv_scraper.model.dto

import com.kv_scraper.model.TodoItem

data class CreateTodoItemDto(
  val task: String
) {

  fun toModel() = TodoItem(
    task = task,
  )
}
