package com.ecommerce.api.ecommerce.service.impl

import com.ecommerce.api.ecommerce.dto.req.SignupReqDto
import com.ecommerce.api.ecommerce.entity.Member
import com.ecommerce.api.ecommerce.repository.MemberRepository
import com.ecommerce.api.ecommerce.service.service.MemberService
import kotlinx.coroutines.coroutineScope
import org.mindrot.jbcrypt.BCrypt
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class MemberServiceImpl(
    private val memberRepository: MemberRepository
): MemberService {
    override suspend fun signup(reqDto: SignupReqDto): String = coroutineScope{

        val passwordHashed = BCrypt.hashpw(reqDto.memberPw, BCrypt.gensalt(10))

        val entity = Member(
            memberId = reqDto.memberId,
            memberPw = passwordHashed,
            craeteDate = LocalDateTime.now(),
            recentLogin = null
        )

        memberRepository.save(entity)
            .run { "성공했습니다." }
    }
}