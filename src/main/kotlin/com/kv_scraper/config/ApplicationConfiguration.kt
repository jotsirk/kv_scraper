package com.kv_scraper.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InjectionPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class ApplicationConfiguration: WebMvcConfigurer {

  @Bean
  fun logger(injectionPoint: InjectionPoint): Logger {
    val declaringClass = injectionPoint.methodParameter?.containingClass
      ?: injectionPoint.field?.declaringClass
      ?: Any::class.java
    return LoggerFactory.getLogger(declaringClass)
  }

  override fun addCorsMappings(registry: CorsRegistry) {
    registry.addMapping("/api/**")
      .allowedOrigins("http://localhost:3000", "http://192.168.3.28:3000")
      .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
      .allowCredentials(true)
      .maxAge(3600)
  }
}
