package com.ecommerce.api.ecommerce.dto.dto

data class MailRequestDto(
    val to: Array<String>,
    val subject: String,
    val text: String,
)
