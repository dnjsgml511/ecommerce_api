package com.ecommerce.api.ecommerce.service.impl

import com.ecommerce.api.ecommerce.dto.req.SaveBasketReqDto
import com.ecommerce.api.ecommerce.dto.res.BasketResDto
import com.ecommerce.api.ecommerce.entity.Basket
import com.ecommerce.api.ecommerce.repository.BasketRepository
import com.ecommerce.api.ecommerce.repository.MemberRepository
import com.ecommerce.api.ecommerce.repository.ProductRepository
import com.ecommerce.api.ecommerce.service.service.BasketService
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.stereotype.Service

@Service
class BasketServiceImpl(
    private val basketRepository: BasketRepository,
    private val productRepository: ProductRepository,
    private val memberRepository: MemberRepository,
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

    override suspend fun getBasket(memberNo: Int): List<BasketResDto> = coroutineScope{
        val basketData = basketRepository.findByMemberNo(memberNo = memberNo)
            .asFlow().toList()

        basketData.mapNotNull {
            val memberData = async {
                memberRepository.findById(it.memberNo).awaitSingleOrNull()
            }

            val productData = async {
                productRepository.findById(it.productNo).awaitSingleOrNull()
            }

            BasketResDto(
                basketNo = it.basketNo ?: 0,
                productNo = it.productNo,
                memberNo = it.memberNo,
                memberName = memberData.await()?.memberName ?: "",
                productName = productData.await()?.productName ?: ""
            )
        }
    }
}