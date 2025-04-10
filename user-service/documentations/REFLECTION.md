
# Project Reflection – User Service with Kotlin & Spring Boot

## 1. Why I Chose This Project

When I saw that the Hero Tech Course focused on backend development in Kotlin, I felt both excited and overwhelmed. I had zero experience with Spring Boot or Kotlin, but I was drawn to the idea of building something "real"—something that could eventually be part of a food delivery app like Delivery Hero or UberEats.

This project started as a simple curiosity: _How do users register and log in securely in modern applications?_ 

I wanted to explore:
- How APIs work using REST

- How databases connect to code

- How authentication is actually done

- How microservices talk to each other (work in progress)

I decided to go all in, no matter how many errors it would throw at me (and wow, it threw a lot).

## 2. Choosing Tech Stack

- ✅ **Kotlin**: Cleaner syntax, recommended by Hero Tech Course — felt modern.
- ✅ **Spring Boot**: Powerful and industry-standard for backend apps.
- ✅ **JWT** over session-based auth because it’s stateless and microservice-friendly.
- ✅ **Docker + PostgreSQL**: To understand real-world dev environments and isolation.
- ✅ **Postman**: To test endpoints realistically, without building a frontend.

Even when I didn’t fully understand, I tried to follow best practices and learn why they exist.

## 3. Initial Challenges

I’m being honest here — I was *totally lost* in the beginning. As a complete beginner, I underestimated how deep the rabbit hole goes and struggled with:
- Setting up a Spring Boot project correctly
- Understand how Spring Boot wired things together.
- Understanding how dependencies worked (Gradle, yikes!)
- Dependency injection and annotations like `@Component`, `@Service`, and `@Autowired` were confusing.
- Wiring controllers, services, and repositories — and what those even are
- Getting PostgreSQL to run in Docker and connect to Spring
- Setting up application properties correctly
- Dealing with mysterious 403/500 errors
- Understanding JWT and Spring Security filters

Structuring a backend project felt like guessing, but I learned that **every beginner has to wade through confusion**, and that’s okay!



## 4. Common Beginner Errors I Faced

A deeper look into the actual backend issues I ran into, what caused them, and how I fixed them:

| **Error Message**                                  | **What It Meant**                                                                  | **How I Fixed It**                                                   |
|----------------------------------------------------|------------------------------------------------------------------------------------|----------------------------------------------------------------------|
| **Trying to use PostgreSQL before DB existed**     | Forgot to spin up the container or create the DB first                            | Used `docker-compose` or created manually                           |
| **Not creating a default constructor for User**    | Hibernate needs a no-args constructor for data mapping                            | Used default values in the Kotlin data class                         |
| **Sending wrong HTTP methods (e.g., GET instead of POST)** | Sent incorrect method in Postman or curl                          | Verified method matches controller annotations (`@PostMapping`)     |
| **Forgetting to hash the password**                | Passwords were stored in plain text                                              | Added `BCryptPasswordEncoder.encode(...)` before saving             |
| **JWT token silently failing**                     | JWT secret was invalid or not Base64-encoded                                      | Regenerated a valid key using `Keys.secretKeyFor(...)`              |
| **Port conflict: Docker + IntelliJ**               | Both tried to use `8080` or `5432` at the same time                               | Only ran one at a time OR changed the exposed port in Docker        |
| **Non-RESTful endpoints like /register**           | Not following REST standards (endpoint naming was off)                            | Merged with `POST /api/users` to keep it clean and RESTful          |
| **403 Forbidden**                                  | I forgot to allow public access to endpoints like `/login`                        | Used `.permitAll()` in `SecurityConfig`                              |
| **No default constructor for entity**              | Hibernate couldn’t create a `User` object because Kotlin data classes need defaults| Added default values (`""`) to all fields in `User.kt`              |
| **port 5432 already in use**                       | Docker Postgres conflicted with my locally installed Postgres                     | Changed port in Docker or stopped local DB                           |
| **database "fooddb" does not exist**               | The DB wasn't created yet (but Spring tried to connect)                           | Created it manually via `psql` or Docker                             |
| **password authentication failed for user**        | Postgres user or password mismatch                                                | Checked Docker env and Spring `application.properties`              |
| **JWT signing key error**                          | I forgot to Base64-decode the JWT secret                                          | Decoded the key using Java's `Base64.getDecoder()` properly         |
| **401 Unauthorized even after login**              | Forgot to include `Bearer <token>` in Postman headers                             | Fixed Postman auth header with full `Authorization`                 |



Each error taught me something valuable.



## 6. What I Would Do Differently

If I could start over:

- Plan my folder structure from day one
- Create DTOs from the beginning to separate data layers cleanly
- Write integration tests alongside each feature
- Separate Docker Compose files for local dev vs production
- Add proper error handling (with exception classes!)

This is just the beginning. Refactoring is a feature, not a flaw.

## 7. What I Learned

This project helped me grow in so many ways:

- How to persist through bugs and vague errors
- How to read official docs and understand Gradle, Spring, Kotlin
- Why authentication matters — and how JWT actually works
- How to connect and containerize services using Docker
- That it's okay to ask questions, copy snippets, and Google relentlessly
- How to ask questions to my peers and in online forums

Most importantly: *Consistency > Genius*. I built this through steady effort.

## 7. Next Steps

This isn’t the end — it’s a foundation.

- [ ] Build `menu-service` and `order-service` next, and connect it with JWT
- [ ] Add role-based access (`USER`, `ADMIN`)
- [ ] Docker Compose setup for multiple microservices

---

This reflection is for me and anyone else trying to learn by building. If you're reading this and feeling overwhelmed: **you can totally do this.** 💪
