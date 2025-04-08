package com.deliveryhero.clone.food_delivery_service.repository

import com.deliveryhero.clone.food_delivery_service.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Gives ready-to-use methods like save(), findAll(), findById(), etc.
 */
@Repository
interface UserRepository : JpaRepository<User, Long>