# 🧑‍💻 (DRAFT) My Backend Learning Journey: Building a Secure User Microservice with Spring Boot, Kotlin & JWT


Welcome to my learning journey! 🎓 This project was built from scratch by me, a curious learner, with the goal of understanding how modern backend systems work — and especially to **learn** Kotlin, Spring Boot, REST, Microservices, Authentication, and Docker – all in a practical way. The goal was not only to make something that works but something **educational and reproducible** for other beginners.

Mistakes were made. Lessons were learned. Logs were stared at. Victory was earned.

---

Feel free to fork this and experiment. Happy coding! 😊



## 🧠 What is This?

It’s a **User Service** — the part of an application responsible for:

- Creating user accounts (signup/register)
- Letting users log in (authentication)
- Returning a token (JWT) that proves who the user is
- Allowing users to update or delete their own accounts
- Securing certain routes so only logged-in users can access them

This is part of a bigger dream: a **Delivery Hero clone** (like UberEats or DoorDash).

### 💡 What Did I Want to Learn?

| Concept                | What I Wanted to Understand                                                                 |
|------------------------|----------------------------------------------------------------------------------------------|
| ✅ Kotlin              | A modern programming language that runs on the JVM and works with Spring Boot.              |
| ✅ Spring Boot         | A powerful Java/Kotlin framework that makes building APIs easy.                             |
| ✅ REST APIs           | How to design web APIs using HTTP verbs like GET, POST, PUT, DELETE.                        |
| ✅ JWT Authentication  | How to securely log users in and protect data using JSON Web Tokens.                        |
| ✅ Microservices       | How to break big apps into smaller services that can scale independently.                   |
| ✅ Secure Endpoints    | How to make sure only authenticated users can access private data.                         |

### 📦 What’s in This Service?

| Feature                      | Description                                                                 |
|------------------------------|-----------------------------------------------------------------------------|
| ✅ User Registration          | Create a new user with email, password, username.                          |
| ✅ Duplicate Check            | Prevent registering with the same email twice.                             |
| ✅ Password Hashing           | Passwords are encrypted using **BCrypt**, not stored in plain text.        |
| ✅ JWT Token Auth             | Users get a **secure token** after logging in.                             |
| ✅ Login                      | Validate credentials and return token.                                     |
| ✅ Token-Based Access         | Only users with valid tokens can access protected routes.                  |
| ✅ Update Profile             | Update your username or email.                                             |
| ✅ Delete Account             | Securely delete your own account.                                          |

### 🧱 Tech Stack

| Tool/Library         | Why I Used It                                                                              |
|----------------------|---------------------------------------------------------------------------------------------|
| Kotlin               | Modern, concise syntax. I wanted to try something new and readable.                        |
| Spring Boot          | Industry-standard for backend APIs. Easy config, solid ecosystem.                         |
| Spring Security      | Handles authentication, login, and route protection.                                      |
| JWT (jjwt library)   | Token-based authentication. Perfect for stateless microservices.                          |
| PostgreSQL           | Open-source relational database. Great for learning.                                       |
| JPA (Hibernate)      | Allows writing less SQL. Works well with Kotlin and Spring.                                |

## 📚 Key Concepts Explained

### 🌐 What is a REST API?

REST = **REpresentational State Transfer**  
It’s a way to design web APIs using standard HTTP verbs:

| HTTP Verb | What It Does     | Example                           |
|-----------|------------------|-----------------------------------|
| `POST`    | Create something | `POST /api/users` → create user   |
| `GET`     | Read something   | `GET /api/users` → get user list  |
| `PUT`     | Update something | `PUT /api/users/1` → update user  |
| `DELETE`  | Delete something | `DELETE /api/users/1` → delete user|

### 🔐 What is JWT?

**JWT = JSON Web Token**

It's a small, secure text token that proves who the user is. When a user logs in, we give them a JWT. On every request, they send it back:

```
Authorization: Bearer <your_token>
```

We verify that token before showing any sensitive data.

### 🧩 What are Microservices?

Instead of building **one giant application**, we split it into **small services**.

| Microservice       | Responsibility                  |
|--------------------|----------------------------------|
| `user-service`     | Login, signup, profile           |
| `menu-service`     | Show restaurant items            |
| `order-service`    | Handle orders and payments       |

Each service runs independently but can talk to each other via **HTTP + JWT tokens**.

## 🔨 Project Structure

```
src/main/kotlin/
└── com.deliveryhero.clone.user_service/
    ├── controller/        <-- REST API endpoints
    ├── domain/            <-- User entity
    ├── dto/               <-- Request/Response models
    ├── repository/        <-- JPA repositories
    ├── util/              <-- JWT utils
    ├── config/            <-- Spring Security configuration
```

## 🔐 Authentication Flow

```
Client -> POST /api/users (register) -> 200 OK
Client -> POST /api/users/login -> JWT Token
Client -> GET /api/users -> (Authorization: Bearer token)
```

## 🧪 How to Test the API

### 1. Register

```
POST /api/users
{
  "email": "me@example.com",
  "username": "me",
  "password": "123456"
}
```

### 2. Login

```
POST /api/users/login
{
  "email": "me@example.com",
  "password": "123456"
}
```

✅ This returns a token!

### 3. Access Protected Routes

```
GET /api/users
Authorization: Bearer <your_token_here>
```



## 📈 Future Plans

| Area                  | Plan                                                                |
|------------------------|---------------------------------------------------------------------|
| Error Handling         | Consistent error format (with custom exception classes)             |
| Tests                 | Add unit tests and integration tests                                |
| Swagger/OpenAPI       | Auto-generate documentation for all routes                          |
| Role-Based Access     | Separate access for `ADMIN`, `USER` etc.                            |
| Refresh Tokens        | Support long sessions with refresh tokens                           |
| Rate Limiting         | Prevent brute force attacks on login                                |



## 📎 How to Run

```bash
git clone https://github.com/yourusername/user-service-kotlin
cd user-service-kotlin
./gradlew bootRun
```

---

## 🧠 Beginner-Friendly Concepts and Explanations

This project was built from scratch by a beginner, for beginners. Here's a breakdown of important concepts:

### 🧪 REST & CRUD

| Term | Meaning | Example in this Project |
|------|--------|--------------------------|
| REST | REpresentational State Transfer – A way to structure communication between services using HTTP verbs | `/api/users` follows REST principles |
| CRUD | Create, Read, Update, Delete – core operations of data management | We implement all 4 with user endpoints |

| HTTP Verb | CRUD Action | Description |
|-----------|-------------|-------------|
| `POST`    | Create      | Create a new resource (e.g. user registration) |
| `GET`     | Read        | Retrieve data (e.g. fetch users) |
| `PUT`     | Update      | Modify existing data (e.g. update profile) |
| `DELETE`  | Delete      | Remove data (e.g. delete account) |

---

### 📦 Kotlin, Spring Boot & Microservices

| Term | Meaning |
|------|---------|
| **Kotlin** | A modern language that runs on the JVM – concise, expressive, and safe |
| **Spring Boot** | A framework to quickly build Java/Kotlin apps with lots of built-in tools |
| **Microservice** | A small, focused service (like `user-service`) that can run independently and communicate with others (like `menu-service`) |

Why Microservices?
- Easy to manage and scale each service separately
- Helps build large systems in small, testable pieces
- Encourages better code structure and responsibility

---

### 🛠️ Postman Commands

Use these commands in Postman to test your API:

#### ✅ Register a New User
```
POST http://localhost:8080/api/users
Content-Type: application/json

{
  "username": "funfun",
  "email": "funfun@example.com",
  "password": "funfun"
}
```

#### ✅ Login User
```
POST http://localhost:8080/api/users/login
Content-Type: application/json

{
  "email": "funfun@example.com",
  "password": "funfun"
}
```

This will return a JWT `token` – use it for the next calls:

#### 🔐 Get All Users (Requires Token)
```
GET http://localhost:8080/api/users
Authorization: Bearer <your_token>
```

#### 📝 Update User
```
PUT http://localhost:8080/api/users/1
Authorization: Bearer <your_token>
Content-Type: application/json

{
  "username": "newUsername"
}
```

#### 🗑️ Delete User
```
DELETE http://localhost:8080/api/users/1
Authorization: Bearer <your_token>
```

---

### 🐘 PostgreSQL Setup (via Docker)

This project uses **PostgreSQL** for storing user data.

#### 🔧 Run Postgres with Docker
```bash
docker run --name deliveryhero-db   -e POSTGRES_DB=deliveryhero   -e POSTGRES_USER=postgres   -e POSTGRES_PASSWORD=password   -p 5432:5432   -d postgres
```

- DB: `deliveryhero`
- User: `postgres`
- Password: `password`

#### 📦 Connect in `application.yml`
Make sure your Spring Boot app connects to this DB. Example:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/deliveryhero
    username: postgres
    password: password
  jpa:
    hibernate:
      ddl-auto: update
```

---

### 📊 Diagram: System Overview

```
+-----------------+         +-----------------+
|                 |  REST   |                 |
|   Postman       +-------->+  user-service   |
|                 |         | (Spring Boot)   |
+--------+--------+         +--------+--------+
                                 |
                                 | JPA
                           +-----v------+
                           |  Postgres  |
                           +------------+
```

---

## 🔮 Future Improvements & Ideas

| Feature | Motivation |
|--------|------------|
| Email verification | Confirm user authenticity |
| Password reset via token | Recover forgotten credentials |
| Rate limiting / request throttling | Prevent abuse of login or delete endpoints |
| Admin role | Add authorization levels |
| Dockerize whole service | For deployment |
| Menu-service | New microservice for managing food/menu data |
| API Gateway (e.g., Spring Cloud Gateway) | Route and secure requests across services |
| Centralized logging | Use tools like ELK or Loki |

---



## ✅ What You Already Have

| Feature                     | Description                                                                 |
|-----------------------------|-----------------------------------------------------------------------------|
| 🧑‍💻 **User Registration**   | Users can sign up via `POST /api/users`. Validates input and saves to DB.   |
| 🔐 **Password Hashing**     | Passwords are hashed using **BCrypt** for security before storing.         |
| 🗝️ **Login**                | Authenticated via email + password on `POST /api/users/login`.              |
| 📜 **JWT Authentication**   | Users receive a JWT token which is required to access protected endpoints. |
| 🔒 **Spring Security**      | Uses custom `SecurityConfig` and JWT filters to secure the application.    |
| 🧪 **Tested via Postman**   | All flows (register, login, fetch, update, delete) tested via Postman.     |
| 📦 **DTO Layer**            | Clean separation of domain and API contracts (e.g. no raw password return).|
| 🗃️ **PostgreSQL (fooddb)** | Uses a real PostgreSQL DB (in Docker or locally) for persistence.          |
| 🛠️ **Layered Structure**    | Clear folders: `controller`, `domain`, `dto`, `repository`, `config`, `util`.|


## 🙏 Final Thoughts

This project was built with love and curiosity. I ran into errors, Googled a LOT, asked dumb questions, and slowly put the puzzle together.

💬 If you're a beginner like me: **don’t give up!** Every bug teaches you something.

---