package com.ecommerce.api.ecommerce.service.impl

import com.ecommerce.api.ecommerce.dto.req.SaveBasketReqDto
import com.ecommerce.api.ecommerce.entity.Basket
import com.ecommerce.api.ecommerce.repository.BasketRepository
import com.ecommerce.api.ecommerce.service.service.BasketService
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class BasketServiceImpl(
    private val basketRepository: BasketRepository,
): BasketService {
    override suspend fun saveBasket(reqDto: SaveBasketReqDto, memberNo: Int): String = coroutineScope {
        val entity = Basket(
            basketNo = null,
            productNo = reqDto.productNo,
            memberNo = memberNo
        )

        basketRepository.save(entity).awaitSingle()
            .run { "성공했습니다." }
    }
}