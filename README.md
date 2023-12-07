# MovieLibrary

> AUTHOR: SZYMON KORNIK <br>
> LINKEDIN: https://www.linkedin.com/in/szymon-kornik-3b12ba217/ <br>
> WEBSITE: https://www.szymonkornik.com/ <br>

## Specification

- Spring Boot web application
- Architecture: Modular monolith hexagonal architecture 
- MySQL database for storing users and movies.
- Facade's design pattern
- High coverage with unit tests and integration tests
- MockMvc was used to test the controlle.
- Full containerization in Docker

## Tech

MovieLibrary is developed using following technologies: <br>

Core: <br>
![image](https://img.shields.io/badge/17-Java-orange?style=for-the-badge) &nbsp;
![image](https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white) &nbsp;
![image](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring) &nbsp;
![image](https://img.shields.io/badge/Hibernate-59666C.svg?style=for-the-badge&logo=Hibernate&logoColor=white) &nbsp;
![image](https://img.shields.io/badge/MySQL-4479A1.svg?style=for-the-badge&logo=MySQL&logoColor=white) &nbsp;
![image](https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white) &nbsp;

Testing:<br>
![image](https://img.shields.io/badge/Junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white) &nbsp;
![image](https://img.shields.io/badge/Mockito-78A641?style=for-the-badge) &nbsp;
![image](https://img.shields.io/badge/Testcontainers-9B489A?style=for-the-badge) &nbsp;


## Installation and run

### Requirements:
- [Docker](https://www.docker.com/products/docker-desktop/) to run.

### To run the application:
- Just run following command, and wait for containers to be pulled up and started.

``
docker compose up
``

- Alternatively you can run docker-compose file through you IDE

After everything builds and ready, you can start the application and test the application using [Postman](https://www.postman.com/) 
or use <a href="http://localhost:8080/swagger-ui/index.html#/">Swagger</a>.


## Rest-API Endpoints

Application provides five endpoints: for retrieving all offers, for retrieving specific offer by ID, for saving an offer, for registration and login. Please follow the specification below:

Service url: http://localhost:8080

| HTTP METHOD | Endpoint           |          REQUEST            |   RESPONSE   |                 Function                    |
|-------------|--------------------|-----------------------------|--------------|---------------------------------------------|
| GET         |  /movies           |              -              |     JSON     | Retrieve all available movies               |
| GET         |  /movies/{movieId} |   PATH VARIABLE (offerId)   |     JSON     | Retrieve an movie for a given ID            |
| POST        |  /movies           |      BODY-JSON (offer)      |     JSON     | Add new movie                               |
| PUT         |  /movies           |      BODY-JSON (offer)      |     JSON     | Update existing movie                       |
| DELETE      |  /movies/{movieId} |   PATH VARIABLE (offerId)   |     JSON     | Delete movie                                |
| POST        |  /register         | BODY-JSON (registerRequest) |     JSON     | Register a new user                         |
| POST        |  /token            |  BODY-JSON (loginRequest)   |     JSON     | Generate an access token with login details |
