package com.deliveryhero.clone.user_service.dto

data class UpdateUserRequest(
    val username: String?, // nullable so users can choose what to update
    val email: String?
)
