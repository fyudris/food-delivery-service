package com.deliveryhero.clone.user_service.domain

import jakarta.persistence.*

/**
 * Represents a user entity in the system.
 *
 * This entity is mapped to the "users" table in the database and includes fields
 * for identifying a user, along with their login credentials.
 *
 * @property id The unique identifier for the user. It is auto-generated.
 * @property username The username of the user used for identification.
 * @property email The email address associated with the user.
 * @property password The user's password, which is stored in a hashed format.
 */
@Entity // Map class to a database table. Each instance of this class corresponds to a row in table.
@Table(name = "users") // Explicit definition of the table name
data class User (
    @Id // Mark a field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Defines how the value for the primary key is generated
    val id: Long = 0,

    val username: String = "",
    val email: String = "",
    val password: String = ""
)