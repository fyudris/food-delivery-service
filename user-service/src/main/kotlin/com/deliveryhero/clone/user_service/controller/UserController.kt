package com.deliveryhero.clone.user_service.controller

import com.deliveryhero.clone.user_service.domain.User
import com.deliveryhero.clone.user_service.repository.UserRepository
import com.deliveryhero.clone.user_service.dto.CreateUserRequest
import com.deliveryhero.clone.user_service.dto.LoginRequest
import com.deliveryhero.clone.user_service.dto.LoginResponse
import com.deliveryhero.clone.user_service.dto.UserResponse
import com.deliveryhero.clone.user_service.dto.UpdateUserRequest
import com.deliveryhero.clone.user_service.util.JwtUtil
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication

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
    fun createUser(@RequestBody request: CreateUserRequest): ResponseEntity<Any> {
        // Check for duplicate email
        if (userRepository.findByEmail(request.email) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists.")
        }

        val hashedPassword = passwordEncoder.encode(request.password)
        val user = User(
            username = request.username,
            email = request.email,
            password = hashedPassword
        )
        val savedUser = userRepository.save(user)
        return ResponseEntity.ok(UserResponse(savedUser.id, savedUser.username, savedUser.email))
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping
    fun getAllUsers(): List<User> = userRepository.findAll()

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<Any> {
        println("LOGIN ENDPOINT HIT")
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

    private fun getAuthorizedUser(id: Long, auth: Authentication): User? {
        val user = userRepository.findById(id).orElse(null) ?: return null
        return if (user.email == auth.name) user else null
    }

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @RequestBody request: UpdateUserRequest,
        authentication: Authentication
    ): ResponseEntity<*> {
        val user = getAuthorizedUser(id, authentication)
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized or not found")

        val updatedUser = user.copy(
            username = request.username ?: user.username,
            email = request.email ?: user.email
        )
        userRepository.save(updatedUser)
        return ResponseEntity.ok(UserResponse(updatedUser.id, updatedUser.username, updatedUser.email))
    }

    @DeleteMapping("/{id}")
    fun deleteUser(
        @PathVariable id: Long,
        authentication: Authentication
    ): ResponseEntity<*> {
        val user = getAuthorizedUser(id, authentication)
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized or not found")

        userRepository.delete(user)
        return ResponseEntity.ok("User deleted successfully.")
    }
}

data class CreateUserRequest(
    val username: String,
    val email: String,
    val password: String
)


