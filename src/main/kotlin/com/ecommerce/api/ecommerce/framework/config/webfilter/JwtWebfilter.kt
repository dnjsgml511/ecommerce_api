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

        if(!checkUrl){
            setMemberNo(exchange)
        }

        return chain.filter(exchange)
    }

    private fun checkUrl(request: ServerHttpRequest): Boolean{
        return ALLOWED_PATHS.contains(request.uri.path)
    }

    private fun setMemberNo(exchange: ServerWebExchange){
        val jwt = exchange.request.headers[HttpHeaders.AUTHORIZATION]?.get(0)?.replace("Bearer", "") ?: ""
        println("jwt: $jwt")
        val memberNo = token.getMemberNo(jwt)
        exchange.attributes["memberNo"] = memberNo
    }
}