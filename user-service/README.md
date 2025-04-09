# 🧑‍💻 My Backend Learning Journey: Building a Secure User Microservice with Spring Boot, Kotlin & JWT

---

## 📌 Introduction

As someone diving into backend development, I set out to build a **user microservice** for a food delivery system. My goals were simple:

- Learn how backend APIs work

- Store and authenticate users securely

- Understand modern backend tech like **Spring Boot**, **PostgreSQL**, **Docker**, and **JWT**


Spoiler alert: I not only learned how it all works — I built it from scratch, debugged real-world issues, and came out much more confident as a backend developer 💪

This blog post walks you through what I learned, how I structured things, and what each part means (especially if you're a beginner like me).

---

## 🔨 Tools I Used

- **Kotlin** (clean and expressive syntax)

- **Spring Boot** (for rapid backend development)

- **PostgreSQL** (as the database)

- **Docker** (to run services in containers)

- **JWT** (for stateless authentication)

- **Postman** (for testing the APIs)


---

## 🧠 Understanding Microservices

Before anything, I had to learn what a microservice is.

> 🧩 A **microservice** is a small, focused application that handles one job — like user management, ordering, or payments — and communicates with other services over the network.

So for a food delivery system, we might have:

- `user-service`

- `menu-service`

- `order-service`

- etc.


I started by building the **user-service**.

---

## 📁 Project Structure

Here’s how I organized my folders and files:

```
user-service/
│
├── controller/     # API endpoints
├── domain/         # User entity/model
├── repository/     # Database access
├── dto/            # Data Transfer Objects (input/output)
├── util/           # JWT utility
├── config/         # Spring Security config
└── application.properties  # DB config

```

Each layer had a clear responsibility. This was a huge mindset shift for me — understanding **separation of concerns** made everything easier to debug and test.

---

## 🧰 PostgreSQL: My First Real Database

I used Docker to spin up PostgreSQL:

```
docker run --name food-postgres -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres:15
```
Later, I switched to a local Postgres installation via Postgres.app for better integration with IntelliJ.

In the `application.properties`, I configured my DB connection:

properties

CopyEdit
```
spring.datasource.url=jdbc:postgresql://localhost:5432/fooddb spring.datasource.username=postgres
spring.datasource.password=password
```


---

## 👥 User Registration

I created a POST endpoint to register users:



```
@PostMapping("/api/users")
fun createUser(@RequestBody request: CreateUserRequest): ResponseEntity<UserResponse>
```

**Security tip**: I used **BCrypt** to hash passwords before storing them.

---

## 🔐 JWT Authentication (Login)

This was the most exciting part! I learned how JWT (JSON Web Tokens) work and how to use them in Spring Boot.

### What happens at login:

1. User sends email and password.

2. If correct → I generate a JWT token with `JwtUtil`.

3. The token is returned and must be included in future requests.



`val token = jwtUtil.generateToken(user.email)`

### What happens on protected routes:

- A custom `JwtAuthenticationFilter` checks the `Authorization` header.

- If the token is valid, Spring sets the security context.


---

## 🛡️ SecurityConfig with Spring Security

I customized `SecurityConfig` so some endpoints are public and others require a valid JWT:

```
http
    .csrf { it.disable() }
    .authorizeHttpRequests {
        it.requestMatchers("/api/users/login", "/api/users").permitAll()
        it.anyRequest().authenticated()
    }
    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

```

This means:

- `/api/users` and `/login` → Public

- All other endpoints → Require a valid token


---

## ✅ Testing With Postman

I used Postman to:

4. Register a user

5. Log in to get a token

6. Access protected routes with `Bearer <token>` in the header


Seeing that 200 OK response with my user data felt like pure victory 😄

---

## 💥 Bugs I Faced (and Fixed)

- **Port conflicts**: When using Docker and IntelliJ together.

- **Entity errors**: Hibernate required a no-arg constructor.

- **Password auth failures**: Wrong password or wrong user setup.

- **Database errors**: Wrong DB name or missing tables.


Each of these helped me learn how to read stack traces, dig into logs, and appreciate the power of Spring Boot's error handling.

---

## 💡 What I Learned

- How to structure a clean backend project.

- The value of DTOs for separating input/output.

- How to secure endpoints with JWT and Spring Security.

- Basic but real-world use of Docker + Postgres.

- How to test APIs using Postman.

- How microservices can be developed and run independently.


---

## 🛣️ What’s Next?

Here’s what I want to build next:

-  `menu-service`: Manage dishes, categories, prices

-  `order-service`: Handle order creation and payment

-  API Gateway + Service Registry

-  Docker Compose for multi-service orchestration

-  Role-based security (`USER`, `ADMIN`)


---

## ✨ Final Thoughts

Building this user-service was a massive leap in my backend journey. The best part? I now **understand** what’s happening under the hood — not just copying tutorials.

> If you’re just starting: don’t worry if it feels confusing. Keep building, break things, and read those stack traces like a detective.

Happy coding! 🚀

## ✅ What You Already Have

| Feature                              | Description                                          |
| ------------------------------------ | ---------------------------------------------------- |
| 🧑‍💻 **User Registration**          | New users can register via `/api/users`              |
| 🔐 **Password Hashing**              | Passwords are securely hashed with `BCrypt`          |
| 🗝️ **Login**                        | Users can log in and receive a **JWT token**         |
| 📜 **JWT Authentication**            | Protected endpoints require valid JWT                |
| 🔒 **Spring Security**               | JWT filter + custom `SecurityConfig`                 |
| 🧪 **Tested via Postman**            | Confirmed end-to-end with real requests              |
| 📦 **DTO Layer**                     | Clean separation between data and transport          |
| 🗃️ **PostgreSQL**                   | Working with real DB, either local or Docker         |
| 🛠️ **Project Structured by Layers** | `controller`, `repository`, `domain`, `config`, etc. |
## 🧩 Optional Extras You _Could_ Add (Nice-to-Haves)

|Feature|Why Add It|
|---|---|
|✉️ **Email Uniqueness Validation**|Prevent duplicate accounts|
|👤 **User Roles** (`ADMIN`, `USER`)|For future authorization features|
|🔄 **Refresh Tokens**|So users don’t log in again every hour|
|👁️ **User Profile Endpoint**|`/api/users/me` to get current user info from JWT|
|🔐 **JWT Expiry Handling**|Custom error when token expires|
|🧪 **Unit & Integration Tests**|Boost reliability, especially when adding more services|
|💾 **Persistence on App Restart**|Use volume or init script if using Docker|
|📄 **OpenAPI/Swagger Docs**|For better developer experience|
