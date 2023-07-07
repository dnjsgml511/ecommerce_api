package com.ecommerce.api.ecommerce.dto.res

data class TokenResDto(
        val token: String? = null,
        val refreshToken: String? = null,
        val permission: List<String> = listOf(),
)