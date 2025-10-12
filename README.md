# Spring Boot Kafka Producer MongoDB

A distributed inventory management application demonstrating integration between Spring Boot, Apache Kafka, and MongoDB.

## Overview

This application provides a web-based inventory management system that:

- Stores inventory data in MongoDB
- Publishes price discount events to Apache Kafka when prices are reduced
- Provides REST API and Web UI for managing inventory
- Uses Swagger for API documentation

## Tech Stack

- **Java 21**
- **Spring Boot 3.5.6**
- **Apache Kafka** (via Spring Integration Kafka 6.5.2)
- **MongoDB 8.0**
- **Freemarker** (for web templates)
- **Swagger/Springfox** (for API documentation)
- **Lombok** (for reducing boilerplate code)
- **Docker Compose** (for infrastructure)

## Architecture

The application follows a distributed architecture:

1. **Web Layer**: Controllers handle HTTP requests and serve Freemarker templates
2. **Service Layer**: Kafka producers publish events when prices change
3. **Data Layer**: MongoDB repositories manage inventory persistence
4. **Messaging**: Kafka handles asynchronous price update events

## Prerequisites

- Java 21 or higher
- Maven 3.6.3 or higher
- Docker and Docker Compose

## Getting Started

### 1. Start Infrastructure Services

Start MongoDB, Kafka, and Zookeeper using Docker Compose:

```bash
docker compose up -d
```

This will start:

- **Zookeeper** on port 2181
- **Kafka** on port 9092
- **MongoDB** on port 27017
- **Mongo Express** (MongoDB Web UI) on port 8081

### 2. Build the Application

```bash
mvn clean package
```

### 3. Run the Application

```bash
mvn spring-boot:run
```

Or run the JAR directly:

```bash
java -jar target/springboot-kafka-producer-mongodb-0.0.1-SNAPSHOT.jar
```

The application will start on `http://localhost:8080`

## Application Features

### Web Interface

- **Home Page** (`/`): View all inventory items
- **Create** (`/create`): Add new inventory items
- **Edit Stock** (`/editStock/{id}`): Update product stock
- **Edit Price** (`/editPrice/{id}`): Update product price (triggers Kafka event if price is reduced)
- **Delete** (`/delete/{id}`): Remove inventory item

### REST API

#### Get Inventory by ID

```
GET /inventory/getInventoryByCode?id={id}
```

Returns inventory information in JSON format.

### Swagger Documentation

Access API documentation at:

```
http://localhost:8080/swagger-ui/
```

## MongoDB Configuration

The application connects to MongoDB with these settings (configured in `application.properties`):

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/kafka
spring.data.mongodb.username=yuji
spring.data.mongodb.password=S3cret
```

### Mongo Express

Access Mongo Express (MongoDB Web UI) at `http://localhost:8081`

## Kafka Configuration

Default Kafka settings (can be overridden in `application.properties`):

```properties
kafka.broker.address=localhost:9092
kafka.topic=inventoryTest
```

### How Kafka Integration Works

When a product price is updated:

1. The system checks if the new price is lower than the current price
2. If it's a price reduction (discount), a message is published to Kafka
3. The message contains the product ID and new price
4. Kafka consumers (can be separate services) can listen to these discount events

## Docker Compose Services

The `compose.yml` file defines:

```yaml
services:
  - zookeeper: Kafka coordination service
  - kafka: Message broker
  - mongodb: NoSQL database
  - mongo-express: MongoDB web admin interface
```

### Managing Docker Services

```bash
# Start all services
docker compose up -d

# Stop all services
docker compose down

# View logs
docker compose logs -f

# Stop and remove volumes
docker compose down -v
```

## Project Structure

```
src/main/java/com/hendisantika/springbootkafkaproducermongodb/
├── SpringbootKafkaProducerMongodbApplication.java  # Main application class
├── controller/
│   ├── InventoryController.java                    # Web UI controller
│   └── InventoryRestController.java                # REST API controller
├── kafka/
│   ├── KafkaConfig.java                             # Kafka configuration
│   ├── KafkaUpdatePriceProducer.java               # Kafka producer implementation
│   └── UpdatePriceProducer.java                    # Producer interface
├── model/
│   └── Inventory.java                              # Inventory entity
└── repository/
    ├── InventoryRepository.java                    # MongoDB repository interface
    ├── InventoryRepositoryCustom.java              # Custom repository interface
    └── InventoryRepositoryImpl.java                # Custom repository implementation
```

## Data Model

### Inventory

```java
{
        "id":"string",           // MongoDB ObjectId
        "productName":"string",  // Product name
        "stock":int,             // Available stock quantity
        "price":double           // Product price
}
```

## Development

### Building without Tests

```bash
mvn clean compile -DskipTests
```

### Running Tests

```bash
mvn test
```

## Troubleshooting

### Docker not running

If you see "Cannot connect to Docker daemon", ensure Docker Desktop is running:

```bash
docker ps
```

### Port Already in Use

If ports 8080, 9092, 2181, or 27017 are in use, either:

1. Stop the conflicting service
2. Modify the ports in `compose.yml` and `application.properties`

### Lombok Not Working in IDE

If your IDE doesn't recognize Lombok annotations:

1. Install the Lombok plugin for your IDE
2. Enable annotation processing in IDE settings

## API Examples

### Create New Inventory

```bash
curl -X POST http://localhost:8080/create \
  -d "productName=Laptop&stock=10&price=999.99"
```

### Update Price

```bash
curl -X POST http://localhost:8080/updatePrice \
  -d "id=<product-id>&price=899.99"
```

### Get Inventory via REST

```bash
curl http://localhost:8080/inventory/getInventoryByCode?id=<product-id>
```

## Contributors

- **Hendi Santika** - hendisantika@gmail.com
- Telegram: @hendisantika34

## License

APL V2 - See http://www.apache.org/licenses/LICENSE-2.0.html

## Version

2.0
