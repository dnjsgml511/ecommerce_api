package com.ecommerce.api.ecommerce.repository.r2dbc

import com.ecommerce.api.ecommerce.entity.Member
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface MemberRepository: ReactiveCrudRepository<Member, Int> {
    fun findByMemberId(memberId: String): Mono<Member>
}