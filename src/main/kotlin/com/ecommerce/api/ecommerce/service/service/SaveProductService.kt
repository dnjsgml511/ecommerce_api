package com.ecommerce.api.ecommerce.service.service

import com.ecommerce.api.ecommerce.dto.req.SaveProductReqDto

interface SaveProductService{
    suspend fun saveProduct(reqDto: SaveProductReqDto, sellerNo: Int): String
}