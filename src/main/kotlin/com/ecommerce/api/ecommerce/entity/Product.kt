package com.ecommerce.api.ecommerce.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("product")
data class Product(
    @Id
    val productNo: Int?,
    val sellerNo: Int,
    val productName: String,
    val productExplain: String,
    val productPrice: Int,
    val productCount: Int,
    val productImage: String?,
    val createDate: String,
)
