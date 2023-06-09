package com.ecommerce.api.ecommerce.service.basket

import com.ecommerce.api.ecommerce.dto.req.DeleteBasketReqDto
import com.ecommerce.api.ecommerce.dto.req.SaveBasketReqDto
import com.ecommerce.api.ecommerce.entity.Product
import com.ecommerce.api.ecommerce.repository.r2dbc.ProductRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service

// https://unluckyjung.github.io/spring/2023/03/11/spring-redisTemplate/
@Service
class BasketService(
    private val productRepository: ProductRepository,
    private val redisTemplate: StringRedisTemplate,
) {

    suspend fun deleteBasket(reqDto: DeleteBasketReqDto, memberNo: Int): String = coroutineScope {

        val basketList = getBasket(memberNo).toMutableList()
        basketList.removeIf { product -> product.productNo == reqDto.productNo }

        val operations = redisTemplate.opsForList().operations
        operations.delete(memberNo.toString())

        basketList.map {
            saveBasket(SaveBasketReqDto(productNo = reqDto.productNo), memberNo)
        }

        "success"
    }

    suspend fun saveBasket(reqDto: SaveBasketReqDto, memberNo: Int): String = coroutineScope {

        val redis = redisTemplate.opsForList()
        redis.rightPush(memberNo.toString(), reqDto.productNo.toString())

        "success"
    }

     suspend fun getBasket(memberNo: Int): List<Product> = coroutineScope {

        val operations = redisTemplate.opsForList().operations
        val basketData = (operations.opsForList().range(memberNo.toString(), 0, -1) ?: listOf())
            .map{ it.toInt() }

        return@coroutineScope productRepository.findByProductNoIn(basketData)
            .asFlow().toList()
    }
}