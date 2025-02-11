# Event-Driven Architecture with Camunda and Kafka

## Overview
This project uses **Camunda 8** with **Spring Boot** to implement an **event-driven architecture**. The backend is built with **Java 21**, leveraging **Kafka** as the event broker.

## Prerequisites
Ensure you have the following installed:
- **Docker & Docker Compose** (for running Camunda & Kafka)
- **Java 21** (for running the Spring Boot application)
- **Maven** (for building the application)

## Setup and Run
### 1️⃣ Start Camunda Services
Run the following command to start Camunda services:
```sh
cd docker-compose/camunda-8.6
docker-compose up -d
```
This will start the required Camunda services in the background.

### 2️⃣ Start Kafka
Navigate to the project root and start Kafka:
```sh
docker-compose up -d
```
This will bring up the Kafka broker and dependencies.

### 3️⃣ Run the Spring Boot Application
Once Camunda and Kafka are running, start the application:
```sh
mvn spring-boot:run
```

## API Endpoints
### 🚴 **Bike Registration API**
- **Endpoint:** `POST /api/bike`
- **Description:** Registers a new bike and starts the **BikeRegSaga** workflow in Camunda.

## Deploying Camunda Workflow
- The Camunda BPMN file used for the saga process is **`BikeRegSaga.bpmn`**.
- Deploy the BPMN file to the Camunda server through the **Camunda Modeler** or using the REST API.

## Technologies Used
- **Java 21**
- **Spring Boot 3**
- **Camunda 8**
- **Apache Kafka**
- **Docker & Docker Compose**

## Next Steps
- Add monitoring with **Camunda Optimize**.
- Implement retries and error handling in Kafka consumers.
- Expand the event-driven flow with more microservices.

---
### 💡 Need Help?
Feel free to raise an issue or contribute to the project!

