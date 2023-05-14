package com.ecommerce.api.ecommerce.framework.util.mail

import com.ecommerce.api.ecommerce.dto.dto.MailRequestDto
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

@Service
class MessageService(
    private val mailManager: MailManager
) {

    @Value("\${spring.mail.username}")
    private val fromAddress: String? = null

    @Value("\${spring.mail.properties.mail.smtp.nickname}")
    private val fromName: String? = null

    @Async(value = "mailSenderExecutor")
    fun mailSendWithAsync(mailRequestDto: MailRequestDto, fileName: String) {
        send(mailRequestDto, fileName)
    }

    fun mailSend(mailRequestDto: MailRequestDto, fileName: String) {
        send(mailRequestDto, fileName)
    }
    fun mailSend(to: Array<String>, subject: String, text: String, fileName: String){
        val dto = MailRequestDto(
            to = to,
            subject = subject,
            text = text,
        )
        mailSend(dto, fileName)
    }

    private fun send(mailRequestDto: MailRequestDto, fileName: String) {
        val subject: String = mailRequestDto.subject
        val text: String = mailRequestDto.text
        val context = Context()
        context.setVariable("text", text)
        try {
            mailManager.setSubject("[원희상점] $subject")
            mailManager.setThymeleafText(context, fileName, true)
            mailManager.setFromName(fromAddress, fromName)
            for (to in mailRequestDto.to) {
                mailManager.setTo(to)
                mailManager.send()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}