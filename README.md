# Target API Demo for PostgreSQL

This is a simple Spring Boot application with Kotlin designed to handle RESTful endpoints for parsing and adding a spreadsheet to PostgreSQL. The spreadsheet is parsed using Apache POI library.

## Deployment

- **target-demo-api**:
  - This is deployed using a simple [docker compose](docker-compose.yaml) in my home data center on a Virtual machine with proxmox.
  - This can be deployed using terraform and kubernetes on a choice of cloud as well..

## Features

- **RESTful Endpoints**:
  - `post products`: Accepts a spreadsheet with list of product information.
  - `get products`: Allows to retrieve recently pushed products from postgreSQL database.
  - `get sample-file`: Allows to retrieve an example spreadsheet file with dummy products listed in it.
- **Hexagonal Architecture**:
  - Simple architecture is used as no Events are needed but we can expand based on need.
- **Containerized Components**:
  - Spring Boot service
  - PostgreSQL database

## Technologies Used

- **Spring Boot** (Kotlin)
- **PostgreSQL** (SQL/NoSQL database)
- **Docker** (Containerization)

## REST Endpoints (More detail on [Swagger page](https://target-demo.spinachsoftware.com/swagger-ui/index.html))

### 1. `POST /products`

- **Description**: Accepts a spreadsheet with list of product information.

### 2. `GET /products`

- **Description**: Retrieves products from the database.

## Getting Started Locally

### 1. Clone the Repository

```bash
git clone https://github.com/your-repo/target-demo.git
cd target-demo
```

### 2. Build and Run Services

- **Spring Boot Application**:
  ```bash
  docker compose up -d
  ```

### 4. Access the Service

- Base URL: `http://<your-machine>:8080`

## Future Enhancements

- Add IntegrationTest and AcceptanceTest (use JSONAssert to compare API results) along with basic JUnit test.

## License

This project is licensed under the [MIT License](LICENSE).

---

Happy coding! 🚀
