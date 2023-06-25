package com.ecommerce.api.ecommerce.service.member.component

import org.springframework.stereotype.Component

@Component
class CheckMember(
        private val duplication: Duplication,
        private val validation: Validation,
) {

    suspend fun checkId(id: String): Boolean{
        if(!duplication.id(id)) return false
        return validation.id(id)
    }

    fun checkPassword(password: String): Boolean{
        return validation.password(password)
    }

    suspend fun checkEmail(email: String): Boolean{
        if(!duplication.email(email)) return false
        return validation.email(email)
    }
}