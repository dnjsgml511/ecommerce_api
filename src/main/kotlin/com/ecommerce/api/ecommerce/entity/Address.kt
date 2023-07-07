package com.ecommerce.api.ecommerce.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("address")
data class Address(
        @Id
        val addressNo: Int? = null,
        var label: String,
        var address: String,
        var addressDetail: String,
        val userNo: Int,
)