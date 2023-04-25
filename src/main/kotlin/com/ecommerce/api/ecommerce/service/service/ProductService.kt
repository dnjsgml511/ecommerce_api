package com.ecommerce.api.ecommerce.service.service

import com.ecommerce.api.ecommerce.dto.req.SaveProductReqDto
import com.ecommerce.api.ecommerce.entity.Product

interface ProductService {
    suspend fun getProductList(productName: String): List<Product>
    suspend fun saveProduct(reqDto: SaveProductReqDto, sellerNo: Int): String
}