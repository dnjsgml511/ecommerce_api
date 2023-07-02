package com.ecommerce.api.ecommerce.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckController {

    @Value("\${spring.r2dbc.url}")
    private lateinit var mysqlHostPort: String

    @GetMapping("/health")
    fun healthCheck(): String{
        return "healthy! - mysql : $mysqlHostPort"
    }

}