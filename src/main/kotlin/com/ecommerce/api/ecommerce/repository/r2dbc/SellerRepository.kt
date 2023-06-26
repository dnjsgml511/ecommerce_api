package com.ecommerce.api.ecommerce.repository.r2dbc

import com.ecommerce.api.ecommerce.entity.Seller
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface SellerRepository: ReactiveCrudRepository<Seller, Int> {
    fun findBySellerNo(sellerNo: Int): Mono<Seller>
}