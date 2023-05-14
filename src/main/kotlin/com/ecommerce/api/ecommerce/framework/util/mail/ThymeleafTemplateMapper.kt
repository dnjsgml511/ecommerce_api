package com.ecommerce.api.ecommerce.framework.util.mail

import org.springframework.stereotype.Component
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context


@Component
class ThymeleafTemplateMapper(
    val templateEngine: TemplateEngine
){
    fun parse(context: Context?, fileName: String?): String {
        return templateEngine!!.process(fileName, context)
    }
}