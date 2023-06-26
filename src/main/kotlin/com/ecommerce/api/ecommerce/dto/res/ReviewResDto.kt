package com.ecommerce.api.ecommerce.dto.res

import com.ecommerce.api.ecommerce.entity.Review

data class ReviewResDto(
    val reviewNo: Int ?= null,
    val reviewTitle: String,
    val reviewContent: String,
    val productNo: Int,
    val createDate: String,
    val modifyDate: String? = null,
){
    constructor(review: Review): this(
        reviewNo = review.reviewNo,
        reviewTitle = review.reviewTitle,
        reviewContent = review.reviewContent,
        productNo = review.productNo,
        createDate = review.createDate,
        modifyDate = review.modifyDate,
    )
}