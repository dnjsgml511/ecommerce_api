package com.ecommerce.api.ecommerce.service.impl

import com.ecommerce.api.ecommerce.entity.Product
import com.ecommerce.api.ecommerce.repository.ProductRepository
import com.ecommerce.api.ecommerce.service.service.GetProductService
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Service

@Service
class GetProductServiceImpl(
    private val productRepository: ProductRepository
): GetProductService {

    override suspend fun searchProduct(productName: String): List<Product> = coroutineScope{
        val productName = "%${productName}%"
        productRepository.findByProductNameLike(productName = productName)
            .toStream().toList()
    }
}