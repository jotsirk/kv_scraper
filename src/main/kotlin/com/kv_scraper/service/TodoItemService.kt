package com.kv_scraper.service

import com.kv_scraper.model.TodoItem
import com.kv_scraper.repository.TodoItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TodoItemService {

  @Autowired
  private lateinit var todoItemRepository: TodoItemRepository

  fun add(todoItem: TodoItem): TodoItem {
    return todoItemRepository.save(todoItem)
  }

  fun findAll(): List<TodoItem> = todoItemRepository.findAll()

  fun getAllUnfinished(): List<TodoItem> = todoItemRepository.findAllByIsFinishedIsFalse()

  fun getAllFinished(): List<TodoItem> = todoItemRepository.findAllByIsFinishedIsTrue()

  fun finish(id: Long) {
    todoItemRepository.setFinished(id)
  }

  fun unfinish(id: Long) {
    todoItemRepository.setUnfinished(id)
  }
}
