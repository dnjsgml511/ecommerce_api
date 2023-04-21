package com.ecommerce.api.ecommerce.service.service

import com.ecommerce.api.ecommerce.dto.req.SignupReqDto

interface MemberService {
    suspend fun signup(reqDto: SignupReqDto): String
}