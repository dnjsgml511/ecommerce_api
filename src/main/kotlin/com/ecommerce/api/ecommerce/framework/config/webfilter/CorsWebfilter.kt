package com.ecommerce.api.ecommerce.framework.config.webfilter

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Component
import org.springframework.web.cors.reactive.CorsUtils
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono


@Component
class CorsWebfilter: WebFilter {
    private val ALL = "*"
    private val MAX_AGE = "18000L"

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val request: ServerHttpRequest = exchange.request
        val path: String = request.getPath().value()
        val response: ServerHttpResponse = exchange.response
        if ("/favicon.ico" == path) {
            response.setStatusCode(HttpStatus.OK)
            return Mono.empty()
        }

        if (!CorsUtils.isCorsRequest(request)) {
            return chain.filter(exchange)
        }

        val requestHeaders: HttpHeaders = request.headers
        val requestMethod: HttpMethod? = requestHeaders.accessControlRequestMethod
        val headers: HttpHeaders = response.headers
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, requestHeaders.origin)
        headers.addAll(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, requestHeaders.accessControlRequestHeaders)

        if (requestMethod != null) {
            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, requestMethod.name())
        }

        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true")
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, ALL)
        headers.add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, MAX_AGE)

        if (request.method === HttpMethod.OPTIONS) {
            response.setStatusCode(HttpStatus.OK)
            return Mono.empty()
        }
        return chain.filter(exchange)
    }
}