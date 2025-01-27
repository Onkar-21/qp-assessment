# Grocery Booking Application Using Spring Boot

This is a simple overview of how to configure and run the project.

## Description
The Grocery Booking Application provides APIs for both Admin and User profiles. These profiles are configured with Spring Security, ensuring that only the corresponding profile has access to the related APIs. The login API returns a JWT token, which needs to be included in subsequent secured API calls to access them. The codebase includes the related Postman collection, where all the relevant APIs for both Admin and User are configured. The Postman collection also contains environment variables to store the JWT token returned by the login API, so users do not have to manually copy and paste the token in subsequent secured calls.

The project's requirements and features are as follows:

### Admin APIs:
1. Admins have access to the following APIs:
   - Add new grocery items to the system.
   - View existing grocery items.
   - Remove grocery items from the system.
   - Update details (e.g., name, price) of existing grocery items.
   - Manage inventory levels of grocery items.

### User APIs:
2. Users can access the following APIs:
   - View the list of available grocery items.
   - Ability to book multiple grocery items in a single order.

## Dependencies

To run this project, the following resources are needed:
- JDK 17 or higher
- Maven
- Spring Tool Suite (STS)
- MySQL Database
- Postman for API testing
- Docker for containerization

## Running the Project

### Steps to Run the Project:

1. Fork the codebase from GitHub to your local directory:
  ```
   $ git clone https://github.com/Onkar-21/qp-assessment.git
  ```
2. Install all the Maven dependencies using the following command:
  ```
   $ mvn clean install
  ```
3. Create a database in your database software (MySQL).
4. Run the project as a Spring Boot application.

### API Endpoints
Admin API Endpoints
- POST /auth/register: regiser a user as admin
- POST /auth/login: login for user
- POST /app/admin/saveGroceryItem: Add a new grocery item.
- PATCH /app/admin/updateGroceryItem: Update the grocery item
- PATCH /app/admin/updateGroceryStock: Update the grocery stock
- GET /app/admin/getAllGroceryItems: View all grocery items.
- DELETE /app/admin/removeGroceryItem/{id}: Remove a grocery item by ID.

User API Endpoints
- POST /auth/register: regiser a user as normal user
- POST /auth/login: login for user
- POST /app/user/orderGroceryItems: Place an order for multiple grocery items.
- GET /app/user/getAllGroceryItems: View the list of available grocery items.

### Containerization (Using Docker)
- You can also containerize the application using Docker
- Generate the jar file for the application:
  ```
  $ mvn clean install
- Run the following command to start the containers(Java container + SQL container)
  ```
  $ docker compose up -d
  ```
- To view the logs use:
  ```
  $ docker compose logs -f
  ```
- To view running container:
  ```
  $ docker compose ps
  ```  
- To stop the container:
  ```
  $ docker compose stop
  ```
