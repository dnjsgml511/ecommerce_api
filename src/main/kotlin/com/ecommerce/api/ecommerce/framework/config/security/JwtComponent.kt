//package com.ecommerce.api.ecommerce.framework.config.security
//
//import io.jsonwebtoken.Claims
//import io.jsonwebtoken.Jws
//import io.jsonwebtoken.Jwts
//import io.jsonwebtoken.SignatureAlgorithm
//import io.jsonwebtoken.security.Keys
//import jakarta.servlet.http.HttpServletRequest
//import org.springframework.http.server.reactive.ServerHttpRequest
//import org.springframework.stereotype.Component
//import java.nio.charset.StandardCharsets
//import java.time.LocalDateTime
//import java.time.ZoneId
//import java.util.*
//
//
//// https://jsonobject.tistory.com/581
//
//@Component
//class JwtComponent {
//    val secretKey: String = "236979CB6F1AD6B6A6184A31E6BE37DB3818CC36871E26235DD67DCFE4041492"
//
//    fun createToken(memberNo: Int): String{
//        val key = Keys.hmacShaKeyFor(secretKey.toByteArray(StandardCharsets.UTF_8))
//
//        return Jwts.builder()
//            // 회원 식별 문자열 저장 (sub)
//            .setSubject(memberNo.toString())
//            // 토큰 발급 일시 저장 (iat)
//            .setIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.of("Asia/Seoul")).toInstant()))
//            // 토큰 만료 일시 저장 (exp)
//            .setExpiration(Date.from(LocalDateTime.now().plusHours(2).atZone(ZoneId.of("Asia/Seoul")).toInstant()))
//            // 시그너쳐 알고리즘 지정
//            .signWith(key, SignatureAlgorithm.HS256)
//            .compact()
//    }
//
//    fun resolveToken(request: HttpServletRequest): String{
//        return request.getHeader("authorization")
////        return request.headers["authorization"]?.get(0)?.replace("Bearer ", "") ?: ""
//    }
//
//    fun popData(token: String, pop: String): String {
//        return Jwts.claims()[pop].toString()
//    }
//
//    fun validateToken(jwt: String): Boolean{
//        val jwtObject = Jwts.parserBuilder()
//            .setSigningKey(Keys.hmacShaKeyFor(secretKey.toByteArray(StandardCharsets.UTF_8)))
//            .build()
//            .parseClaimsJws(jwt)
//
//        return true
//    }
//}
