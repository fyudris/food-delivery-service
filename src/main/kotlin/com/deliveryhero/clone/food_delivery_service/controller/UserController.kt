package com.deliveryhero.clone.food_delivery_service.controller

import com.deliveryhero.clone.food_delivery_service.domain.User
import com.deliveryhero.clone.food_delivery_service.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController (private val userRepository: UserRepository){
    @PostMapping
    fun createUser(@RequestBody request: CreateUserRequest): ResponseEntity<User> {
        val user = User(
            username = request.username,
            email = request.email,
            password = request.password // will hash later
        )
        return ResponseEntity.ok(userRepository.save(user))
    }

    @GetMapping
    fun getAllUsers(): List<User> = userRepository.findAll()
}

data class CreateUserRequest(
    val username: String,
    val email: String,
    val password: String
)