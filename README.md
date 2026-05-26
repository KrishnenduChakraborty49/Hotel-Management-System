# Hotel Management System Backend

A production-ready Hotel Booking backend application built with Spring Boot, MySQL, Redis, and Docker. This project uses a Clean N-Tier Architecture, ensuring high scalability and maintainability.

## 🚀 Features

- **Authentication & Authorization**: Stateless JWT-based authentication with role-based access (`CUSTOMER`, `ADMIN`, `HOTEL_OWNER`).
- **Hotel & Room Management**: Endpoints to add, update, and manage hotels and their specific room inventories.
- **Booking Engine**: Robust booking system that dynamically calculates prices and handles booking statuses.
- **Concurrency Control**: Prevents double-booking using Optimistic Locking (`@Version`).
- **Caching**: Redis integration to drastically speed up read-heavy hotel search operations.
- **Global Exception Handling**: Standardized error responses using `@ControllerAdvice`.

## 🛠️ Tech Stack

- **Java 21**
- **Spring Boot 3.2+** (Web, Data JPA, Security, Validation)
- **MySQL 8.0** (Primary Database)
- **Redis** (Caching Layer)
- **JWT (JSON Web Tokens)** (Security)
- **Docker & Docker Compose** (Containerization)
- **Swagger / OpenAPI** (API Documentation)

## 🐳 Docker & Redis Setup (How to Run)

This project is fully containerized. You do not need to install MySQL or Redis on your local machine, Docker handles it all for you!

### Prerequisites
- Install [Docker Desktop](https://www.docker.com/products/docker-desktop)

### Step-by-Step Execution

1. **Open a terminal** in the root directory of this project (where the `docker-compose.yml` file is located).
2. **Run the following command** to build the application and start all containers in the background:
   ```bash
   docker-compose up -d --build
   ```
3. **What happens under the hood?**
   - Docker pulls the `mysql:8.0` image, creates a database named `hotel_booking`, and exposes it on port `3306`.
   - Docker pulls the `redis:alpine` image and exposes the caching server on port `6379`.
   - Docker compiles your Spring Boot application into a `.jar` file, bundles it in an `eclipse-temurin:21` Java environment, and runs it on port `8080`.
   - The Spring Boot app automatically connects to the MySQL and Redis containers using the environment variables defined in the `docker-compose.yml`.

4. **Verify the Application**:
   Wait a few seconds for the application to start. You can view the logs by running:
   ```bash
   docker-compose logs -f app
   ```

## 📖 API Documentation (Swagger)

Once the application is running via Docker, you can access the interactive API documentation and test the endpoints directly from your browser:

**Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Key Endpoints to Test:
- `POST /api/v1/auth/register` - Create a new user.
- `POST /api/v1/auth/login` - Authenticate and receive a JWT token.
- `POST /api/v1/hotels` - Add a new hotel (Requires HOTEL_OWNER/ADMIN JWT token).
- `GET /api/v1/hotels` - Retrieve all hotels (Caches results in Redis).

## 🛑 Stopping the Application

To stop the database, cache, and application containers gracefully, run:
```bash
docker-compose down
```
*(Note: Data in the MySQL database will be lost when you run `down` unless you configure volume mounting in the docker-compose file).*
