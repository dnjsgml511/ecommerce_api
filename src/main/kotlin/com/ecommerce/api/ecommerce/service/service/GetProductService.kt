package com.ecommerce.api.ecommerce.service.service

import com.ecommerce.api.ecommerce.dto.req.SearchProductReqDto
import com.ecommerce.api.ecommerce.entity.Product

interface GetProductService {
    suspend fun searchProduct(reqDto: SearchProductReqDto): List<Product>
}