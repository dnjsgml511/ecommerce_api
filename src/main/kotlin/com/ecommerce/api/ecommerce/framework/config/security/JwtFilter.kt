package com.ecommerce.api.ecommerce.framework.config.security

import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono


@Component
class JwtFilter : WebFilter {

    val log = KotlinLogging.logger {  }

    override fun filter(
        serverWebExchange: ServerWebExchange,
        webFilterChain: WebFilterChain
    ): Mono<Void> {
        log.info { "Test.." }
        println("Test...")
        return webFilterChain.filter(serverWebExchange)
    }
}
