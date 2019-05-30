# Trading account backend service

Trading account backend service to support a web application that allows user to view transactions on any of the account they hold

## Getting Started

Run following command to start the back end service

$ mvn spring-boot:run

### Prerequisites

* Java 11
* Spring boot
* Maven
* H2 inmemory database

## API Endpoints
* /api/{userId}/accounts
 - will return a list of accounts for the userId
 
* /api/{accountId}/txns
 - returns a list of account transactions for the selected account

### Installing

Run command to generate jar file

$ mvn clean install 

## Running the tests

$ mvn test

## Domain model object
* User
* Account
* AccountTransaction

## Authors
* **John Luo** 



