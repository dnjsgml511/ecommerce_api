package com.ecommerce.api.ecommerce.controller

import com.ecommerce.api.ecommerce.dto.req.AddAddressReqDto
import com.ecommerce.api.ecommerce.entity.Address
import com.ecommerce.api.ecommerce.framework.response.ResponseDto
import com.ecommerce.api.ecommerce.framework.response.ResponseModel
import com.ecommerce.api.ecommerce.service.user.AddressService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class AddressController(
        val addressService: AddressService,
): ResponseModel() {

    @GetMapping("/addresses")
    suspend fun getAddressList(
        @RequestAttribute memberNo: Int
    ): ResponseDto<List<Address>> {
        return addressService.getUserAddressList(userNo = memberNo)
                .responseMapping()
    }

    @GetMapping("/address/{id}")
    suspend fun getAddress(
        @RequestAttribute memberNo: Int,
        @PathVariable id: Int,
    ): ResponseDto<Address> {
        return addressService.getUserAddress(userNo = memberNo, addressNo = id)
                .responseMapping()
    }

    @PostMapping("/address")
    suspend fun addAddress(
        @RequestAttribute memberNo: Int,
        @RequestBody addAddressReqDto: AddAddressReqDto
    ): ResponseDto<Address> {
        return addressService.addAddress(userNo = memberNo, reqDto = addAddressReqDto)
                .responseMapping()
    }

    @PutMapping("/address/{id}")
    suspend fun updateAddress(
        @RequestAttribute memberNo: Int,
        @RequestBody addAddressReqDto: AddAddressReqDto,
        @PathVariable id: Int,
    ): ResponseDto<Address> {
        return addressService.updateAddress(userNo = memberNo, reqDto = addAddressReqDto, addressNo = id)
                .responseMapping()
    }

    @DeleteMapping("/address/{id}")
    suspend fun deleteAddress(
        @RequestAttribute memberNo: Int,
        @PathVariable id: Int,
    ): ResponseDto<Void?> {
        return addressService.deleteAddress(userNo = memberNo, addressNo = id)
                .responseMapping()
    }



}