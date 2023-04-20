package com.ecommerce.api.ecommerce.controller

import com.ecommerce.api.ecommerce.dto.req.SaveProductReqDto
import com.ecommerce.api.ecommerce.dto.req.SearchProductReqDto
import com.ecommerce.api.ecommerce.entity.Product
import com.ecommerce.api.ecommerce.framework.response.ResponseDto
import com.ecommerce.api.ecommerce.framework.response.ResponseModel
import com.ecommerce.api.ecommerce.service.impl.GetProductServiceImpl
import com.ecommerce.api.ecommerce.service.impl.SaveProductServiceImpl
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
        private val productService: GetProductServiceImpl,
        private val saveProductServiceImpl: SaveProductServiceImpl,
) : ResponseModel(){

    @GetMapping("/products")
    suspend fun productList(
        @RequestParam searchProductReqDto: SearchProductReqDto
    ): ResponseDto<List<Product>> {
        return productService.searchProduct(reqDto = searchProductReqDto).responseMapping()
    }

    @PostMapping("/product")
    fun saveProduct(
        @RequestBody saveProductReqDto: SaveProductReqDto
    ): ResponseDto<String> {
        return saveProductServiceImpl.saveProduct(reqDto = saveProductReqDto).responseMapping()
    }
}