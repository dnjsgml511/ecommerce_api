package com.ecommerce.api.ecommerce.service

import com.ecommerce.api.ecommerce.dto.req.SaveProductReqDto
import com.ecommerce.api.ecommerce.dto.res.ProductDetailResDto
import com.ecommerce.api.ecommerce.entity.Product
import com.ecommerce.api.ecommerce.repository.r2dbc.ProductRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ProductService(
    private val productRepository: ProductRepository
) {

    suspend fun getProductList(productName: String): List<Product> = coroutineScope{
        val wildCardProductName = "%${productName}%"
        val sort = Sort.by(Sort.Direction.DESC, "productNo")
        productRepository.findByProductNameLike(productName = wildCardProductName, sort)
            .toStream().toList()
    }

    suspend fun getProductDetail(productNo: Int): ProductDetailResDto? = coroutineScope{
        val productData = productRepository.findById(productNo).awaitSingleOrNull()

        ProductDetailResDto(
            productNo = productData?.productNo ?: 0,
            productName = productData?.productName ?: "",
            productExplain = productData?.productExplain ?: "",
            productPrice = productData?.productPrice ?: 0,
            productCount = productData?.productCount ?: 0,
            productImage = "https://ibb.co/s2xWW50",
            sellerNo = productData?.sellerNo ?: 0,
            sellerName = "판매자1",
        )
    }

    suspend fun saveProduct(reqDto: SaveProductReqDto, sellerNo: Int): String = coroutineScope{

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
            .run { "success" }

    }
}