package com.ecommerce.api.ecommerce.service.member.component

import com.ecommerce.api.ecommerce.repository.r2dbc.MemberRepository
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.stereotype.Component

@Component
class Duplication(
    private val memberRepository: MemberRepository
) {
    suspend fun id(id: String): Boolean{
        return memberRepository.findByMemberId(id).awaitSingleOrNull() == null
    }

    suspend fun email(email: String): Boolean{
        return memberRepository.findByEmail(email).awaitSingleOrNull() == null
    }
}