package com.ecommerce.api.ecommerce.dto.res

data class BasketResDto(
    val basketNo: Int,
    val productNo: Int,
    val memberNo: Int,
    val memberName: String,
    val productName: String,
)