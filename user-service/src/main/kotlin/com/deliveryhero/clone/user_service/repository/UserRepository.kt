package com.deliveryhero.clone.user_service.repository

import com.deliveryhero.clone.user_service.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Gives ready-to-use methods like save(), findAll(), findById(), etc. by extending JpaRepository.
 */
@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
}