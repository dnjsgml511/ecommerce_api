package com.ecommerce.api.ecommerce.service.impl

import com.ecommerce.api.ecommerce.dto.req.SaveProductReqDto
import com.ecommerce.api.ecommerce.entity.Product
import com.ecommerce.api.ecommerce.repository.ProductRepository
import com.ecommerce.api.ecommerce.service.service.SaveProductService
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.coRouter
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class SaveProductServiceImpl(
    private val productRepository: ProductRepository
): SaveProductService {

    override suspend fun saveProduct(reqDto: SaveProductReqDto, sellerNo: Int): String = coroutineScope{

        val entity = Product(
            productNo = null,
            sellerNo = sellerNo,
            productName = reqDto.productName,
            productExplain = reqDto.productExplain,
            productPrice = reqDto.productPrice,
            productCount = reqDto.productCount,
            productImage = reqDto.productImage,
            createDate = LocalDateTime.now().toString()
        )

        productRepository.save(entity)
            .awaitSingle()
            .run { "성공했습니다." }

    }
}