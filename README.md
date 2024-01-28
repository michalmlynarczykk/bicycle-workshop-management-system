# Bike Workshop Management System
#### The Bike Workshop Management System is a comprehensive solution for managing bike service operations, including user registration, workshop creation, order management, and more.
#### The system is designed to facilitate efficient workflows in bike workshops, providing tools for both workshop owners and mechanics.

## Technology Stack:
- Java 21
- Spring Boot 3
- Spring (Cloud, Security, Data, Web, Validation etc.)
- Maven
- MongoDB: NoSQL database for storing workshop and order-related data.
- PostgreSQL: A relational database for managing data, such as user information.
- RabbitMQ: A message broker for handling asynchronous communication between microservices.
- JWT: JSON Web Tokens for authorization.
- OpenAPI/Swagger: API documentation.

## Features:
1. User Registration
2. Role upgrade after creating a workshop (owner) or approaved workshop join request (employee).
3. Workshop Creation: Workshop owners can create workshops, including details like name, location, and services offered.
4. Order Management: Creating and managing bike service orders, including client details, bike details, services performed, and used parts.

## Microservices Descriptions:
1. Authentication Service:
  <br>Description: Handles user registration, authentication, and role assignments.
   <br>Responsibilities:
   - User registration and authentication.
   - Role assignment (with role upgrade logic after workshop creation/join request approval event).
2. Workshop Service:
   <br>Description: Manages workshop-related operations, join requests, and order creation.
   <br>Responsibilities:
   - Workshop creation.
   - Approval or rejection of employees join requests.
3. Order Service:
   <br>Description: Manages bike service orders and updates.
   <br>Responsibilities:
   - Creation of bike service orders.
   - Update of order status, services performed, and used parts.
4. API Gateway:
   <br>Description: A gateway (Spring Cloud Gateway) for routing and managing API requests.
   <br>Responsibilities:
   - Centralized entry point for all API requests.
   - Routing to appropriate microservices.
5. Service Registry:
   <br>Description: A service registry (Eureka) for managing and locating microservices within the system. Facilitates efficient communication in a distributed environment.
   <br>Responsibilities:
   - Registration and discovery of microservices.
   - Dynamic updates of microservices' locations.