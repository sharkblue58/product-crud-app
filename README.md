# CRUD Spring Boot Application

This is a CRUD (Create, Read, Update, Delete) web application built using Spring Boot, MySQL, Thymeleaf, and MVC architecture. The application allows users to manage a list of entities through a web interface.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Setup](#setup)
- [Running the Application](#running-the-application)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

## Introduction

This project is a simple web-based application that demonstrates the CRUD operations using Spring Boot and MySQL as the database. Thymeleaf is used as the template engine for rendering views, and the application follows the MVC (Model-View-Controller) architecture to separate concerns.

## Features

- Create new entities
- View a list of entities
- Update existing entities
- Delete entities
- User-friendly web interface

## Technologies Used

- Spring Boot
- Spring MVC
- Spring Data JPA
- MySQL
- Thymeleaf
- Bootstrap (for frontend styling)

## Prerequisites

- IDE ( VScode , Intellij , Eclipse , etc .. )
- Java 11 or higher
- Maven
- MySQL Server

## Setup

1. **Clone the repository:**

    ```bash
    git clone https://github.com/your-username/crud-spring-boot.git
    cd crud-spring-boot
    ```

2. **Configure the database:**

    Create a database named `crud_db` in your MySQL server and update the `application.properties` file with your database credentials.

    ```properties
    server.error.whitelabel.enabled=false
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.url=jdbc:mysql://localhost:3306/beststore
    spring.datasource.username=root
    spring.datasource.password=yourPassword
    spring.jpa.show-sql=true
    spring.jpa.hibernate.ddl-auto=update
    ```

3. **Build the project:**

    ```bash
    mvn clean install
    ```

## Running the Application

Run the application using the following command:

```bash
mvn spring-boot:run
