# Finance Dashboard Backend (Spring Boot)

## Project Overview

This project is a backend system for a Finance Dashboard that allows users to manage financial records (income and expenses) with secure role-based access control using JWT authentication.

It is designed as part of an internship assessment to demonstrate backend engineering skills including API design, security, and database management.

---

## Features

* JWT-based Authentication and Authorization
* Role-based Access Control (ADMIN, ANALYST, VIEWER)
* Financial Records Management (CRUD operations)
* Filtering (by date, type, category)
* Pagination support
* Swagger API Documentation
* Secure APIs with Spring Security

---

## Tech Stack

* Backend: Spring Boot
* Security: Spring Security with JWT
* Database: MySQL
* ORM: JPA / Hibernate
* Build Tool: Maven
* API Documentation: Swagger (OpenAPI)

---

## Architecture

* Controller Layer: Handles HTTP requests
* Service Layer: Contains business logic
* Repository Layer: Handles database operations
* Security Layer: JWT filter and authentication

---

## Authentication Flow

1. User logs in using email and password

2. Server generates a JWT token

3. Client sends token in header:

   Authorization: Bearer <token>

4. Backend validates token for protected APIs

---

## API Documentation

Swagger UI is available at:

http://localhost:8080/swagger-ui/index.html

---

## Setup Instructions

### 1. Clone Repository

git clone https://github.com/JavaDeveloper-Sinku/Finance-Data-Processing-and-Access-Control-Backend.git
cd finance-backend

### 2. Configure Database

Update application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/finance_db
spring.datasource.username=root
spring.datasource.password=your_password

### 3. Add JWT Secret

jwt.secret=your_secret_key_here

### 4. Run Application

mvn spring-boot:run

---

## Sample API Endpoints

### Authentication APIs

* POST /api/auth/login
* POST /api/auth/register

### Financial Records APIs

* GET /api/records
* POST /api/records
* PUT /api/records/{id}
* DELETE /api/records/{id}

---

## Project Structure

src/main/java/com/finance/backend

├── controller
├── service
├── repository
├── entity
├── security
└── config

---

## Author

Rishi Singh
Java Backend Developer
