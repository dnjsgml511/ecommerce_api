package com.ecommerce.api.ecommerce.service.member.component

import com.ecommerce.api.ecommerce.dto.req.SigninReqDto
import com.ecommerce.api.ecommerce.entity.Member
import com.ecommerce.api.ecommerce.repository.r2dbc.MemberRepository
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class GetMember(
    private val memberRepository: MemberRepository
) {
    suspend fun getMember(id: String): Member? {
        return memberRepository.findByMemberId(id).awaitSingleOrNull()
    }
    suspend fun getMemberToMemberNo(memberNo: Int): Member? {
        return memberRepository.findByMemberNo(memberNo).awaitSingleOrNull()
    }
    suspend fun getMemberToEmail(email: String): Member? {
        return memberRepository.findByEmail(email).awaitSingleOrNull()
    }
}