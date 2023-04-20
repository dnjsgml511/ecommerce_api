package com.ecommerce.api.ecommerce.service.impl

import com.ecommerce.api.ecommerce.dto.req.SaveProductReqDto
import com.ecommerce.api.ecommerce.repository.ProductRepository
import com.ecommerce.api.ecommerce.service.service.SaveProductService
import org.springframework.stereotype.Service

@Service
class SaveProductServiceImpl(
    private val productRepository: ProductRepository
): SaveProductService {

    override fun saveProduct(reqDto: SaveProductReqDto): String {
        return "성공했습니다."
    }
}