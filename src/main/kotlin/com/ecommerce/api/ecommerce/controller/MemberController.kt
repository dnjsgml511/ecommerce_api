package com.ecommerce.api.ecommerce.controller

import com.ecommerce.api.ecommerce.dto.req.*
import com.ecommerce.api.ecommerce.dto.res.MemberResDto
import com.ecommerce.api.ecommerce.dto.res.TokenResDto
import com.ecommerce.api.ecommerce.entity.Member
import com.ecommerce.api.ecommerce.framework.response.ResponseDto
import com.ecommerce.api.ecommerce.framework.response.ResponseModel
import com.ecommerce.api.ecommerce.service.member.MemberService
import com.ecommerce.api.ecommerce.service.token.Token
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/member")
class MemberController(
    private val memberService: MemberService,
    private val token: Token,
): ResponseModel() {

    @PostMapping("/signup")
    suspend fun signup(
        @RequestBody signupReqDto: SignupReqDto
    ): ResponseDto<TokenResDto> {
        return memberService.signUp(reqDto = signupReqDto)
            .responseMapping()
    }

    @PostMapping("/signin")
    suspend fun signin(
        @RequestBody signinReqDto: SigninReqDto
    ): ResponseDto<TokenResDto> {
        return memberService.signIn(reqDto = signinReqDto)
            .responseMapping()
    }

    @PostMapping("/password/update")
    suspend fun updatePassword(
        @RequestBody changePasswordReqDto: ChangePasswordReqDto
    ): ResponseDto<String> {
        return memberService.changePw(reqDto = changePasswordReqDto)
            .responseMapping()
    }

    @PostMapping("/password/find")
    suspend fun findPassword(
        @RequestBody findPwReqDto: FindPwReqDto
    ): ResponseDto<String> {
        return memberService.findPassword(reqDto = findPwReqDto)
            .responseMapping()
    }

    @PostMapping("/id/find")
    suspend fun findId(
        @RequestBody findIdReqDto: FindIdReqDto
    ): ResponseDto<String> {
        return memberService.findId(reqDto = findIdReqDto)
            .responseMapping()
    }

    @PostMapping("/id/update")
    suspend fun updateId(
        @RequestBody changeIdReqDto: ChangeIdReqDto
    ): ResponseDto<String> {
        return memberService.changeId(reqDto = changeIdReqDto)
            .responseMapping()
    }

    @PostMapping("/email/update")
    suspend fun updateEmail(
        @RequestBody changeEmailReqDto: ChangeEmailReqDto
    ): ResponseDto<String> {
        return memberService.changeEmail(reqDto = changeEmailReqDto)
            .responseMapping()
    }

    @GetMapping("/user")
    suspend fun getUser(
        @RequestAttribute memberNo: Int
    ): ResponseDto<MemberResDto> {
        return memberService.getUser(memberNo = memberNo)
                .responseMapping()
    }
}