Markdown
# Planto Backend

REST API for **Planto**, an online plant and gardening store built with Spring Boot and MongoDB.

The service handles user authentication with JWT access/refresh tokens, product catalog management, dynamic filtering for products and orders, order processing, and image uploads via ImageKit.

## Tech Stack

- **Java 17** & **Spring Boot 3.5.0**
- **MongoDB** (Spring Data MongoDB + custom queries via `MongoTemplate`)
- **Spring Security** + **JJWT (0.12.6)** (stateless JWT auth with refresh tokens)
- **ModelMapper** & **Lombok**
- **ImageKit Java SDK** (cloud media storage)
- **Docker** (multi-stage build)

## Main Features

- **Auth & Roles:** User registration, login, and token refresh (`Access` + `Refresh` tokens stored in MongoDB), role-based access control (`Role`), and custom security error handlers (`JwtAuthEntryPoint`, `CustomAccessDeniedHandler`).
- **Product Catalog & Chapters:** CRUD operations for products and categoriesпше, input validation, and custom repository queries for filtering by price, category, and sorting.
- **Orders & Cart:** Order creation, status tracking (`OrderStatus`), order updates, and dynamic filtering by date ranges, amounts, and statuses.
- **Image Uploads:** Endpoint for uploading product/category images directly to ImageKit (`ImageKitController`).
- **Exception Handling:** Centralized error responses via `GlobalExceptionHandler` (duplicate email/login checks, invalid credentials, validation errors).

## Project Structure

```text
src/main/java/planto_project/
├── configuration/   # ModelMapper, CORS, and web settings
├── controller/      # REST controllers (Auth, User, Product, Chapter, Order)
├── dao/             # MongoDB repositories & custom filter implementations
├── dto/             # Request/response DTOs and filter criteria models
├── exceptions/      # Custom exceptions and GlobalExceptionHandler
├── imagekit/        # ImageKit integration (controller, service, properties)
├── model/           # MongoDB documents and domain models
├── security/        # JWT filter, token utils, SecurityConfiguration
├── service/         # Business logic layer
└── validator/       # Custom validators for users and products
Getting Started
Prerequisites
JDK 17+

Maven 3.8+ (or the included ./mvnw wrapper)

MongoDB instance (local or MongoDB Atlas)

ImageKit account credentials (public key, private key, URL endpoint)

Configuration
Create an application.properties or application.yml in src/main/resources/ (or pass variables via environment) with your MongoDB, JWT, and ImageKit settings:

Properties
spring.data.mongodb.uri=mongodb://localhost:27017/planto
jwt.secret=your_base64_encoded_secret_key
imagekit.public-key=your_public_key
imagekit.private-key=your_private_key
imagekit.url-endpoint=[https://ik.imagekit.io/your_id](https://ik.imagekit.io/your_id)
Run Locally
Bash
./mvnw clean spring-boot:run
The API will start on http://localhost:8080.

Run with Docker
Build the image and run the container:

Bash
docker build -t planto-backend .
docker run -p 8080:8080 \
  -e SPRING_DATA_MONGODB_URI="mongodb://host.docker.internal:27017/planto" \
  -e JWT_SECRET="your_base64_encoded_secret_key" \
  planto-backend
API Controllers Overview
AuthController — registration, login, and refresh token rotation

UserController — user profile management and role assignment

ChapterController — store sections/categories management

ProductController — product CRUD, catalog search, and dynamic filtering

OrderController — checkout, order history, status updates, and order filtering

ImageKitController — image file upload to ImageKit