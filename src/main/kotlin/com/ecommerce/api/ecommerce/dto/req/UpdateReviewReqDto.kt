package com.ecommerce.api.ecommerce.dto.req

data class UpdateReviewReqDto(
    val reviewNo: Int,
    val reviewTitle: String,
    val reviewContent: String,
)
