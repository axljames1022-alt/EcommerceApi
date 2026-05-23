# EcommerceApiV2

## Project Overview
REST API for managing products in an e-commerce system. Built with Spring Boot using in-memory storage.
Supports CRUD operations, filtering by category, searching by name, and input validation.

## Setup Instructions

### Prerequisites
- Java 17 or higher
- IntelliJ IDEA or any Java IDE
- Postman or similar tool for testing API

### How to Run the Application
1. Open the project in IntelliJ IDEA
2. Make sure you're on the `feat/project-setup` branch
3. Run the `EcommerceApiV2Application` class
4. The API will run on `http://localhost:8080`

## API Endpoint Reference

| Method | Path | Description | Expected Response |
| --- | --- | --- | --- |
| GET | `/api/v1/products` | Get all products | 200 OK - List of products |
| GET | `/api/v1/products/{id}` | Get product by ID | 200 OK - Product or 404 Not Found |
| GET | `/api/v1/products/category/{category}` | Get products by category | 200 OK - List of products |
| GET | `/api/v1/products/search?name={name}` | Search products by name | 200 OK - List of products |
| POST | `/api/v1/products` | Create a new product | 201 Created or 400 Bad Request |
| PUT | `/api/v1/products/{id}` | Update a product completely | 200 OK or 404 Not Found |
| PATCH | `/api/v1/products/{id}` | Partially update a product | 200 OK |
| DELETE | `/api/v1/products/{id}` | Delete a product | 204 No Content |

## Testing

**API Test Results:**

![GET Test](test1.png)
![POST Test](test2.png)
![POST Test](test3.png)
## Sample Request/Response Examples

### Create Product - POST /api/v1/products
**Request:**
```json
{
  "name": "Wireless Mouse",
  "price": 250.50,
  "category": "Electronics",
  "stockQuantity": 10,
  "description": "Ergonomic wireless mouse",
  "imageUrl": "http://example.com/mouse.jpg"
}
```

**Success Response 201 Created:**
```json
{
  "id": 1,
  "name": "Wireless Mouse",
  "price": 250.50,
  "category": "Electronics",
  "stockQuantity": 10,
  "description": "Ergonomic wireless mouse",
  "imageUrl": "http://example.com/mouse.jpg"
}
```

### Validation Error - POST /api/v1/products
**Request:**
```json
{
  "name": "A",
  "price": -100,
  "category": "",
  "stockQuantity": -5
}
```
**Error Response 400 Bad Request:**
```json
{
  "name": "Name must be at least 3 characters",
  "price": "Price must be greater than 0",
  "category": "Category cannot be empty",
  "stockQuantity": "Stock quantity cannot be negative"
}
```

### Get All Products - GET /api/v1/products
**Request:**

```http
GET http://localhost:8080/api/v1/products
```
**Response 200 OK:**
```json
[
  {
    "id": 1,
    "name": "Wireless Mouse",
    "price": 250.50,
    "category": "Electronics",
    "stockQuantity": 10
  }
]
```
## Known Limitations
- **In-memory storage**: Data is stored in a HashMap and will be lost when the application restarts.
- **No database**: This version does not use a database like MySQL or PostgreSQL.
- **No authentication**: The API is publicly accessible without login or token.
