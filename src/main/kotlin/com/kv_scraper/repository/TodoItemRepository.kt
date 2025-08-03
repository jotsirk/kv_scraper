package com.kv_scraper.repository

import com.kv_scraper.model.TodoItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface TodoItemRepository : JpaRepository<TodoItem, Long> {

  fun findAllByIsFinishedIsTrue(): List<TodoItem>

  fun findAllByIsFinishedIsFalse(): List<TodoItem>

  @Modifying
  @Transactional
  @Query("UPDATE TodoItem t SET t.isFinished = true  WHERE t.id = :id")
  fun setFinished(
    @Param("id") id: Long,
  ): Int

  @Modifying
  @Transactional
  @Query("UPDATE TodoItem t SET t.isFinished = false WHERE t.id = :id")
  fun setUnfinished(
    @Param("id") id: Long,
  ): Int
}
