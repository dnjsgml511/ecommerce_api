package com.ecommerce.api.ecommerce.framework.config.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

// https://jsonobject.tistory.com/581

@Component
class JwtComponent {
    val secretKey: String = "leewonhee"

    fun crateToken(memberId: String): String{
        val key = Keys.hmacShaKeyFor(secretKey.toByteArray(StandardCharsets.UTF_8))

        return Jwts.builder()
            // 회원 식별 문자열 저장 (sub)
            .setSubject(memberId)
            // 토큰 발급 일시 저장 (iat)
            .setIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.of("Asia/Seoul")).toInstant()))
            // 토큰 만료 일시 저장 (exp)
            .setExpiration(Date.from(LocalDateTime.now().plusHours(2).atZone(ZoneId.of("Asia/Seoul")).toInstant()))
            // 시그너쳐 알고리즘 지정
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()

    }

    fun validateToken(jwt: String): Boolean{
        val jwtObject = Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor("{secret-key}".toByteArray(StandardCharsets.UTF_8)))
            .build()
            .parseClaimsJws(jwt)

        return true
    }
}