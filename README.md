Microservices Authentication & Text Transformation System

A dual-service Spring Boot application demonstrating secure inter-service communication, JWT authentication, and Docker orchestration.
🏗 Architecture

    auth-api (Service A): Handles user management, JWT issuance, and persistent logging to PostgreSQL.
    data-api (Service B): Provides text transformation logic. Protected via internal shared secret.
    PostgreSQL: Stores user credentials and processing history.

🚀 Quick Start
1. Prerequisites
    Docker and Docker Compose installed.
    Java 17+ (if building locally).

2. Run with Docker Compose

From the project root, execute:
`docker compose up -d --build`
This command builds both services and starts the PostgreSQL container.

🧪 Testing the Flow (CURL)

    Register a user:

curl -X POST http://localhost:8080/api/auth/register \
-H "Content-Type: application/json" \
-d '{"email":"test@example.com","password":"password123"}'

    Login to get JWT:

curl -X POST http://localhost:8080/api/auth/login \
-H "Content-Type: application/json" \
-d '{"email":"test@example.com","password":"password123"}'

    Process text (Replace <TOKEN> with your actual token):

curl -X POST http://localhost:8080/api/process \
-H "Authorization: Bearer <TOKEN>" \
-H "Content-Type: application/json" \
-d '{"text":"hello world"}'

🔒 Security Implementation

    BCrypt Hashing: All user passwords are encrypted before storage.
    
    Internal Token: data-api validates the X-Internal-Token header for all requests, rejecting direct external traffic.
    
    Audit Trail: Every transformation request is logged in the processing_log table with the associated user ID.
