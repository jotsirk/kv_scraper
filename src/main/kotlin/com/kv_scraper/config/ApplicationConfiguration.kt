package com.kv_scraper.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InjectionPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfiguration {

  @Bean
  fun logger(injectionPoint: InjectionPoint): Logger {
    val declaringClass = injectionPoint.methodParameter?.containingClass
      ?: injectionPoint.field?.declaringClass
      ?: Any::class.java
    return LoggerFactory.getLogger(declaringClass)
  }
}
