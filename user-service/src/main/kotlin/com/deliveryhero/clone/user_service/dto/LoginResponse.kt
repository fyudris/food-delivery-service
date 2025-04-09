package com.deliveryhero.clone.user_service.dto

data class LoginResponse(
    val id: Long,
    val username: String,
    val email: String,
    val message: String = "Login Successful",
    val token: String
)
