package com.ecommerce.api.ecommerce.dto.req

data class SaveProductReqDto(
    val productName: String,
    val productExplain: String,
    val productPrice: Int,
    val productCount: Int,
    val productImage: String,
)
