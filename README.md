# RepsyProject

This is a sample **Spring Boot REST API** designed to simulate a minimalistic package repository system for the fictional *Repsy* programming language.

## Purpose

The purpose of this project is to simulate a minimal package management system for educational and demonstration purposes. It showcases:

- RESTful API design using Spring Boot
- Modular storage strategies
- Integration with PostgreSQL and MinIO
- Best practices in Java backend development

It is ideal for junior/mid-level backend developers to practice working with file uploads, storage abstractions, and microservice-friendly design patterns.

---

## Tech Stack

- Java 17 (LTS)
- Spring Boot
- PostgreSQL
- MinIO (Object Storage)
- Docker
- Maven

---

## Project Structure

```

src/main/java/com/example/repsy
├── config                # Configuration classes for MinIO and storage strategy
├── controller            # REST controller for package upload and download
├── dto                  # Data Transfer Objects
│   ├── request
│   └── response
├── entity                # JPA entities (e.g., Package)
├── exception             # Custom exceptions and global handler
├── repository            # Spring Data repository
├── service               # Business logic layer
│   ├── abstract
│   └── concrete
├── storage               # Storage service implementations
│   ├── FileStorageService
│   ├── ObjectStorageService
│   └── StorageService (interface)
└── RepsyApplication      # Spring Boot main entry

```

---

## Features

- Upload endpoint for `.rep` packages and `meta.json` files
- Download endpoint for fetching uploaded files
- Two interchangeable storage strategies:
  - Filesystem storage
  - Object storage (e.g., MinIO)
- PostgreSQL database integration
- Dockerized Spring Boot application
- Modular architecture with reusable libraries

---

## REST API Endpoints

### Upload Package

```

POST /{packageName}/{version}

````

**Request Body:**
- `multipart/form-data` with:
  - `package.rep` (binary file)
  - `meta.json` (application/json)

 *Only valid file types and structures are accepted. Invalid submissions are rejected.*

### Sample `meta.json`

```json
{
  "name": "mypackage",
  "version": "1.0.0",
  "author": "John Doe",
  "dependencies": [
    { "package": "even", "version": "3.4.7" },
    { "package": "math", "version": "4.2.8" },
    { "package": "std", "version": "1.2.0" },
    { "package": "repsy", "version": "1.0.3" },
    { "package": "client", "version": "3.3.0" }
  ]
}
````

### Download Package

```
GET /{packageName}/{version}/{fileName}
```

Returns the requested file if available in storage.

---

## 🗃 Storage Configuration

In `application.properties`:

```properties
storage.strategy=file-system  # or object-storage
```

This can also be overridden using an environment variable:

```env
STORAGE_STRATEGY=object-storage
```

| Strategy       | Description                |
| -------------- | -------------------------- |
| file-system    | Stores files on local disk |
| object-storage | Uses MinIO as backend      |

---

## Configuration Example

Example `.env` file:

```env
STORAGE_STRATEGY=object-storage
MINIO_URL=http://localhost:9000
POSTGRES_URL=jdbc:postgresql://localhost:5432/repsy
```

---

## Docker Instructions

Build and run the app:

```bash
docker build -t repsy-api .
docker run -p 8080:8080 repsy-api
```

Make sure PostgreSQL and MinIO are running (or use Docker Compose).

---

## Requirements

* Java 17+
* Spring Boot
* PostgreSQL
* Docker
* MinIO
* Maven
* Public GitHub repository with proper Git history
* Libraries published to Repsy Maven repository
* Dockerized app published to Repsy Docker registry

---

## Acceptance Criteria

* Two REST endpoints are exposed
* PostgreSQL is used for metadata persistence
* Two modular storage strategies
* Dockerized and developer-friendly
* Complies with HTTP status codes and API best practices
* Validates and rejects invalid inputs

---

## Out of Scope

* No authentication/security
* No frontend interface
* No deep validation of `.rep` content beyond format

---

## Evaluation Process

* Docker container is pulled and tested
* REST API is validated with valid and invalid inputs
* Code quality and structure are reviewed
* Modular libraries are examined

---

## Contributing

Contributions are welcome! To contribute:

1. Fork the repository
2. Create a feature/bugfix branch
3. Commit your changes
4. Submit a Pull Request

Make sure your changes are documented and tested.

---

## License

This project is licensed under the **MIT License**.

