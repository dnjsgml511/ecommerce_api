package com.ecommerce.api.ecommerce.service.review

import com.ecommerce.api.ecommerce.dto.req.SaveReviewReqDto
import com.ecommerce.api.ecommerce.dto.req.UpdateReviewReqDto
import com.ecommerce.api.ecommerce.dto.res.ReviewResDto
import com.ecommerce.api.ecommerce.entity.Review
import com.ecommerce.api.ecommerce.repository.r2dbc.ReviewRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.time.LocalDateTime

@Service
class ReviewService(
    val reviewRepository: ReviewRepository,
) {

    suspend fun getReview(productNo: Int) = coroutineScope {
        reviewRepository.findByProductNo(productNo)
            .flatMap { Flux.just(ReviewResDto(it)) }
            .asFlow().toList()
    }

    suspend fun saveReview(reqDto: SaveReviewReqDto): String = coroutineScope{
        val entity = Review(
            reviewNo = null,
            reviewTitle = reqDto.reviewTitle,
            reviewContent = reqDto.reviewContent,
            productNo = reqDto.productNo,
            createDate = LocalDateTime.now().toString(),
        )

        reviewRepository.save(entity).awaitSingle()
            .run { "success" }
    }

    suspend fun updateReview(reqDto: UpdateReviewReqDto): String = coroutineScope{
        val reviewData = reviewRepository.findById(reqDto.reviewNo)
            .awaitSingleOrNull() ?: return@coroutineScope "실패했습니다."

        val updateEntity = reviewData.copy(
            reviewTitle = reqDto.reviewTitle,
            reviewContent = reqDto.reviewContent,
            modifyDate = LocalDateTime.now().toString()
        )

        reviewRepository.save(updateEntity).awaitSingle()
            .run { "success" }
    }
}