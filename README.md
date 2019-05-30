# Trading account backend service

Trading account backend service to support a web application that allows user to view transactions on any of the account they hold

## Prerequisites

* Java 11
* Spring boot
* Maven
* H2 inmemory database

## Getting Started

1. Git clone the repo
```
$ git clone https://github.com/babyfacejl/TradingAccount.git
```

## Start backend service

Run following command to start the back end service
```
$ mvn spring-boot:run
```

## API Endpoints
* /api/{userId}/accounts - returns a list of accounts for the userId
* /api/{accountId}/txns - returns a list of account transactions for the selected account

## Installing

1. Run maven command to generate jar file
```
$ mvn clean install 
```
## Running the tests

$ mvn test

## Domain model object
* User
* Account
* AccountTransaction

## Authors
* **John Luo** 



