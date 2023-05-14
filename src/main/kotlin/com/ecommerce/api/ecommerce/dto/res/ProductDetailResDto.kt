package com.ecommerce.api.ecommerce.dto.res

data class ProductDetailResDto(
    val productNo: Int,
    val productName: String,
    val productExplain: String,
    val productPrice: Int,
    val productCount: Int,
    val productImage: String,
    val sellerNo: Int,
    val sellerName: String,
)
