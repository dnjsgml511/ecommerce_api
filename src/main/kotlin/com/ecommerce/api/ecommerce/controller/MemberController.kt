package com.ecommerce.api.ecommerce.controller

import com.ecommerce.api.ecommerce.dto.req.FindPwReqDto
import com.ecommerce.api.ecommerce.dto.req.SigninReqDto
import com.ecommerce.api.ecommerce.dto.req.SignupReqDto
import com.ecommerce.api.ecommerce.dto.req.UpdatePasswordReqDto
import com.ecommerce.api.ecommerce.dto.res.MemberResDto
import com.ecommerce.api.ecommerce.framework.response.ResponseDto
import com.ecommerce.api.ecommerce.framework.response.ResponseModel
import com.ecommerce.api.ecommerce.service.impl.MemberServiceImpl
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/member")
class MemberController(
    private val memberService: MemberServiceImpl
): ResponseModel() {

    @PostMapping("/signup")
    suspend fun signup(
        @RequestBody signupReqDto: SignupReqDto
    ): ResponseDto<String> {
        return memberService.signup(reqDto = signupReqDto)
            .responseMapping()
    }

    @PostMapping("/signin")
    suspend fun signin(
        @RequestBody signinReqDto: SigninReqDto
    ): ResponseDto<String> {
        return memberService.signin(reqDto = signinReqDto)
            .responseMapping()
    }

    @PostMapping("/password/update")
    suspend fun updatePassword(
        @RequestBody updatePasswordReqDto: UpdatePasswordReqDto
    ): ResponseDto<String> {
        return memberService.updatePassword(reqDto = updatePasswordReqDto, memberNo = 1)
            .responseMapping()
    }

    @PostMapping("/password/find")
    suspend fun findPassword(
        @RequestBody findPwReqDto: FindPwReqDto
    ): ResponseDto<String> {
        return memberService.findPassword(reqDto = findPwReqDto)
            .responseMapping()
    }

    @GetMapping("/info")
    suspend fun getMemberInfo(
    ): ResponseDto<MemberResDto> {
        return memberService.getMemberInfo()
            .responseMapping()
    }
}