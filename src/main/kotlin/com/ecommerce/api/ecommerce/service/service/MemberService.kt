package com.ecommerce.api.ecommerce.service.service

import com.ecommerce.api.ecommerce.dto.req.*
import com.ecommerce.api.ecommerce.dto.res.MemberResDto

interface MemberService {
    suspend fun signup(reqDto: SignupReqDto): String
    suspend fun signin(reqDto: SigninReqDto): String
    suspend fun updatePassword(reqDto: UpdatePasswordReqDto, memberNo: Int): String
    suspend fun findPassword(reqDto: FindPwReqDto): String
    suspend fun createAuth(reqDto: CreateAuthReqDto): String
    suspend fun getMemberInfo(): MemberResDto
}