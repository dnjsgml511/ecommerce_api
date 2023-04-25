package com.ecommerce.api.ecommerce.dto.req

data class SaveReviewReqDto(
    val reviewTitle: String,
    val reviewContent: String,
    val productNo: Int
)