package com.ecommerce.api.ecommerce.framework.config.security

import mu.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@EnableWebFluxSecurity
class SecurityConfig {

    val log = KotlinLogging.logger {  }

    @Bean
    fun securityWebFilterChain(
        http: ServerHttpSecurity,
    ): SecurityWebFilterChain {

        log.info { "Test..." }
        log.info { "wow" }

        http
                .cors()
                .and()
                .csrf().disable()
                .formLogin().disable()  //폼로그인 안쓰겠다
                .httpBasic().disable()  //기본 인증방식 사용 X
                .authorizeExchange().pathMatchers("/**").permitAll().and()
                .addFilterBefore(JwtFilter(), SecurityWebFiltersOrder.HTTP_BASIC)

        return http.build()

//                .csrf().disable()
//                .formLogin().disable()
//                .httpBasic().disable()
//                .csrf().disable()
//                .logout().disable()
//                .authorizeExchange()
//                .pathMatchers("/**").permitAll()
//                .and()
//                .addFilterAt(filter, SecurityWebFiltersOrder.AUTHENTICATION)
//                .authorizeExchange().anyExchange().authenticated().and()
//                .build()
    }
}