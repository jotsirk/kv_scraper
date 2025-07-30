package com.kv_scraper.model

import org.jsoup.nodes.Document

data class DocumentResponse(
  val document: Document?,
  val responseCode: Int,
)
