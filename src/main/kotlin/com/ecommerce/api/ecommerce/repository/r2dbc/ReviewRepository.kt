package com.ecommerce.api.ecommerce.repository.r2dbc

import com.ecommerce.api.ecommerce.entity.Review
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface ReviewRepository: ReactiveCrudRepository<Review, Int> {
    fun findByProductNo(productNo: Int): Flux<Review>
}