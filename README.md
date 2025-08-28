# Spring Boot Pagination & Sorting Demo 🚀

A **starter kit for implementing pagination and sorting** in **Spring Boot** using **clean DTO responses** and **best practices**.
This version demonstrates:
- ✅ **Custom `PaginatedResponse<T>` wrapper** for consistent API responses
- ✅ **Pagination and sorting with Spring Data JPA**
- ✅ **Swagger/OpenAPI integration** for interactive API documentation

Perfect for developers who want to quickly integrate **pagination & sorting** features into their Spring Boot projects with a clean, extensible structure.

---

## ✨ Features

- ✅ Layered architecture: **Controller → Service → Repository**
- ✅ DTO pattern for cleaner APIs
- ✅ Custom `PaginatedResponse<T>` class for a unified response format
- ✅ Multiple **pagination & sorting** strategies:
   - Basic pagination
   - Pagination + single-field sorting
   - Pagination + multiple sorting criteria
   - Pageable integration with Spring's built-in handling
- ✅ Interactive API documentation with **Swagger/OpenAPI**
- ✅ In-memory **H2 database** for quick testing

---

## ⚠️ Important Note on Best Practices

This **demo version** focuses on **pagination, sorting, and DTO structure** for educational purposes.  

For production-ready solutions with:
- ✅ Advanced DTO mapping patterns
- ✅ Layered validation
- ✅ Centralized error handling & response wrappers
- ✅ JWT authentication & authorization

👉 Check out:
- 🔗 My detailed article on **[Dev.to](https://dev.to/gianfcop)**
- 💼 The **Pro Starter Kit on [Gumroad](https://gianfcop.gumroad.com/)**:

---

## ⚙️ Requirements

- **Java** 17+
- **Maven** 3.8+
- IDE: IntelliJ IDEA or VS Code

---

## 🚀 Quick Start

1. Clone the repo:
   ```bash
   git clone https://github.com/gianfcop/springboot-pagination-sorting-demo.git
   ```
2. Run the application:
   ```bash
   mvn spring-boot:run
   ```
3. Open Swagger UI:
   ```
   http://localhost:8080/swagger-ui/index.html
   ```

## 🏗️ Project Structure
   ```
      src/main/java/com/starterkit/api
   ├── config/          → Swagger configuration
   ├── controller/      → REST Controllers
   ├── dto/             → DTO classes (ProductRequest, ProductResponse, PaginatedResponse<T>, etc.)
   ├── entity/          → JPA Entities (Product, Category)
   ├── mapper/          → Mappers
   ├── repository/      → Spring Data JPA Repositories
   ├── service/         → Business logic and pagination handling
   └── SpringBootPaginationSortingDemoApplication.java
   ```

## 📑 API Documentation

Interactive Swagger UI available at:
   ```
   http://localhost:8080/swagger-ui/index.html
   ```

Endpoints included:
- `POST /api/categories` → Create category
- `GET /api/categories` → Get all categories
- `POST /api/products` → Create a product
- `GET /products` → Get all products (basic pagination)
- `GET /products/sorted` → Pagination + single-field sorting
- `GET /products/multi-sort` → Pagination + multiple sorting criteria
- `GET /products/query-sort` → Pageable-based pagination and sorting
- `GET /products/by-category/{categoryId}` → Products by category with pagination
- `GET /products/top-priced` → Top N most expensive products


## 🧪 Testing

This version is minimal and does not include automated tests, but you can easily test the API using Swagger UI or tools like Postman.

## ✅ Want More Features?

Check out **Full Pro Starter Kit** (available on [Gumroad](https://gianfcop.gumroad.com/)) with:
- JWT Security
- DTOs and Mappers
- Error Handling and Response Wrappers
- Logging, Profiles, and CI/CD ready structure
- Test Suite (MockMvc, Validation)
- Available on Gumroad

Also read the full tutorials on [Dev.to](https://dev.to/gianfcop).


## 📜 License

This project is licensed for educational and personal use only.
Do not redistribute or resell.