package com.ecommerce.api.ecommerce.service.member.component

import org.apache.commons.lang3.RandomStringUtils
import org.springframework.stereotype.Component

@Component
class CreateNewPassword {
    fun randomPassword(): String{
        return RandomStringUtils.randomAlphabetic(10)
    }
}