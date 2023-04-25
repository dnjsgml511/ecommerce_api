package com.ecommerce.api.ecommerce.service.service

import com.ecommerce.api.ecommerce.dto.req.SaveBasketReqDto

interface BasketService {
    suspend fun saveBasket(reqDto: SaveBasketReqDto, memberNo: Int): String
}