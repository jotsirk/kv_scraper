package com.kv_scraper.resource

import com.kv_scraper.model.dto.CreatePropertyTickDTO
import com.kv_scraper.model.dto.PropertyTickDTO
import com.kv_scraper.service.PropertyTickService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/property‐ticks")
@Validated
class PropertyTickResource {

  @Autowired
  private lateinit var propertyTickService: PropertyTickService

  @GetMapping
  fun findAll(): ResponseEntity<List<PropertyTickDTO>> {
    val propertyTicksDTO = propertyTickService.findAll().map { it.toDTO() }

    return ResponseEntity(propertyTicksDTO, OK)
  }

  @PostMapping
  fun create(
    @Valid @RequestBody dto: CreatePropertyTickDTO,
  ): ResponseEntity<Void> {
    propertyTickService.save(dto.toModel())

    return ResponseEntity
      .status(CREATED)
      .build()
  }
}
