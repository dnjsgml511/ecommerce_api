package com.ecommerce.api.ecommerce.dto.req

import java.time.LocalDate

data class SignupReqDto(
    val memberId: String,
    val memberPw: String,
    val memberName: String,
    val email: String,
    val birthDate: LocalDate,
)