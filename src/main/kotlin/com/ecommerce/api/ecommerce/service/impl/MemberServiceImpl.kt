package com.ecommerce.api.ecommerce.service.impl

import com.ecommerce.api.ecommerce.dto.dto.MailRequestDto
import com.ecommerce.api.ecommerce.dto.req.*
import com.ecommerce.api.ecommerce.entity.EmailAuth
import com.ecommerce.api.ecommerce.entity.Member
import com.ecommerce.api.ecommerce.framework.util.mail.MessageService
import com.ecommerce.api.ecommerce.framework.util.random.CreateRandomString
import com.ecommerce.api.ecommerce.repository.r2dbc.EmailAuthRepository
import com.ecommerce.api.ecommerce.repository.r2dbc.MemberRepository
import com.ecommerce.api.ecommerce.service.service.MemberService
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.mindrot.jbcrypt.BCrypt
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class MemberServiceImpl(
    private val memberRepository: MemberRepository,
    private val emailAuthRepository: EmailAuthRepository,
    private val randomString: CreateRandomString,
    private val messageService: MessageService,
): MemberService {

    override suspend fun signup(reqDto: SignupReqDto): String = coroutineScope{
        val passwordHashed = BCrypt.hashpw(reqDto.memberPw, BCrypt.gensalt(10))

        val entity = Member(
            memberId = reqDto.memberId,
            memberPw = passwordHashed,
            memberName = reqDto.memberName,
            createDate = LocalDateTime.now(),
            memberState = "200",
            recentLogin = null,
            email = reqDto.email
        )

        memberRepository.save(entity)
            .awaitSingle()
            .run { "success" }
    }

    override suspend fun signin(reqDto: SigninReqDto): String = coroutineScope{

        val userData = memberRepository.findByMemberId(memberId = reqDto.memberId)
            .awaitSingleOrNull() ?: return@coroutineScope "fail"

        val validatePw = BCrypt.checkpw(reqDto.memberPw, userData.memberPw)

        if(!validatePw) return@coroutineScope "fail"

        "success"
    }

    override suspend fun updatePassword(reqDto: UpdatePasswordReqDto, memberNo: Int): String = coroutineScope{
        val memberData = memberRepository.findById(memberNo).awaitSingleOrNull() ?: return@coroutineScope "fail"

        val passwordHashed = BCrypt.hashpw(reqDto.memberPw, BCrypt.gensalt(10))

        val updateMemberData = memberData.copy(
            memberPw = passwordHashed,
        )

        memberRepository.save(updateMemberData).awaitSingle()

        "success"
    }

    override suspend fun findPassword(reqDto: FindPwReqDto): String = coroutineScope{
        val memberData = memberRepository.findByMemberId(reqDto.memberId).awaitSingleOrNull() ?: return@coroutineScope "실패했습니다."

        val newPassword = randomString.randomPassword(8)

        val updatePassword = memberData.copy(
            memberPw = BCrypt.hashpw(newPassword, BCrypt.gensalt(10))
        )

        memberRepository.save(updatePassword).awaitSingle()

        val mailRequestDto = MailRequestDto(
            to = arrayOf("dnjsgml511@naver.com"),
            subject = "원희의 스터디 - 비밀번호 찾기",
            text = "새로운 비밀번호는 ${newPassword} 입니다",
        )

        messageService.mailSend(mailRequestDto, "mail.html")

        "성공했습니다"
    }

    override suspend fun createAuth(reqDto: CreateAuthReqDto): String = coroutineScope{
        val newAuth = randomString.randomPassword(8)

        val entity = EmailAuth(
            emailAuthNo = null,
            email = reqDto.email,
            auth = newAuth
        )

        emailAuthRepository.save(entity).awaitSingle()

        val mailRequestDto = MailRequestDto(
            to = arrayOf("dnjsgml511@naver.com"),
            subject = "제목",
            text = "내용",
        )

        messageService.mailSend(mailRequestDto, "message.html")

        "success"
    }
}