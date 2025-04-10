package com.deliveryhero.clone.user_service.config

import com.deliveryhero.clone.user_service.repository.UserRepository
import com.deliveryhero.clone.user_service.util.JwtUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

@Component
class JwtAuthenticationFilter(
    private val jwtUtil: JwtUtil,
    private val userRepository: UserRepository
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            val token = authHeader.substring(7)
            val email = jwtUtil.validateToken(token)

            if (email != null && SecurityContextHolder.getContext().authentication == null) {
                val user = userRepository.findByEmail(email)

                if (user != null) {
                    val authToken = UsernamePasswordAuthenticationToken(email, null, emptyList())
                    authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                    SecurityContextHolder.getContext().authentication = authToken
                } else {
                    // 🛑 User not found = token should not be valid
                    response.status = HttpServletResponse.SC_UNAUTHORIZED
                    response.writer.write("User no longer exists.")
                    return
                }
            }
        }

        filterChain.doFilter(request, response)
    }
}

