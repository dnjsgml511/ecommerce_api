package com.ecommerce.api.ecommerce.service.member.component

import org.springframework.stereotype.Component
import java.util.regex.Pattern

@Component
class Validation {
    fun id(id: String): Boolean{
        val regex = "^[a-zA-Z]{1}[a-zA-Z0-9]{4,11}$"
        return Pattern.matches(regex, id)
    }

    fun password(password: String): Boolean{
        val regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d~!@#\$%^&*()+|=]{8,20}\$"
        return Pattern.matches(regex, password)
    }

    fun email(email: String): Boolean{
        val regex = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}\$"
        return Pattern.matches(regex, email)
    }
}