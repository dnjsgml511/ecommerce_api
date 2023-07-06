package com.ecommerce.api.ecommerce.framework.config.cors

import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.WebFluxConfigurer

@Configuration
@EnableWebFlux
class CorsConfig : WebFluxConfigurer {

    override fun addCorsMappings(registry: CorsRegistry){
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
    }
}