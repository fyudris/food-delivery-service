package com.deliveryhero.clone.user_service.controller

import com.deliveryhero.clone.user_service.domain.User
import com.deliveryhero.clone.user_service.repository.UserRepository
import com.deliveryhero.clone.user_service.dto.CreateUserRequest
import com.deliveryhero.clone.user_service.dto.LoginRequest
import com.deliveryhero.clone.user_service.dto.LoginResponse
import com.deliveryhero.clone.user_service.dto.UserResponse
import com.deliveryhero.clone.user_service.util.JwtUtil
import org.springframework.http.HttpStatus

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import org.springframework.security.crypto.password.PasswordEncoder

@RestController
@RequestMapping("/api/users")
class UserController (
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JwtUtil
){
    @PostMapping
    fun createUser(@RequestBody request: CreateUserRequest): ResponseEntity<UserResponse> {
        val hashedPassword = passwordEncoder.encode(request.password)
        val user = User(
            username = request.username,
            email = request.email,
            password = hashedPassword // will hash later
        )
        val savedUser = userRepository.save(user)
        return ResponseEntity.ok(UserResponse(savedUser.id, savedUser.username, savedUser.email))
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    fun getAllUsers(): List<User> = userRepository.findAll()

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<Any> {
        val user = userRepository.findByEmail(request.email)
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials")

        return if (passwordEncoder.matches(request.password, user.password)) {
            val token = jwtUtil.generateToken(user.email)
            val response = LoginResponse(
                id = user.id,
                username = user.username,
                email = user.email,
                token = token
            )
            ResponseEntity.ok(response)
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials")
        }
    }

}

data class CreateUserRequest(
    val username: String,
    val email: String,
    val password: String
)