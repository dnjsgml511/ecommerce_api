//package com.ecommerce.api.ecommerce.service
//
//import com.ecommerce.api.ecommerce.dto.req.*
//import com.ecommerce.api.ecommerce.dto.res.MemberResDto
//import com.ecommerce.api.ecommerce.entity.EmailAuth
//import com.ecommerce.api.ecommerce.entity.Member
////import com.ecommerce.api.ecommerce.framework.config.security.JwtComponent
//import com.ecommerce.api.ecommerce.framework.util.mail.MessageService
//import com.ecommerce.api.ecommerce.framework.util.random.CreateRandomString
//import com.ecommerce.api.ecommerce.repository.r2dbc.EmailAuthRepository
//import com.ecommerce.api.ecommerce.repository.r2dbc.MemberRepository
//import kotlinx.coroutines.async
//import kotlinx.coroutines.coroutineScope
//import kotlinx.coroutines.reactor.awaitSingle
//import kotlinx.coroutines.reactor.awaitSingleOrNull
//import org.mindrot.jbcrypt.BCrypt
//import org.springframework.stereotype.Service
//import java.time.LocalDateTime
//
//
//@Service
//class MemberService(
//    private val memberRepository: MemberRepository,
//    private val emailAuthRepository: EmailAuthRepository,
//    private val randomString: CreateRandomString,
//    private val messageService: MessageService,
////    private val jwtComponent: JwtComponent,
//) {
//
//    suspend fun signup(reqDto: SignupReqDto): String = coroutineScope{
//
//        // 아이디 중복 체크
//        val idCheck = memberRepository.findByMemberId(memberId = reqDto.memberId)
//            .awaitSingleOrNull()
//        if(idCheck != null){
//            return@coroutineScope "fail"
//        }
//
//        val passwordHashed = BCrypt.hashpw(reqDto.memberPw, BCrypt.gensalt(10))
//        val entity = Member(
//            memberId = reqDto.memberId,
//            memberPw = passwordHashed,
//            memberName = reqDto.memberName,
//            createDate = LocalDateTime.now(),
//            memberState = "200",
//            recentLogin = null,
//            email = reqDto.email
//        )
//        memberRepository.save(entity).awaitSingle()
//
//        // 환영 메일 보내기
//        messageService.mailSend(
//            to = arrayOf("dnjsgml511@naver.com"),
//            subject = "환영합니다~",
//            text = "안녕하세요 ${reqDto.memberName}님 환영합니다.",
//            fileName = "mail.html"
//        )
//
//        "success"
//    }
//
//    suspend fun signin(reqDto: SigninReqDto): String = coroutineScope{
//
//        val memberData = memberRepository.findByMemberId(memberId = reqDto.memberId)
//            .awaitSingleOrNull() ?: return@coroutineScope "fail"
//
//        val validatePw = BCrypt.checkpw(reqDto.memberPw, memberData.memberPw)
//
//        if(!validatePw) return@coroutineScope "fail"
//
////        val token = jwtComponent.createToken(memberData.memberNo ?: 0)
//
////        token
//
//        "test,,"
//    }
//
//
//    suspend fun updatePassword(reqDto: UpdatePasswordReqDto, memberNo: Int): String = coroutineScope{
//        val memberData = memberRepository.findById(memberNo).awaitSingleOrNull() ?: return@coroutineScope "fail"
//
//        val passwordHashed = BCrypt.hashpw(reqDto.memberPw, BCrypt.gensalt(10))
//
//        val updateMemberData = memberData.copy(
//            memberPw = passwordHashed,
//        )
//
//        memberRepository.save(updateMemberData).awaitSingle()
//
//        "success"
//    }
//
//    suspend fun findPassword(reqDto: FindPwReqDto): String = coroutineScope{
//        // 아이디, 이메일 체크
//        val memberData = memberRepository.findByMemberIdAndEmail(memberId = reqDto.memberId, email = reqDto.email)
//            .awaitSingleOrNull() ?: return@coroutineScope "fail"
//
//        val newPassword = randomString.randomPassword(8)
//
//        val updatePassword = memberData.copy(
//            memberPw = BCrypt.hashpw(newPassword, BCrypt.gensalt(10))
//        )
//
//        memberRepository.save(updatePassword).awaitSingle()
//
//        messageService.mailSend(
//            to = arrayOf("dnjsgml511@naver.com"),
//            subject = "비밀번호 찾기",
//            text = "새로운 비밀번호는 ${newPassword} 입니다",
//            fileName = "mail.html"
//        )
//
//        "success"
//    }
//
//    suspend fun createAuth(reqDto: CreateAuthReqDto): String = coroutineScope{
//        val newAuth = randomString.randomPassword(8)
//
//        val entity = EmailAuth(
//            emailAuthNo = null,
//            email = reqDto.email,
//            auth = newAuth
//        )
//
//        emailAuthRepository.save(entity).awaitSingle()
//
//        messageService.mailSend(
//            to = arrayOf(reqDto.email),
//            subject = "이메일 인증",
//            text = "인증번호 : $newAuth",
//            fileName = "mail.html")
//
//        "success"
//    }
//
//    suspend fun getMemberInfo(): MemberResDto = coroutineScope{
//        val memberData = async {
//            memberRepository.findById(1).awaitSingleOrNull()
//        }
//
//        MemberResDto(
//            memberNo = memberData.await()?.memberNo ?: 0,
//            memberId = memberData.await()?.memberId ?: "",
//            email = memberData.await()?.email ?: "",
//            memberName = memberData.await()?.memberName ?: "",
//            memberState = memberData.await()?.memberState ?: ""
//        )
//    }
//}