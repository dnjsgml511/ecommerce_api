package com.ecommerce.api.ecommerce.service.user

import com.ecommerce.api.ecommerce.dto.req.AddAddressReqDto
import com.ecommerce.api.ecommerce.entity.Address
import com.ecommerce.api.ecommerce.repository.r2dbc.AddressRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.stereotype.Service

@Service
class AddressService(
    val addressRepository: AddressRepository,
) {

    // 주소 목록 가져오기
    suspend fun getUserAddressList(userNo: Int): List<Address> = coroutineScope{
        addressRepository.findByUserNo(userNo).asFlow().toList()
    }

    // 주소 가져오기
    suspend fun getUserAddress(userNo: Int, addressNo: Int): Address = coroutineScope{
        addressRepository.findByUserNoAndAddressNo(userNo = userNo, addressNo = addressNo).awaitSingleOrNull()
                ?: throw Exception("주소 번호 확인")
    }

    // 주소 추가
    suspend fun addAddress(userNo: Int, reqDto: AddAddressReqDto): Address = coroutineScope{

        validationAddress(address = reqDto.address, addressDetail = reqDto.addressDetail, userNo = userNo)

        val entity = Address(
            label = reqDto.label,
            address = reqDto.address,
            addressDetail = reqDto.addressDetail,
            userNo = userNo,
        )
        addressRepository.save(entity).awaitSingle()
    }

    // 주소 업데이트
    suspend fun updateAddress(userNo: Int, reqDto: AddAddressReqDto, addressNo: Int): Address = coroutineScope{

        validationAddress(address = reqDto.address, addressDetail = reqDto.addressDetail, userNo = userNo)

        val addressData = addressRepository.findByUserNoAndAddressNo(userNo = userNo, addressNo = addressNo)
                .awaitSingleOrNull() ?: throw Exception("addressNo 확인")

        val entity = addressData.copy(
            label = reqDto.label,
            address = reqDto.address,
            addressDetail = reqDto.addressDetail,
        )

        addressRepository.save(entity).awaitSingle()
    }

    // 주소 업데이트
    suspend fun deleteAddress(userNo: Int, addressNo: Int): Void? = coroutineScope{

        val addressData = addressRepository.findByUserNoAndAddressNo(userNo = userNo, addressNo = addressNo)
                .awaitSingleOrNull() ?: throw Exception("addressNo 확인")

        addressRepository.delete(addressData).awaitSingleOrNull()
    }

    // 주소 중복 체크
    suspend fun validationAddress(address: String, addressDetail: String, userNo: Int) = coroutineScope {
        val checkAddress = addressRepository.existsByAddressAndAddressDetailAndUserNo(
            address = address, addressDetail = addressDetail, userNo = userNo
        )

        if(checkAddress.awaitSingle()) throw Exception("주소 중복")
    }

}