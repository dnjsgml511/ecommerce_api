package com.ecommerce.api.ecommerce.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("basket")
data class Basket(
    @Id
    val basketNo: Int ?= null,
    val productNo: Int,
    val memberNo: Int,
)
