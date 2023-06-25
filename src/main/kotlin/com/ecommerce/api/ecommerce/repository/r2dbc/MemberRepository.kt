package com.ecommerce.api.ecommerce.repository.r2dbc

import com.ecommerce.api.ecommerce.entity.Member
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface MemberRepository: ReactiveCrudRepository<Member, Int> {
    fun findByMemberNo(memberNo: Int): Mono<Member>
    fun findByMemberId(memberId: String): Mono<Member>
    fun findByEmail(email: String): Mono<Member>
}