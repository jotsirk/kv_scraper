package com.kv_scraper.repository

import com.kv_scraper.model.TodoItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TodoItemRepository : JpaRepository<TodoItem, Long> {

  fun findAllByIsFinishedIsTrue(): List<TodoItem>

  fun findAllByIsFinishedIsFalse(): List<TodoItem>
}
