package com.ecommerce.api.ecommerce

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@SpringBootApplication
@EnableR2dbcRepositories(basePackages = ["com.ecommerce.api.ecommerce.repository.r2dbc"])
class EcommerceApplication

fun main(args: Array<String>) {
	runApplication<EcommerceApplication>(*args)
}
