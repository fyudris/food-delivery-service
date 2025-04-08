package com.deliveryhero.clone.food_delivery_service.domain

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val username: String,
    val email: String,
    val password: String
)