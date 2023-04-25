package com.ecommerce.api.ecommerce.service.service

import com.ecommerce.api.ecommerce.dto.req.SaveReviewReqDto
import com.ecommerce.api.ecommerce.dto.req.UpdateReviewReqDto

interface ReviewService {
    suspend fun saveReview(reqDto: SaveReviewReqDto): String
    suspend fun updateReview(reqDto: UpdateReviewReqDto): String
}