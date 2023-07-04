package com.ecommerce.api.ecommerce.framework.config.cors

import mu.KotlinLogging
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.WebFluxConfigurer

@Configuration
@EnableWebFlux
class CorsConfig : WebFluxConfigurer {

    val log = KotlinLogging.logger {  }

    override fun addCorsMappings(registry: CorsRegistry){
        registry.addMapping("/**")
                .allowedOriginPatterns("*") // any host or put domain(s) here
                .allowedMethods("GET","POST","OPTIONS") // put the http verbs you want allow
                .allowedHeaders("*") // put the http headers you want allow
                .allowCredentials(true)
    }
}