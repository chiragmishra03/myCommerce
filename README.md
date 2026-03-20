# myCommerce

A Spring Boot-based e-commerce REST API for managing products, categories, orders, and reviews.

## Features

- **Product Management**: Create, read, update, and delete products with categories.
- **Category Management**: Organize products into categories.
- **Order Management**: Handle customer orders with multiple products.
- **Review System**: Allow customers to review products based on orders.
- **Soft Deletes**: All entities support soft deletion for data integrity.
- **Auditing**: Automatic creation and update timestamps.

## Tech Stack

- **Framework**: Spring Boot 4.0.3
- **Language**: Java 21
- **Database**: MySQL with Flyway migrations
- **ORM**: JPA/Hibernate
- **Mapping**: MapStruct
- **Build Tool**: Gradle
- **Monitoring**: New Relic
- **Other**: Lombok, dotenv-java

## Prerequisites

- Java 21
- MySQL 8.0+
- Gradle 9.3+

## Setup

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd myCommerce
   ```

2. **Configure environment variables**:
   Create a `.env` file in the root directory with:
   ```
   SPRING_SERVER_PORT=8080
   DATASOURCE_USERNAME=your_mysql_username
   DATASOURCE_PASSWORD=your_mysql_password
   ```

3. **Set up MySQL database**:
   Ensure MySQL is running and create a database named `mycommerce`.

4. **Run Flyway migrations**:
   ```bash
   ./gradlew flywayMigrate
   ```

## Running the Application

- **Development**: `./gradlew bootRun` (includes New Relic agent)
- **Production Build**: `./gradlew build`
- **Tests**: `./gradlew test`

## API Endpoints

### Categories
- `GET /api/v1/categories` - Get all categories
- `POST /api/v1/categories` - Create a category
- `DELETE /api/v1/categories/{id}` - Delete a category

### Products
- `GET /api/v1/product/get-all` - Get all products
- `GET /api/v1/product/{id}` - Get product by ID
- `GET /api/v1/product/{id}/details` - Get product with category details
- `GET /api/v1/product/search?categoryName={name}` - Search products by category
- `POST /api/v1/product` - Create a product
- `PUT /api/v1/product/{id}` - Update a product
- `DELETE /api/v1/product/{id}` - Delete a product

### Orders
- `GET /api/v1/orders/get-all?orderItems=true` - Get all orders (with/without items)
- `GET /api/v1/orders/{id}` - Get order by ID
- `POST /api/v1/orders/create` - Create an order
- `DELETE /api/v1/orders/{id}` - Delete an order

### Reviews
- `GET /api/v1/reviews` - Get all reviews
- `GET /api/v1/reviews/{id}` - Get review by ID
- `GET /api/v1/reviews/product/{productId}` - Get reviews for a product
- `GET /api/v1/reviews/order/{orderId}` - Get reviews for an order
- `POST /api/v1/reviews` - Create a review
- `DELETE /api/v1/reviews/{id}` - Delete a review

## Response Format

All API responses follow this structure:
```json
{
  "success": true,
  "message": "Operation successful",
  "data": { ... }
}
```

## Data Types

- **Prices**: Stored as integers (cents) in the database.
- **Ratings**: BigDecimal for precision.
- **Quantities**: Integers.

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Open a Pull Request

## License

This project is licensed under the MIT License.
