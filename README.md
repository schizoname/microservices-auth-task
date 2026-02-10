# Microservices Auth & Data Processing System

This project consists of two Spring Boot microservices interacting via Docker Compose, utilizing JWT authentication and secure inter-service communication.

## Architecture
* **Service A (auth-api)**: Port `8080`. Handles user registration, login (JWT), and logs processing requests to PostgreSQL.
* **Service B (data-api)**: Port `8081`. Performs text transformation. Accessible only via Service A using a shared secret token.
* **PostgreSQL**: Database storing user credentials and the processing log table.

---

## Getting Started

### Prerequisites
* Docker and Docker Compose installed.

### Running the System
You can build and start all services (including the database) with a single command:
docker compose up -d --build

API Usage (CURL Examples)
1. User Registration

Create a new account:
curl -X POST http://localhost:8080/api/auth/register \
     -H "Content-Type: application/json" \
     -d '{"email":"dev@example.com","password":"secretpassword"}'

2. User Login

Authenticate to receive a JWT token:
`curl -X POST http://localhost:8080/api/auth/login \`
     `-H "Content-Type: application/json" \`
     `-d '{"email":"dev@example.com","password":"secretpassword"}'`

Copy the token value from the JSON response.

3. Process Data (Secure Endpoint)

Send a request to transform text. Replace <YOUR_TOKEN> with the token from the previous step:
`curl -X POST http://localhost:8080/api/process \
     `-H "Authorization: Bearer <YOUR_TOKEN>" \`
     `-H "Content-Type: application/json" \`
     `-d '{"text":"hello world"}'`

Expected Output: {"result": "DLROW OLLEH"}


Security Features
    Password Hashing: BCrypt is used to secure user passwords in the database.
    Internal Security: Service B validates the X-Internal-Token header. Direct unauthorized access to Service B is denied (403 Forbidden).
    Data Persistence: Every successful transformation is logged in the processing_log table with the User ID and timestamps.
    
Shutdown

To stop and remove containers:
`docker compose down`
