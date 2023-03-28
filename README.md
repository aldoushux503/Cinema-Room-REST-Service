# Cinema Room REST Service



## Table of Contents
* [About The Project](#about-the-project)
* [Getting Started](#getting-started)
* [Usage](#usage)
* [Tools Used](#tools-used)
* [Endpoints](#endpoints)

## About The Project
The REST service that is responsible for showing available tickets, selling them, making a refund, and getting statistics.

## Getting Started
To get started with this project, you'll need to clone the repository and install the necessary dependencies.

```bash
git clone https://github.com/aldoushux503/Cinema-Room-REST-Service.git
cd Cinema-Room-REST-Service
mvn install
```

## Usage
Once you have the project set up, you can start the server by running the following command:
```bash
mvn spring-boot:run
```
The server will start on port 8080 by default. You can access the REST API by sending requests to http://localhost:8080.

## Tools Used
* Java
* Spring Boot
* Maven
* Jackson

## Endpoints
The following endpoints are available:

### GET /seats
Returns a list of all available seats in the movie theater and and their specific price.

### POST /purchase
Allows a user to purchase a ticket for a specific seat. The request body should include the seat number.

Example request body:
```json
{
  "row": 1,
  "column": 3
}
```

### POST /return
Allows a user to return a ticket for a specific seat. The request body should include the token value.

Example request body:
```json
{
  "token": "e4bed3b2-766c-41a6-9741-73b7c377be85"
}
```

### GET /stats
Returns statistics about the movie theater avalible only with password, including the current income and the number of available seats and purchased seats.
The password is a ***super_secret*** which is the value of the request param. **(This method is not safe!!!)**

![image](https://user-images.githubusercontent.com/55160026/228284222-56909fba-dcda-4cc1-9d1a-9028b453bd03.png)

