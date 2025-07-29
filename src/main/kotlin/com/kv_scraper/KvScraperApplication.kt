package com.kv_scraper

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KvScraperApplication

fun main(args: Array<String>) {
  runApplication<KvScraperApplication>(*args)
}
