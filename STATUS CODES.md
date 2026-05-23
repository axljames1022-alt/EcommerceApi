# HTTP Status Codes Documentation

## Status Codes Used in EcommerceApiV2

### 200 OK
- **Endpoints**: GET /api/v1/products, GET /api/v1/products/{id}
- **Reason**: Successful request. Data returned in response body.

### 201 Created
- **Endpoint**: POST /api/v1/products
- **Reason**: Product successfully created. New resource returned.

### 204 No Content
- **Endpoint**: DELETE /api/v1/products/{id}
- **Reason**: Product successfully deleted. No response body.

### 400 Bad Request
- **Endpoint**: POST, PUT /api/v1/products
- **Reason**: Invalid input data. Triggered by @Valid annotations.
- **Handled by**: GlobalExceptionHandler.handleValidationErrors()

### 404 Not Found
- **Endpoint**: GET, PUT, DELETE /api/v1/products/{id}
- **Reason**: Product with given ID does not exist.
- **Handled by**: GlobalExceptionHandler.handleProductNotFound()

### 500 Internal Server Error
- **Endpoint**: All endpoints
- **Reason**: Unexpected server error. Caught by GlobalExceptionHandler.