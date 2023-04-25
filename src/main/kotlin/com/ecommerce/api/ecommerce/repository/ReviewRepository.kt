package com.ecommerce.api.ecommerce.repository

import com.ecommerce.api.ecommerce.entity.Review
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface ReviewRepository: ReactiveCrudRepository<Review, Int> {
}