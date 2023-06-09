package com.ecommerce.api.ecommerce.framework.config.webfilter

import com.ecommerce.api.ecommerce.service.token.Token
import io.reactivex.rxjava3.internal.util.QueueDrainHelper.request
import org.springframework.http.HttpHeaders
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono
import java.util.*


@Component
class JwtWebfilter(
    private val token: Token
): WebFilter {

    private val ALLOWED_PATHS: List<String> = listOf(
        "/health",
        "/favicon.ico",

        "/member/signup",
        "/member/signin",
        "/password/update",
        "/password/find",
        "/id/update",
        "/id/find",
        "/email/update",
    )

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val checkUrl = checkUrl(exchange.request)
        val checkMethod = checkOptions(exchange.request)

        if(checkUrl) return chain.filter(exchange)
        if(checkMethod) return chain.filter(exchange)

        setMemberNo(exchange)
        return chain.filter(exchange)
    }

    private fun checkUrl(request: ServerHttpRequest): Boolean{
        println("Path: ${request.uri.path} -> ${ALLOWED_PATHS.contains(request.uri.path)}")
        return ALLOWED_PATHS.contains(request.uri.path)
    }

    private fun checkOptions(request: ServerHttpRequest): Boolean{
        println("Method : ${request.method.name()} -> ${request.method.name() == "OPTIONS"}")
        return request.method.name() == "OPTIONS"
    }

    private fun setMemberNo(exchange: ServerWebExchange){
        val jwt = exchange.request.headers[HttpHeaders.AUTHORIZATION.lowercase(Locale.getDefault())]?.get(0)?.replace("Bearer", "") ?: ""
        println("Token : $jwt")
        val memberNo = token.getMemberNo(jwt)
        exchange.attributes["memberNo"] = memberNo
    }
}