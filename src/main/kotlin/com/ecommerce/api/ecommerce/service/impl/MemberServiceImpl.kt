package com.ecommerce.api.ecommerce.service.impl

import com.ecommerce.api.ecommerce.dto.req.SignupReqDto
import com.ecommerce.api.ecommerce.entity.Member
import com.ecommerce.api.ecommerce.repository.r2dbc.MemberRepository
import com.ecommerce.api.ecommerce.service.service.MemberService
import kotlinx.coroutines.coroutineScope
import org.mindrot.jbcrypt.BCrypt
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull

@Service
class MemberServiceImpl(
    private val memberRepository: MemberRepository
): MemberService {

    override suspend fun signup(reqDto: SignupReqDto): String = coroutineScope{
        val passwordHashed = BCrypt.hashpw(reqDto.memberPw, BCrypt.gensalt(10))

        val entity = Member(
            memberId = reqDto.memberId,
            memberPw = passwordHashed,
            memberName = reqDto.memberName,
            createDate = LocalDateTime.now().toString(),
            memberState = "200",
            recentLogin = null
        )

        memberRepository.save(entity)
            .awaitSingle()
            .run { "성공했습니다." }
    }

    override suspend fun signin(reqDto: SignupReqDto): String = coroutineScope{
        val userData = memberRepository.findByMemberId(memberId = reqDto.memberId)
            .awaitSingleOrNull() ?: return@coroutineScope "fail"

        val passwordHashed = BCrypt.hashpw(reqDto.memberPw, BCrypt.gensalt(10))
        val validatePw = BCrypt.checkpw(userData.memberPw, passwordHashed)

        if(!validatePw) return@coroutineScope "fail"

        "success"
    }
}