package com.ecommerce.api.ecommerce.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("emailAuth")
data class EmailAuth(
    @Id
    val emailAuthNo: Int?,
    val email: String,
    val auth: String,
)
