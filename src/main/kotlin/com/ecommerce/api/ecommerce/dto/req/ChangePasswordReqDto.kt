package com.ecommerce.api.ecommerce.dto.req

data class ChangePasswordReqDto(
    val memberNo: Int,
    val password: String,
)