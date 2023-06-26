package com.ecommerce.api.ecommerce.controller

import com.ecommerce.api.ecommerce.dto.req.SaveReviewReqDto
import com.ecommerce.api.ecommerce.dto.req.UpdateReviewReqDto
import com.ecommerce.api.ecommerce.dto.res.ReviewResDto
import com.ecommerce.api.ecommerce.framework.response.ResponseDto
import com.ecommerce.api.ecommerce.framework.response.ResponseModel
import com.ecommerce.api.ecommerce.service.review.ReviewService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/review")
class ReviewController(
        private val reviewService: ReviewService,
): ResponseModel() {

    @PostMapping("/save")
    suspend fun saveReview(
        @RequestBody saveReviewReqDto: SaveReviewReqDto
    ): ResponseDto<String> {
        return reviewService.saveReview(reqDto = saveReviewReqDto).responseMapping()
    }

    @PostMapping("/update")
    suspend fun updateReview(
        @RequestBody updateReviewReqDto: UpdateReviewReqDto
    ): ResponseDto<String> {
        return reviewService.updateReview(reqDto = updateReviewReqDto).responseMapping()
    }

    @GetMapping("/list")
    suspend fun getReview(
        @RequestParam productNo: Int,
    ): ResponseDto<List<ReviewResDto>> {
        return reviewService.getReview(productNo = productNo).responseMapping()
    }

}