package com.ecommerce.api.ecommerce.controller

import com.ecommerce.api.ecommerce.dto.req.SaveBasketReqDto
import com.ecommerce.api.ecommerce.entity.Product
import com.ecommerce.api.ecommerce.framework.response.ResponseDto
import com.ecommerce.api.ecommerce.framework.response.ResponseModel
import com.ecommerce.api.ecommerce.service.BasketService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/basket")
class BasketController(
        private val basketService: BasketService,
): ResponseModel() {

    @PostMapping("/save")
    suspend fun saveBasket(
        @RequestBody saveBasketReqDto: SaveBasketReqDto
    ): ResponseDto<String> {
        return basketService.saveBasket(reqDto = saveBasketReqDto, memberNo = 1)
            .responseMapping()
    }

    @GetMapping("/list")
    suspend fun getBasketList(): ResponseDto<List<Product>> {
        return basketService.getBasket(memberNo = 1).responseMapping()
    }

}