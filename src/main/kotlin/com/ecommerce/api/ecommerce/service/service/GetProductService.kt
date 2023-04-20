package com.ecommerce.api.ecommerce.service.service

import com.ecommerce.api.ecommerce.entity.Product

interface GetProductService {
    suspend fun searchProduct(productName: String): List<Product>
}