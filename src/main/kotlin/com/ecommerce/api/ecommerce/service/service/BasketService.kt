package com.ecommerce.api.ecommerce.service.service

import com.ecommerce.api.ecommerce.dto.req.SaveBasketReqDto
import com.ecommerce.api.ecommerce.entity.Product

interface BasketService {
    suspend fun saveBasket(reqDto: SaveBasketReqDto, memberNo: Int): String
    suspend fun getBasket(memberNo: Int): List<Product>
}