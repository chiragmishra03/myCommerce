# myCommerce AI Agent Guidelines

## Architecture Overview
- **Framework**: Spring Boot 4.0.3 with Java 21
- **Data Layer**: JPA/Hibernate with MySQL, Flyway migrations, soft deletes via `@SQLDelete` and `@SQLRestriction("deleted_at IS NULL")`
- **Mapping**: MapStruct for DTO-entity conversions (e.g., `ProductMapper`)
- **Response Format**: All API responses use `APIStructure<T>` wrapper with `success`, `message`, `data` fields
- **Entities**: Extend `BaseEntity` for auditing (`createdAt`, `updatedAt`, `deletedAt`)
- **Domain Model**: Categories → Products (many-to-one), Orders ↔ Products (many-to-many via `OrderProducts`), Reviews (product+order)

## Key Patterns
- **Soft Deletes**: All entities support soft delete; repositories inherit from `JpaRepository` but queries exclude deleted records
- **DTOs**: Separate request/response DTOs (e.g., `CreateProductRequestDTO`, `GetProductResponseDTO`)
- **Services**: Business logic in services, injected with repositories and mappers
- **Controllers**: REST endpoints return `ResponseEntity<APIStructure<?>>`
- **Enums**: `OrderStatus` (PENDING, DELIVERED, CANCELLED, SHIPPED)
- **Data Types**: Prices as `Integer` (cents), ratings as `BigDecimal`, quantities as `Integer`

## Developer Workflows
- **Run App**: `./gradlew bootRun` (includes New Relic agent via JVM args)
- **Tests**: `./gradlew test` (JUnit 5, includes Flyway test starter)
- **Build**: `./gradlew build` (generates MapStruct impls in `generated/`)
- **Migrations**: Flyway scripts in `src/main/resources/db/migration/` (e.g., `V1__init_schema.sql`)
- **Environment**: Load `.env` file via `dotenv-java` in `MyCommerceApplication.main()`

## Conventions
- **Imports**: Use Lombok (`@Data`, `@Builder`, etc.) and MapStruct processors
- **Exception Handling**: Custom `ResourceNotFound` exception, global handler in `GlobalExceptionHandler`
- **Dependencies**: Injected via `@RequiredArgsConstructor`
- **File Structure**: `controller/` → `service/` → `repositories/` + `mappers/`, DTOs in `dto/`
- **Naming**: Kebab-case endpoints (e.g., `/api/v1/product/get-all`), camelCase methods

## Examples
- **Entity Creation**: `Product.builder().title("...").category(category).build()`
- **Mapping**: `@Mapping(source = "categoryId", target = "category")` with custom mapper for lazy-loaded entities
- **Repository Query**: `findByCategory(String category)` for custom finds
- **API Response**: `APIStructure.success(data, "Fetched successfully")`
