package com.ecommerce.api.ecommerce.repository

import com.ecommerce.api.ecommerce.entity.Basket
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface BasketRepository: ReactiveCrudRepository<Basket, Int> {
    fun findByMemberNo(memberNo: Int): Flux<Basket>
}