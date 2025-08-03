package com.kv_scraper.repository

import com.kv_scraper.model.PropertyTick
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface PropertyTickRepository : JpaRepository<PropertyTick, Long> {

  @Modifying
  @Transactional
  @Query("UPDATE PropertyTick p SET p.isArchived = true WHERE p.id = :id")
  fun setArchived(
    @Param("id") id: Long,
  ): Int

  fun findAllByIsArchivedIsFalse(): List<PropertyTick>
}
