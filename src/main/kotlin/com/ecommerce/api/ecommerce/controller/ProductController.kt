package com.ecommerce.api.ecommerce.controller

import com.ecommerce.api.ecommerce.dto.req.SaveProductReqDto
import com.ecommerce.api.ecommerce.dto.res.ProductDetailResDto
import com.ecommerce.api.ecommerce.entity.Product
import com.ecommerce.api.ecommerce.framework.response.ResponseDto
import com.ecommerce.api.ecommerce.framework.response.ResponseModel
import com.ecommerce.api.ecommerce.service.product.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product")
class ProductController(
        private val productService: ProductService,
) : ResponseModel(){

    @GetMapping("/list")
    suspend fun productList(
        @RequestParam(value = "productName", defaultValue = "") productName: String
    ): ResponseDto<List<Product>> {
        return productService.getProductList(productName = productName).responseMapping()
    }

    @GetMapping("/detail")
    suspend fun productDetail(
        @RequestParam(value = "productNo", defaultValue = "") productNo: Int
    ): ResponseDto<ProductDetailResDto?> {
        return productService.getProductDetail(productNo = productNo).responseMapping()
    }

    @PostMapping("/save")
    suspend fun saveProduct(
        @RequestBody saveProductReqDto: SaveProductReqDto
    ): ResponseDto<String> {
        return productService.saveProduct(reqDto = saveProductReqDto, sellerNo = 1).responseMapping()
    }
}