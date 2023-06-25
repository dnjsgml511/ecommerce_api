package com.ecommerce.api.ecommerce

import com.ecommerce.api.ecommerce.service.member.component.Validation
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ValidationTest {

    @Autowired
    lateinit var validation: Validation

    @Test
    fun emptyId(){
        val id = ""
        val check = validation.id(id)
        assert(!check)
    }

    @Test
    fun shortId(){
        val id = "t"
        val check = validation.id(id)
        assert(!check)
    }

    @Test
    fun noNumId(){
        val id = "tasdasdasd"
        val check = validation.id(id)
        assert(check)
    }

    @Test
    fun noStringId(){
        val id = "1234444"
        val check = validation.id(id)
        assert(!check)
    }

    @Test
    fun success(){
        val id = "asd123"
        val check = validation.id(id)
        assert(check)
    }
}