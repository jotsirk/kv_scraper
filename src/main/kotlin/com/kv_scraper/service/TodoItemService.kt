package com.kv_scraper.service

import com.kv_scraper.model.TodoItem
import com.kv_scraper.repository.TodoItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TodoItemService {

  @Autowired
  private lateinit var todoItemRepository: TodoItemRepository

  fun findAll(): List<TodoItem> = todoItemRepository.findAll()

  fun getAllUnfinished(): List<TodoItem> = todoItemRepository.findAllByIsFinishedIsFalse()

  fun getAllFinished(): List<TodoItem> = todoItemRepository.findAllByIsFinishedIsTrue()
}
