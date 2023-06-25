package com.ecommerce.api.ecommerce.service.member.component

import org.mindrot.jbcrypt.BCrypt
import org.springframework.stereotype.Component

@Component
class Encryption {
    fun encryption(password: String): String{
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }
    fun checkPassword(inputPassword: String, password: String): Boolean {
        return BCrypt.checkpw(inputPassword, password)
    }
}