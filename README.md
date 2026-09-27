# Carzy - Rental Car Booking Application

Carzy is a simple full-stack rental car application built with Java, Spring Boot, Angular, and MySQL.

## Features

- Browse available cars
- View car details, pricing, model, and availability
- Book a car with customer and booking details
- View bookings
- REST APIs using Spring Boot
- MySQL database integration using Spring Data JPA
- Angular frontend
- Basic validation and exception handling

## Tech Stack

### Backend
- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL
- Maven

### Frontend
- Angular 17
- TypeScript
- HTML/CSS

## Project Structure

```text
Carzy-Rental-Car/
├── backend/
│   ├── pom.xml
│   └── src/
└── frontend/
    ├── package.json
    └── src/
```

## Database Setup

Create a MySQL database:

```sql
CREATE DATABASE carzy_db;
```

Update `backend/src/main/resources/application.properties` with your MySQL username/password.

## Run Backend

```bash
cd backend
mvn spring-boot:run
```

Backend runs on:

```text
http://localhost:8080
```

## Run Frontend

Install Node.js and Angular CLI, then:

```bash
cd frontend
npm install
npm start
```

Frontend runs on:

```text
http://localhost:4200
```

## Main APIs

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/cars` | Get all cars |
| GET | `/api/cars/available` | Get available cars |
| GET | `/api/cars/{id}` | Get car details |
| POST | `/api/bookings` | Create a booking |
| GET | `/api/bookings` | Get bookings |

## Sample Booking Request

```json
{
  "carId": 1,
  "customerName": "Rahul Sharma",
  "customerEmail": "rahul@example.com",
  "startDate": "2026-10-01",
  "endDate": "2026-10-03"
}
```

## Resume Description

**Carzy — Rental Car Booking Application**  
Developed a full-stack rental car application that allows users to browse vehicles, view pricing and availability, and create rental bookings. Implemented REST APIs using Spring Boot and database operations using Spring Data JPA with MySQL, with an Angular frontend for the user interface.
