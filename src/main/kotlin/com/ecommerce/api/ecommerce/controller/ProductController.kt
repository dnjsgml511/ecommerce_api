package com.ecommerce.api.ecommerce.controller

import com.ecommerce.api.ecommerce.dto.req.SaveProductReqDto
import com.ecommerce.api.ecommerce.entity.Product
import com.ecommerce.api.ecommerce.framework.response.ResponseDto
import com.ecommerce.api.ecommerce.framework.response.ResponseModel
import com.ecommerce.api.ecommerce.service.impl.GetProductServiceImpl
import com.ecommerce.api.ecommerce.service.impl.SaveProductServiceImpl
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product")
class ProductController(
        private val productService: GetProductServiceImpl,
        private val saveProductService: SaveProductServiceImpl,
) : ResponseModel(){

    @GetMapping("/list")
    suspend fun productList(
        @RequestParam(value = "productName", defaultValue = "") productName: String
    ): ResponseDto<List<Product>> {
        return productService.searchProduct(productName = productName).responseMapping()
    }

    @PostMapping("/save")
    suspend fun saveProduct(
        @RequestBody saveProductReqDto: SaveProductReqDto
    ): ResponseDto<String> {
        return saveProductService.saveProduct(
            reqDto = saveProductReqDto, sellerNo = 1
        ).responseMapping()
    }
}