package com.ecommerce.api.ecommerce.service.mail

import com.ecommerce.api.ecommerce.framework.util.mail.MessageService
import org.springframework.stereotype.Component

@Component
class SendEmail(
    private val messageService: MessageService
) {

    fun welcomeMail(email: String, name: String){
        messageService.mailSend(
            to = email,
            subject = "환영합니다~",
            text = "안녕하세요 ${name}님 환영합니다.",
            fileName = "mail.html"
        )
    }

    fun findId(email: String, id: String){
        messageService.mailSend(
            to = email,
            subject = "아이디 찾기",
            text = "아이디 : $id",
            fileName = "mail.html"
        )
    }

    fun findPassword(email: String, password: String){
        messageService.mailSend(
            to = email,
            subject = "새로운 비밀번호",
            text = "비밀번호 : $password",
            fileName = "mail.html"
        )
    }
}