package com.ecommerce.api.ecommerce.framework.util.random

import org.springframework.stereotype.Component
import java.util.*

@Component
class CreateRandomString {
    fun randomPassword(n: Int?): String{

        val characterSet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val random = Random(System.nanoTime())
        val password = StringBuilder()

        for (i in 0 until n.let { 16 })
        {
            val rIndex = random.nextInt(characterSet.length)
            password.append(characterSet[rIndex])
        }

        return password.toString()
    }
}