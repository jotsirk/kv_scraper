package com.kv_scraper.resource

import com.kv_scraper.model.TodoItem
import com.kv_scraper.service.TodoItemService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/todo-items")
class TodoItemResource(
  private val todoItemService: TodoItemService
) {

  @GetMapping
  fun getAll(): ResponseEntity<List<TodoItem>> {
    val items = todoItemService.findAll()
    return ResponseEntity.ok(items)
  }

  @GetMapping("/unfinished")
  fun getUnfinished(): ResponseEntity<List<TodoItem>> {
    val items = todoItemService.getAllUnfinished()
    return ResponseEntity.ok(items)
  }

  @GetMapping("/finished")
  fun getFinished(): ResponseEntity<List<TodoItem>> {
    val items = todoItemService.getAllFinished()
    return ResponseEntity.ok(items)
  }
}
