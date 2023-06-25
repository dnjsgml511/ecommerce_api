package com.ecommerce.api.ecommerce.service.token

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Component
class Token {

    val key = Keys.hmacShaKeyFor("rkGU45258GGhiolLO2465TFY5345kGU45258GGhiolLO2465TFY5345".toByteArray(StandardCharsets.UTF_8))

    fun createToken(memberNo: Int): String{
        return Jwts.builder()
            .setSubject(memberNo.toString())
            .setIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.of("Asia/Seoul")).toInstant()))
            .setExpiration(Date.from(LocalDateTime.now().plusHours(2).atZone(ZoneId.of("Asia/Seoul")).toInstant()))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun validateToken(token: String){
        Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor("{secret-key}".toByteArray(StandardCharsets.UTF_8)))
            .build()
            .parseClaimsJws(token)
    }
}