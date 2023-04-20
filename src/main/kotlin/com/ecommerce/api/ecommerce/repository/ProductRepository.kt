package com.ecommerce.api.ecommerce.repository

import com.ecommerce.api.ecommerce.entity.Product
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface ProductRepository: ReactiveCrudRepository<Product, Int> {
    fun findByProductName(productName: String): Flux<Product>
}