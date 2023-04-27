package com.ecommerce.api.ecommerce.repository.r2dbc

import com.ecommerce.api.ecommerce.entity.Product
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface ProductRepository: ReactiveCrudRepository<Product, Int> {
    fun findByProductNameLike(productName: String): Flux<Product>
    fun findByProductNoIn(productNo: List<Int>): Flux<Product>
}