package com.ecommerce.api.ecommerce.controller

import com.ecommerce.api.ecommerce.dto.req.SaveReviewReqDto
import com.ecommerce.api.ecommerce.dto.req.UpdateReviewReqDto
import com.ecommerce.api.ecommerce.framework.response.ResponseDto
import com.ecommerce.api.ecommerce.framework.response.ResponseModel
import com.ecommerce.api.ecommerce.service.impl.ReviewServiceImpl
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/review")
class ReviewController(
    private val reviewServiceImpl: ReviewServiceImpl,
): ResponseModel() {

    @PostMapping("/save")
    suspend fun saveReview(
        @RequestBody saveReviewReqDto: SaveReviewReqDto
    ): ResponseDto<String> {
        return reviewServiceImpl.saveReview(reqDto = saveReviewReqDto).responseMapping()
    }

    @PostMapping("/update")
    suspend fun updateReview(
        @RequestBody updateReviewReqDto: UpdateReviewReqDto
    ): ResponseDto<String> {
        return reviewServiceImpl.updateReview(reqDto = updateReviewReqDto).responseMapping()
    }

}