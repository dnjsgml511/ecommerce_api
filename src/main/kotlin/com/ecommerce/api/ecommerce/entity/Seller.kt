package com.ecommerce.api.ecommerce.entity

import com.ecommerce.api.ecommerce.dto.req.SignupReqDto
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("seller")
data class Seller(
    @Id
    val sellerNo: Int ?= null,
    val sellerId: String,
    val sellerPw: String,
    val sellerName: String,
    val sellerState: String,
    val createDate: LocalDateTime,
    val recentLogin: String?,
    val email: String,
)