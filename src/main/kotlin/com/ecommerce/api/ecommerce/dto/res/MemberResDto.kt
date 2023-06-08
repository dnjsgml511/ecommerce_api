package com.ecommerce.api.ecommerce.dto.res

data class MemberResDto(
    val memberNo: Int,
    val memberId: String,
    val email: String,
    val memberName: String,
    val memberState: String,
)