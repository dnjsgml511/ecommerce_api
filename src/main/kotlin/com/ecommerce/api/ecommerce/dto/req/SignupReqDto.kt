package com.ecommerce.api.ecommerce.dto.req

data class SignupReqDto(
    val memberId: String,
    val memberPw: String,
    val memberName: String,
)