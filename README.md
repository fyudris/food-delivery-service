# 🍔 Food Delivery Service Backend

This is a learning project where I'm building a **miniature food ordering and delivery system** using **Kotlin**, **Spring Boot**, **PostgreSQL**, and **Docker** — inspired by the systems used at Delivery Hero, Baemin, and similar real-world platforms.

The goal is to understand modern backend development using a microservice-like structure, best practices, containerization, and documentation — while keeping the scope small and achievable.

---

## ✅ What I’ve Done So Far

### 🧠 1. Project Setup

- Created a Kotlin + Spring Boot project using [Spring Initializr](https://start.spring.io)
    - Selected `Spring Web`, `Spring Data JPA`, `PostgreSQL`, `DevTools`, and `Actuator`
- Chose **Gradle with Kotlin DSL** as the build tool
- Used **Java 17** as the SDK
- Set project name to: `food-delivery-service`

---

### 🐘 2. Connected to PostgreSQL via Docker

Instead of installing Postgres manually, I used Docker to run the database cleanly and consistently:

```bash
docker run --name food-postgres \
  -e POSTGRES_DB=fooddb \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=password \
  -p 5433:5432 \
  -d postgres:15
```

This:
- Creates a database named fooddb
- Exposes it on port 5433 (to avoid conflict with local Postgres if already installed)
- Uses default Postgres user/password
- Then I configured Spring Boot to connect to it:

***📄 src/main/resources/application.properties***
```bash
spring.datasource.url=jdbc:postgresql://localhost:5433/fooddb
spring.datasource.username=postgres
spring.datasource.password=password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.sql.init.mode=always
```
---
### 🖥️ 3. Ran the Application in IntelliJ
- Clicked the green “Run” button in FoodDeliveryServiceApplication.kt
- At first saw the Whitelabel Error Page, which is expected since no endpoint was created yet
- Then added a simple Hello World controller to verify everything works

**📄 HelloController.kt**
```
@RestController
class HelloController {
    @GetMapping("/")
    fun home(): String = "Hello, food delivery world!"
}
```
Now visiting http://localhost:8080 shows a working response! 🎉

### 🧱 Technologies So Far
| Tech        | Why I’m Using It                                           |
|-------------|------------------------------------------------------------|
| Kotlin      | Concise, modern JVM language                               |
| Spring Boot | Production-grade backend framework                         |
| PostgreSQL  | Industry-standard relational database                      |
| Docker      | So I don’t have to install/configure things manually       |
| IntelliJ    | Full-featured IDE with great Kotlin support                |
| GitHub      | Version control and portfolio showcase                     |
