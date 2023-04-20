package com.ecommerce.api.ecommerce.dto.res

data class ProductResDto(
    val productNo: Int,
    val sellerNo: Int,
    val productName: String,
    val productExplain: String,
    val productPrice: Int,
    val productCount: Int,
    val productImage: String,
    val createDate: String,
)