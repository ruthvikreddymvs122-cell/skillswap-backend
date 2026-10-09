SkillSwap Campus — Backend

The Spring Boot REST API for SkillSwap Campus, a peer-to-peer skill exchange and micro-mentoring platform for college students.

The backend manages student accounts, skills, skill-based matching, exchange requests, and learning sessions using a MySQL database.

Features

- Student registration and login
- User and skill management
- Teach/learn skill associations
- Find students who can teach a requested skill
- Create and manage exchange requests
- Accept or reject exchange requests
- Create and manage learning sessions
- Persistent storage using MySQL

Tech Stack

- Language: Java
- Framework: Spring Boot
- Build Tool: Maven
- Database: MySQL
- Persistence: Spring Data JPA and Hibernate
- Security: Spring Security and BCrypt password encoding
- Authentication: JWT token generation
- API: REST

Project Structure

skillswap-backend/
├── src/
│   ├── main/
│   │   ├── java/com/skillswap/
│   │   │   ├── config/
│   │   │   ├── controller/
│   │   │   ├── entity/
│   │   │   ├── repository/
│   │   │   └── service/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md

Requirements

- JDK 17 or compatible Java version
- Maven, or the included Maven Wrapper
- MySQL Server
- Visual Studio Code or another Java IDE

Database Setup

1. Start MySQL.

2. Create the database:
   
   CREATE DATABASE skillswap_db;

3. Configure your database connection in "src/main/resources/application.properties".
   
   Example:
   
   spring.application.name=skillswap

spring.datasource.url=jdbc:mysql://localhost:3306/skillswap_db
spring.datasource.username=root
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=8080

4. Set the "DB_PASSWORD" environment variable to your local MySQL password before starting the application.

Security note: Never commit your real database password, JWT secrets, or other credentials to GitHub.

Installation and Setup

1. Clone this repository:
   
   git clone https://github.com/ruthvikreddymvs122-cell/skillswap-backend.git

2. Open the project folder:
   
   cd skillswap-backend

3. Configure MySQL credentials.

4. Run the application using the Maven Wrapper on Windows:
   
   .\mvnw.cmd spring-boot:run

5. The backend should start at:
   
   "http://localhost:8080"

Main API Routes

Feature| Endpoint
Authentication| "/api/auth"
Users| "/api/users"
Skills| "/api/skills"
User skills| "/api/user-skills"
Matching| "/api/matching"
Exchange requests| "/api/exchange-requests"
Sessions| "/api/sessions"

These routes are base paths; individual operations use additional paths and HTTP methods.

Project Objective

To provide a backend service that helps college students exchange knowledge, connect with peer mentors, and coordinate learning sessions.

Future Enhancements

- Enforce JWT authentication on protected APIs
- Add role-based access control
- Add reviews, ratings, and skill credits
- Implement skill-chain matching
- Add request validation and centralized exception handling
- Deploy the backend and database securely

Author

Ruthvik Reddy