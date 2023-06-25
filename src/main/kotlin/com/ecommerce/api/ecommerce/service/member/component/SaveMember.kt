package com.ecommerce.api.ecommerce.service.member.component

import com.ecommerce.api.ecommerce.dto.req.SignupReqDto
import com.ecommerce.api.ecommerce.entity.Member
import com.ecommerce.api.ecommerce.repository.r2dbc.MemberRepository
import com.ecommerce.api.ecommerce.service.member.component.Encryption
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.stereotype.Service

@Service
class SaveMember(
        private val memberRepository: MemberRepository,
        private val encryption: Encryption,
) {
    suspend fun createMember(reqDto: SignupReqDto): Member {
        val passwordHashed = encryption.encryption(reqDto.memberPw)
        val copy = reqDto.copy(memberPw = passwordHashed)
        return memberRepository.save(Member(copy)).awaitSingle()
    }

    suspend fun updateId(memberNo: Int, memberId: String): Member? {
        val member = memberRepository.findByMemberNo(memberNo).awaitSingleOrNull()
                ?: return null

        val copy = member.copy(memberId = memberId)
        return memberRepository.save(copy).awaitSingle()
    }

    suspend fun updatePassword(memberNo: Int, memberPw: String): Member?{
        val member = memberRepository.findByMemberNo(memberNo).awaitSingleOrNull()
                ?: return null

        val passwordHashed = encryption.encryption(memberPw)

        val copy = member.copy(memberPw = passwordHashed)

        return memberRepository.save(copy).awaitSingle()
    }

    suspend fun updateEmail(memberNo: Int, email: String): Member?{
        val member = memberRepository.findByMemberNo(memberNo).awaitSingleOrNull()
                ?: return null

        val copy = member.copy(email = email)

        return memberRepository.save(copy).awaitSingle()
    }
}