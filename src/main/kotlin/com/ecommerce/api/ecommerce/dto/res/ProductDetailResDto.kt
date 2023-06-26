package com.ecommerce.api.ecommerce.dto.res

import com.ecommerce.api.ecommerce.entity.Product
import com.ecommerce.api.ecommerce.entity.Seller

data class ProductDetailResDto(
    val productNo: Int,
    val productName: String,
    val productExplain: String,
    val productPrice: Int,
    val productCount: Int,
    val productImage: String,
    val sellerNo: Int,
    val sellerName: String,
){
    constructor(product: Product, seller: Seller): this(
        productNo = product.productNo ?: 0,
        productName = product.productName,
        productExplain = product.productExplain,
        productPrice = product.productPrice,
        productCount = product.productCount,
        productImage = product.productImage ?: "",
        sellerNo = seller.sellerNo ?: 0,
        sellerName = seller.sellerName,
    )
}
