package com.ecommerce.api.ecommerce.controller

import com.ecommerce.api.ecommerce.dto.req.SignupReqDto
import com.ecommerce.api.ecommerce.framework.response.ResponseDto
import com.ecommerce.api.ecommerce.framework.response.ResponseModel
import com.ecommerce.api.ecommerce.service.impl.MemberServiceImpl
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/member")
class MemberController(
    private val memberService: MemberServiceImpl
): ResponseModel() {

    @PostMapping("/signup")
    suspend fun signup(
        @RequestParam signupReqDto: SignupReqDto
    ): ResponseDto<String> {
        return memberService.signup(reqDto = signupReqDto)
            .responseMapping()
    }
}