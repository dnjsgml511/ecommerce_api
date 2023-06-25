package com.ecommerce.api.ecommerce.framework.config.cors

import mu.KotlinLogging
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfig : WebMvcConfigurer {

    val log = KotlinLogging.logger {  }

    override fun addCorsMappings(registry: CorsRegistry) {

        log.info { "...cors add..." }

        registry.addMapping("/**")
            .allowedOrigins("http://localhost:3000")
            .allowedMethods("GET", "POST")
    }
}