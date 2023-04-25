package com.ecommerce.api.ecommerce.service.impl

import com.ecommerce.api.ecommerce.dto.req.SaveProductReqDto
import com.ecommerce.api.ecommerce.entity.Product
import com.ecommerce.api.ecommerce.repository.ProductRepository
import com.ecommerce.api.ecommerce.service.service.ProductService
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository
): ProductService {

    override suspend fun getProductList(productName: String): List<Product> = coroutineScope{
        val wildCardProductName = "%${productName}%"
        productRepository.findByProductNameLike(productName = wildCardProductName)
            .toStream().toList()
    }

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