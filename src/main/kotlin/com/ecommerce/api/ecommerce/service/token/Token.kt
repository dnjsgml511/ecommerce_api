package com.ecommerce.api.ecommerce.service.token

import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.InvalidParameterException
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

    fun getMemberNo(token: String): Int {
        return (validTokenAndReturnBody(token)?.get("sub") ?: "0").toString().toInt()
    }

    fun validTokenAndReturnBody(token: String?): Claims? {
        return try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .body
        } catch (e: ExpiredJwtException) {
            e.printStackTrace()
            throw InvalidParameterException("유효하지 않은 토큰입니다")
        } catch (e: UnsupportedJwtException) {
            e.printStackTrace()
            throw InvalidParameterException("유효하지 않은 토큰입니다")
        } catch (e: MalformedJwtException) {
            e.printStackTrace()
            throw InvalidParameterException("유효하지 않은 토큰입니다")
        } catch (e: SignatureException) {
            e.printStackTrace()
            throw InvalidParameterException("유효하지 않은 토큰입니다")
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
            throw InvalidParameterException("유효하지 않은 토큰입니다")
        }
    }
}