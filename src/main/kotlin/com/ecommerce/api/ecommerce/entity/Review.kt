package com.ecommerce.api.ecommerce.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("review")
data class Review(
    @Id
    val reviewNo: Int ?= null,
    val reviewTitle: String,
    val reviewContent: String,
    val productNo: Int,
    val createDate: String,
    val modifyDate: String? = null,
)
