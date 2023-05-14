package com.ecommerce.api.ecommerce.framework.util.mail

import jakarta.mail.MessagingException
import jakarta.mail.internet.MimeMessage
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.FileSystemResource
import org.springframework.mail.MailAuthenticationException
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import org.thymeleaf.context.Context
import java.io.IOException
import java.io.UnsupportedEncodingException


@Component
class MailManager(private val sender: JavaMailSender, thymeleafTemplateMapper: ThymeleafTemplateMapper) {
    private val message: MimeMessage
    private val messageHelper: MimeMessageHelper
    private val thymeleafTemplateMapper: ThymeleafTemplateMapper

    // 생성자
    init {
        message = sender.createMimeMessage()
        messageHelper = MimeMessageHelper(message, true, "UTF-8")
        this.thymeleafTemplateMapper = thymeleafTemplateMapper
    }

    // 보내는 사람 이메일
    @Throws(MessagingException::class)
    fun setFrom(fromAddress: String?) {
        messageHelper.setFrom(fromAddress!!)
    }

    // 보내는 사람 이름
    @Throws(MessagingException::class, UnsupportedEncodingException::class)
    fun setFromName(fromAddress: String?, fromName: String?) {
        messageHelper.setFrom(fromAddress!!, fromName!!)
    }

    // 받는 사람 이메일
    @Throws(MessagingException::class)
    fun setTo(email: String?) {
        messageHelper.setTo(email!!)
    }

    // 제목
    @Throws(MessagingException::class)
    fun setSubject(subject: String?) {
        messageHelper.setSubject(subject!!)
    }

    // 메일 내용
    @Throws(MessagingException::class)
    fun setText(text: String?, useHtml: Boolean) {
        messageHelper.setText(text!!, useHtml)
    }

    // 메일 내용
    @Throws(MessagingException::class)
    fun setThymeleafText(context: Context?, fileName: String?, useHtml: Boolean) {
        val resultText: String = thymeleafTemplateMapper.parse(context, fileName)
        println(resultText)
        messageHelper.setText(resultText, useHtml)
    }

    // 첨부 파일
    @Throws(MessagingException::class, IOException::class)
    fun setAttach(displayFileName: String?, pathToAttachment: String?) {
        val file = ClassPathResource(pathToAttachment!!).file
        val fsr = FileSystemResource(file)
        messageHelper.addAttachment(displayFileName!!, fsr)
    }

    // 이미지 삽입
    @Throws(MessagingException::class, IOException::class)
    fun setInline(contentId: String?, pathToInline: String?) {
        val file = ClassPathResource(pathToInline!!).file
        val fsr = FileSystemResource(file)
        messageHelper.addInline(contentId!!, fsr)
    }

    // 발송
    fun send() {
        try {
            sender.send(message)
        } catch (e: MailAuthenticationException) {
            e.printStackTrace()
            throw IllegalArgumentException("계정 인증 실패")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}