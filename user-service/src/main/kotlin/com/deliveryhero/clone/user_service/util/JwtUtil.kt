package com.deliveryhero.clone.user_service.util

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtUtil {

    // Ideally, load from application.properties or environment variable
    private val base64Secret = "L3YZ9a+vRz2nIjHt3L9B4iB0rruRtD1mYOunT0r6wlA=" //temp key
    private val secretKey: SecretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(base64Secret))
    private val expirationMs = 3600000 // 1 hour

    fun generateToken(username: String): String {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + expirationMs))
            .signWith(secretKey)
            .compact()
    }

    fun validateToken(token: String): String? {
        return try {
            val claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
            claims.body.subject
        } catch (e: Exception) {
            null
        }
    }
}

