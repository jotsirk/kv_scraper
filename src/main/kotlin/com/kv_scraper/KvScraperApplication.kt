package com.kv_scraper

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class KvScraperApplication

fun main(args: Array<String>) {
  runApplication<KvScraperApplication>(*args)
  Thread.currentThread().join()
}
