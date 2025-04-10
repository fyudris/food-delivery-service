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
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    var username: String = "",

    @Column(unique = true, nullable = false)
    var email: String = "",

    @Column(nullable = false)
    var password: String = ""
)