# 💰 Finance Dashboard Backend (Spring Boot)

A **secure, role-based financial management system** built with Spring Boot. This backend allows users to manage financial records (income and expenses) with JWT authentication and granular access control.

---

## 📌 Project Overview

This is a production-ready backend system for a Finance Dashboard designed to demonstrate backend engineering skills including API design, security, and database management. It features JWT-based authentication, role-based access control, and comprehensive financial record management.

---

## ✨ Features

✅ **JWT-based Authentication & Authorization**  
✅ **Role-based Access Control** (ADMIN, ANALYST, VIEWER)  
✅ **Financial Records Management** (CRUD operations)  
✅ **Advanced Filtering** (by date, type, category)  
✅ **Pagination Support** for large datasets  
✅ **Swagger API Documentation** (OpenAPI)  
✅ **Secure APIs** with Spring Security  
✅ **Dashboard Summary** analytics  

---

## 🛠️ Tech Stack

| Technology | Purpose |
|-----------|---------|
| **Java 17+** | Programming language |
| **Spring Boot** | Application framework |
| **Spring Security** | Authentication & authorization |
| **JWT** | Stateless token-based security |
| **MySQL** | Relational database |
| **JPA/Hibernate** | ORM framework |
| **Maven** | Build tool |
| **Swagger (OpenAPI)** | API documentation |

---

## 🏗️ Architecture

```
HTTP Request
    ↓
Controller Layer (HTTP handling)
    ↓
Service Layer (Business logic)
    ↓
Repository Layer (Database operations)
    ↓
MySQL Database
```

### Layers Breakdown

- **Controller Layer** – Handles HTTP requests and responses
- **Service Layer** – Contains business logic and validations
- **Repository Layer** – Handles database operations (JPA)
- **Security Layer** – JWT filter and authentication
- **Entity Layer** – Database model classes
- **Config Layer** – Spring configuration beans

---

## 🔐 Authentication Flow

```
1. User sends login request
   └─ POST /api/auth/login (email, password)

2. Server validates credentials
   └─ Check in database

3. Server generates JWT token
   └─ Embedded with user role & permissions

4. Client receives token
   └─ Store in local storage

5. Client sends token in Authorization header
   └─ Authorization: Bearer <token>

6. Backend validates token
   └─ JWT filter intercepts request
   └─ Validates signature & expiration

7. Access granted to resource
   └─ Request processed
```

---

## 📂 Project Structure

```
Finance-Data-Processing-and-Access-Control-Backend/
│
├── src/main/java/com/finance/backend
│   ├── controller/
│   │   ├── AuthController.java
│   │   ├── RecordController.java
│   │   └── DashboardController.java
│   │
│   ├── service/
│   │   ├── AuthService.java
│   │   ├── RecordService.java
│   │   ├── DashboardService.java
│   │   └── JwtTokenProvider.java
│   │
│   ├── repository/
│   │   ├── UserRepository.java
│   │   └── FinancialRecordRepository.java
│   │
│   ├── entity/
│   │   ├── User.java
│   │   ├── FinancialRecord.java
│   │   └── Role.java
│   │
│   ├── security/
│   │   ├── JwtAuthenticationFilter.java
│   │   ├── JwtAuthenticationEntryPoint.java
│   │   └── SecurityConfig.java
│   │
│   ├── dto/
│   │   ├── LoginRequest.java
│   │   ├── RegisterRequest.java
│   │   ├── FinancialRecordDTO.java
│   │   └── DashboardSummary.java
│   │
│   ├── exception/
│   │   ├── ResourceNotFoundException.java
│   │   ├── UnauthorizedException.java
│   │   └── GlobalExceptionHandler.java
│   │
│   ├── config/
│   │   ├── AppConfig.java
│   │   └── JwtConfig.java
│   │
│   └── FinanceBackendApplication.java
│
├── src/main/resources
│   ├── application.properties
│   └── data.sql (sample data)
│
├── pom.xml
└── README.md
```

---

## 📡 API Endpoints

### Authentication APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/login` | User login |
| POST | `/api/auth/register` | User registration |
| POST | `/api/auth/refresh` | Refresh JWT token |
| GET | `/api/auth/profile` | Get current user profile |

### Financial Records APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/records` | Get all records (paginated) |
| GET | `/api/records/{id}` | Get record by ID |
| POST | `/api/records` | Create new record |
| PUT | `/api/records/{id}` | Update record |
| DELETE | `/api/records/{id}` | Delete record |
| GET | `/api/records/search` | Search records |

### Dashboard APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/dashboard/summary` | Get dashboard summary |
| GET | `/api/dashboard/income` | Total income |
| GET | `/api/dashboard/expenses` | Total expenses |
| GET | `/api/dashboard/chart-data` | Chart data for UI |

---

## 🔑 Request & Response Examples

### Login

**Request:**
```bash
POST /api/auth/login
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "password123"
}
```

**Response (200 OK):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "expiresIn": 86400,
  "user": {
    "id": 1,
    "email": "user@example.com",
    "role": "VIEWER"
  }
}
```

### Create Financial Record

**Request:**
```bash
POST /api/records
Authorization: Bearer <token>
Content-Type: application/json

{
  "type": "INCOME",
  "category": "SALARY",
  "amount": 5000.00,
  "description": "Monthly salary",
  "date": "2024-01-15"
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "userId": 1,
  "type": "INCOME",
  "category": "SALARY",
  "amount": 5000.00,
  "description": "Monthly salary",
  "date": "2024-01-15",
  "createdAt": "2024-01-15T10:30:00",
  "updatedAt": "2024-01-15T10:30:00"
}
```

### Get Dashboard Summary

**Request:**
```bash
GET /api/dashboard/summary
Authorization: Bearer <token>
```

**Response (200 OK):**
```json
{
  "totalIncome": 15000.00,
  "totalExpenses": 5000.00,
  "netBalance": 10000.00,
  "incomeRecords": 3,
  "expenseRecords": 5,
  "lastUpdated": "2024-01-15T15:30:00"
}
```

---

## 🗄️ Database Schema

### Users Table

```sql
CREATE TABLE users (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  first_name VARCHAR(100),
  last_name VARCHAR(100),
  role ENUM('ADMIN', 'ANALYST', 'VIEWER') DEFAULT 'VIEWER',
  is_active BOOLEAN DEFAULT TRUE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

### Financial Records Table

```sql
CREATE TABLE financial_records (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,
  type ENUM('INCOME', 'EXPENSE') NOT NULL,
  category VARCHAR(50) NOT NULL,
  amount DECIMAL(15, 2) NOT NULL,
  description TEXT,
  record_date DATE NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id),
  INDEX idx_user_date (user_id, record_date)
);
```

---

## 🔑 Role-Based Access Control

| Role | Permissions |
|------|-------------|
| **ADMIN** | Create, Read, Update, Delete all records; Manage users |
| **ANALYST** | Create, Read, Update own records; View all records |
| **VIEWER** | Read-only access to own records |

---

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- MySQL 8.0+
- Maven 3.6+

### Setup Instructions

#### 1. Clone Repository

```bash
git clone https://github.com/JavaDeveloper-Sinku/Finance-Data-Processing-and-Access-Control-Backend.git
cd Finance-Data-Processing-and-Access-Control-Backend
```

#### 2. Configure Database

Create MySQL database:

```sql
CREATE DATABASE finance_db;
```

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/finance_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

#### 3. Configure JWT Secret

Update `application.properties`:

```properties
jwt.secret=your_secret_key_here_make_it_strong_min_32_chars
jwt.expiration=86400000
jwt.refresh-expiration=604800000
```

#### 4. Build Project

```bash
mvn clean install
```

#### 5. Run Application

```bash
mvn spring-boot:run
```

Application starts at: http://localhost:8080

---

## 📚 API Documentation

### Swagger UI

Visit: http://localhost:8080/swagger-ui/index.html

### API Docs JSON

Visit: http://localhost:8080/v3/api-docs

---

## 🧪 Testing

### Login Test

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "admin@example.com",
    "password": "admin123"
  }'
```

### Create Record Test

```bash
curl -X POST http://localhost:8080/api/records \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{
    "type": "INCOME",
    "category": "SALARY",
    "amount": 5000.00,
    "description": "Monthly salary",
    "date": "2024-01-15"
  }'
```

---

## ⚙️ Configuration

### Security Configuration

**SecurityConfig.java:**
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // JWT filter configuration
    // CORS settings
    // Password encoder configuration
}
```

### JWT Configuration

**JwtConfig.java:**
```java
@Configuration
public class JwtConfig {
    @Value("${jwt.secret}")
    private String jwtSecret;
    
    @Value("${jwt.expiration}")
    private long jwtExpiration;
}
```

---

## 🐛 Troubleshooting

| Issue | Solution |
|-------|----------|
| **Database connection error** | Check MySQL credentials in application.properties |
| **JWT token invalid** | Ensure secret key is correct and token hasn't expired |
| **Access denied error** | Verify user role has permission for endpoint |
| **Port 8080 already in use** | Change port: `server.port=8081` |
| **Swagger not loading** | Check dependency versions in pom.xml |

---

## 📈 Future Enhancements

- [ ] Budget management
- [ ] Financial goals tracking
- [ ] Monthly/yearly reports
- [ ] CSV/PDF export
- [ ] Email notifications
- [ ] Multi-currency support
- [ ] Investment tracking
- [ ] Recurring transactions
- [ ] Mobile app integration

---

## 📄 License

This project is open-source and available under the **MIT License**.

---

## 👨‍💻 Author

**Sinku Singh**  
Java Backend Developer | Spring Boot | Security | System Design

- GitHub: [JavaDeveloper-Sinku](https://github.com/JavaDeveloper-Sinku)
- Email: singh173@gmail.com
- LinkedIn: [Sinku Singh](https://www.linkedin.com/in/sinku-singh-7a22ab233/)

---

## ⭐ Support

If you find this project helpful, please give it a **star** ⭐ on GitHub!

Happy Coding 🚀
