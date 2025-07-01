# Gender Income Gap API

API REST built with Spring Boot to analyze gender-related statistics in Argentina using public datasets (EPH, INDEC, ENUT).

## 📊 Features

- Read raw .txt files from EPH datasets
- Parse data to extract income, gender, year, province
- Calculate gender income gaps
- Serve data via REST API

## 🛠 Technologies

- Java 17
- Spring Boot
- Maven
- RESTful API
- Git / GitHub

## 🚀 How to run

1. Clone the repo  
   git clone https://github.com/iaraAlfaro/gender-gap-api.git

2. Import as Maven project in IntelliJ or run:

   `bash
   ./mvnw spring-boot:run
3. Visit: http://localhost:8080/api/eph/sample

## 📁 Dataset Sources

- Encuesta Permanente de Hogares (EPH) - INDEC
- ENUT 2021 - INDEC
- Fundar - Índice GTI

## Author 👩‍💻

Iara Alfaro Asad  
Java Backend Developer  
[Linkedin](https://www.linkedin.com/in/iara-alfaro-asad/)
