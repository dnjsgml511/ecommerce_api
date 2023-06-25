package com.ecommerce.api.ecommerce.service.member

import com.ecommerce.api.ecommerce.dto.req.*
import com.ecommerce.api.ecommerce.service.mail.SendEmail
import com.ecommerce.api.ecommerce.service.member.component.*
import com.ecommerce.api.ecommerce.service.token.Token
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Service

@Service
class MemberService(
        private val checkMember: CheckMember,
        private val saveMember: SaveMember,
        private val sendEmail: SendEmail,
        private val token: Token,
        private val getMember: GetMember,
        private val encryption: Encryption,
        private val createNewPassword: CreateNewPassword,
) {
    suspend fun signUp(reqDto: SignupReqDto) = coroutineScope {

        if(!checkMember.checkId(reqDto.memberId)){
            throw Exception("아이디 확인")
        }

        if(!checkMember.checkPassword(reqDto.memberPw)){
            throw Exception("비밀번호 확인")
        }

        if(!checkMember.checkEmail(reqDto.email)){
            throw Exception("이메일 확인")
        }

        val member = saveMember.createMember(reqDto)

        sendEmail.welcomeMail(email = reqDto.email, name = reqDto.memberName)

        token.createToken(memberNo = member.memberNo ?: 0)
    }
    suspend fun signIn(reqDto: SigninReqDto) = coroutineScope {
        val member = getMember.getMember(reqDto.memberId) ?: throw Exception("아이디 확인")

        if(!encryption.checkPassword(reqDto.memberPw, member.memberPw)) throw Exception("비밀번호 확인")

        token.createToken(memberNo = member.memberNo ?: 0)
    }
    suspend fun findId(reqDto: FindIdReqDto) = coroutineScope {
        val member = getMember.getMemberToEmail(email = reqDto.email)
            ?: throw Exception("이메일 확인")

        sendEmail.findId(email = member.email, id = member.memberId)

        return@coroutineScope "success"
    }
    suspend fun findPassword(reqDto: FindPwReqDto) = coroutineScope {
        if(!checkMember.checkPassword(reqDto.password)){
            throw Exception("비밀번호 확인")
        }

        val newPassword = createNewPassword.randomPassword()
        val member = saveMember.updatePassword(memberNo = reqDto.memberNo, memberPw = newPassword)
            ?: throw Exception("비밀번호 변경 실패")

        sendEmail.findPassword(
            email = member.email,
            password = newPassword
        )

        return@coroutineScope "success"
    }
    suspend fun changeId(reqDto: ChangeIdReqDto) = coroutineScope {
        saveMember.updateId(memberNo = reqDto.memberNo, memberId = reqDto.memberId)
            ?: throw Exception("아이디 업데이트 실패")

        return@coroutineScope "success"
    }
    suspend fun changePw(reqDto: ChangePasswordReqDto) = coroutineScope {
        if(!checkMember.checkPassword(reqDto.password)){
            throw Exception("비밀번호 확인")
        }

        saveMember.updatePassword(memberNo = reqDto.memberNo, memberPw = reqDto.password)
            ?: throw Exception("비밀번호 변경 실패")

        return@coroutineScope "success"
    }
    suspend fun changeEmail(reqDto: ChangeEmailReqDto) = coroutineScope {
        saveMember.updateEmail(memberNo = reqDto.memberNo, email = reqDto.email)
            ?: throw Exception("이메일 업데이트 실패")

        return@coroutineScope "success"
    }

}