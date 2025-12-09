# 🚗 RideShare Backend

A Spring Boot ride-sharing application with JWT authentication and MongoDB.

## 🚀 Features

- User registration and login with JWT
- Role-based access (USER & DRIVER)
- Ride request and management
- Ride acceptance and completion tracking

## 🛠️ Tech Stack

- **Backend**: Spring Boot 3.x
- **Database**: MongoDB
- **Security**: JWT Authentication
- **Build Tool**: Maven
- **Java**: 17

## 📋 Prerequisites

- Java 17+
- MongoDB
- Maven

## ⚡ Quick Start

### 1. Clone Repository
```bash
git clone https://github.com/AbhiGupta1310/rideshare_backend.git
cd rideshare_backend
```

### 2. Configure MongoDB
Update `application.properties`:
```properties
spring.data.mongodb.database=rideshare_db
jwt.secret=yourSecretKey
```

### 3. Run Application
```bash
mvn spring-boot:run
```

Application starts at: **http://localhost:8081**

## 📚 API Endpoints

### Authentication
- `POST /api/auth/register` - Register user
- `POST /api/auth/login` - Login

### User (ROLE_USER)
- `POST /api/v1/rides` - Create ride
- `GET /api/v1/user/rides` - Get user rides

### Driver (ROLE_DRIVER)
- `GET /api/v1/driver/rides/requests` - View pending rides
- `POST /api/v1/driver/rides/{id}/accept` - Accept ride

### General
- `POST /api/v1/rides/{id}/complete` - Complete ride

## 💡 Example Usage

### Register User
```bash
curl -X POST http://localhost:8081/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"john","password":"pass123","role":"ROLE_USER"}'
```

### Create Ride
```bash
curl -X POST http://localhost:8081/api/v1/rides \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"pickupLocation":"Location A","dropLocation":"Location B"}'
```

## 📁 Project Structure
```
src/main/java/org/example/rideshare/
├── config/         # Security & JWT config
├── controller/     # REST controllers
├── dto/            # Data transfer objects
├── exception/      # Exception handling
├── model/          # MongoDB entities
├── repository/     # Data access layer
├── service/        # Business logic
└── util/           # Utilities
```

## 🔒 Security

- JWT tokens for authentication
- BCrypt password encryption
- Role-based access control

## 📄 License

MIT License

## 👤 Author

**ABHI GUPTA**
