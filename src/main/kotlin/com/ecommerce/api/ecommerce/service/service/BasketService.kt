package com.ecommerce.api.ecommerce.service.service

import com.ecommerce.api.ecommerce.dto.req.SaveBasketReqDto
import com.ecommerce.api.ecommerce.dto.res.BasketResDto

interface BasketService {
    suspend fun saveBasket(reqDto: SaveBasketReqDto, memberNo: Int): String
    suspend fun getBasket(memberNo: Int): List<BasketResDto>
}