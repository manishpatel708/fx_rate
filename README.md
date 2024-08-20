Overview: 

This project is a Spring Boot-based microservice designed to fetch and store foreign exchange (FX) rates from an external API provided by the European Central Bank. The service stores this data in a MySQL database using Flyway for database migrations. It provides RESTful endpoints to access the stored exchange rates.

Features :

Fetch FX Rates: Retrieves FX rates from USD to multiple target currencies (EUR, GBP, JPY, CZK) from an external API.
Store and Retrieve Rates: Stores the retrieved FX rates in a MySQL database and provides endpoints to access these rates.

Requirements
Java 17
Spring Boot 3.3.1
MySQL Database
Flyway for database migrations
Maven for dependency management

Endpoints :

** Get FX Rates
    URL: /fx
    Method: GET
    Description: Returns the FX rates from USD to EUR, GBP, JPY, CZK. The response includes the date of conversion,             source currency, target currency, and exchange rate.

Sample Response:

    {
      "date": "2024-08-20",
      "source": "USD",
      "rates": [
        {
          "target": "GBP",
          "value": "0.77206"
        }
      ]
    }

** Get Latest FX Rates for a Specific Currency
URL: /fx/{targetCurrency}
Method: GET

latest FX rates from USD to the specified target currency, with a step of 1 day.
Sample Response:

    {
      "source": "USD",
      "rates": {
        "2024-08-20": {
          "target": "GBP",
          "value": "0.77206"
        },
        "2024-08-19": {
          "target": "GBP",
          "value": "0.77206"
        },
        "2024-08-18": {
          "target": "GBP",
          "value": "0.77431"
        }
      }
    }

    
Setup and Installation
1. Clone the Repository

git clone https://github.com/manishpatel708/fx-rate-microservice.git
cd fx-rate-microservice

2. Set Up MySQL Database
Install MySQL on your local machine or use an existing MySQL instance.
Create a database for the project:

CREATE DATABASE fx_rate_db;
3. Configure application.properties
Update the src/main/resources/application.properties file with your MySQL credentials:

4. Run Flyway Migrations
Flyway will automatically run the migrations when the application starts. Ensure your database is correctly configured.

5. Build and Run the Application

./mvnw clean install
./mvnw spring-boot:run

7. Access the Endpoints
Open a browser or use Postman to test the API.
GET http://localhost:8080/fx
GET http://localhost:8080/fx/{targetCurrency}
Testing
This project includes extensive testing for all functionalities. To run the tests, execute:
