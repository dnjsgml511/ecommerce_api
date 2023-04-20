package com.ecommerce.api.ecommerce.service.service

import com.ecommerce.api.ecommerce.dto.req.SaveProductReqDto

interface SaveProductService{
    fun saveProduct(reqDto: SaveProductReqDto): String
}