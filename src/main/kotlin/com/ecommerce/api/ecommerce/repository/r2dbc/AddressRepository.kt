package com.ecommerce.api.ecommerce.repository.r2dbc

import com.ecommerce.api.ecommerce.entity.Address
import org.springframework.data.r2dbc.repository.R2dbcRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface AddressRepository: R2dbcRepository<Address, Int> {
    fun findByUserNo(userNo: Int): Flux<Address>
    fun existsByAddressAndAddressDetailAndUserNo(address: String, addressDetail: String, userNo: Int): Mono<Boolean>
    fun findByUserNoAndAddressNo(userNo: Int, addressNo: Int): Mono<Address>
}