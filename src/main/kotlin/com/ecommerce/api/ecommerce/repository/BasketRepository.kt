package com.ecommerce.api.ecommerce.repository

import com.ecommerce.api.ecommerce.entity.Basket
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface BasketRepository: ReactiveCrudRepository<Basket, Int> {
}