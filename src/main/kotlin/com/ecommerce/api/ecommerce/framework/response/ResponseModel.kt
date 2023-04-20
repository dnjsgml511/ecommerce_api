package com.ecommerce.api.ecommerce.framework.response

abstract class ResponseModel {
    fun <T> T.responseMapping() =
        ResponseDto(
            data = this
        )
}