package com.ecommerce.api.ecommerce.service.product

import com.ecommerce.api.ecommerce.dto.req.SaveProductReqDto
import com.ecommerce.api.ecommerce.dto.res.ProductDetailResDto
import com.ecommerce.api.ecommerce.entity.Product
import com.ecommerce.api.ecommerce.repository.r2dbc.ProductRepository
import com.ecommerce.api.ecommerce.service.seller.getSeller
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val getSeller: getSeller,
) {

    suspend fun getProductList(productName: String): List<Product> = coroutineScope{
        val wildCardProductName = "%${productName}%"
        val sort = Sort.by(Sort.Direction.DESC, "productNo")
        productRepository.findByProductNameLike(productName = wildCardProductName, sort)
            .toStream().toList()
    }

    suspend fun getProductDetail(productNo: Int): ProductDetailResDto? = coroutineScope{
        val productData = productRepository.findById(productNo).awaitSingleOrNull()
                ?: throw Exception("상품 번호 확인")

        val seller = getSeller.getSeller(productData.sellerNo)
                ?: throw Exception("판매자 번호 확인")

        ProductDetailResDto(product = productData, seller = seller)
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