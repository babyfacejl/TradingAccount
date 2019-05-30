# Account and transaction enquiry backend service

Account and transaction enquiry backend service to support a web application that allows user to view transactions on any of the account they hold

## Prerequisites

* Java 11
* Spring boot
* Maven
* H2 inmemory database

## Assumption
 * An user will not have hundreds or thousands of accounts and no sorting is required. Otherwise would apply query limits or pagination for performance concerns.
 * An account will not have hundreds or thousands of transactions and no sorting is required. Otherwise would apply query limits or pagination for performance concerns.

## Domain model object
* Account
```$xslt
Assumption
-There is UserId column in Account table 
-Not sure if Balance date gets updated to the current date or by some other overnight jobs.
```
* AccountTransaction
```
Assumption
-There is a many to one relationship between AccountTransaction and Account
-Only have 1 Amount column. If DebitCredit column is Credit I assume the Amount would become Credit Amount. if DebitCredit column is Debit I assume the Amount would become Debit Amount.

```

## API Endpoints
* /api/users/{userId}/accounts 
```
 returns a list of accounts for the userId
 Can use postman to do GET
 e.og http://localhost:8080/api/users/1/accounts
```
* /api/accounts/{accountId}/txns 
```
returns a list of account transactions for the selected account
Can use postman to do GET 
e.g http://localhost:8080/api/accounts/1/txns
```

## Getting Started

Git clone the repo
```
$ git clone https://github.com/babyfacejl/TradingAccount.git
```

## Start backend service

Run following command to start the back end service
```
$ mvn spring-boot:run
```

## Installing

Run maven command to generate jar file
```
$ mvn clean install 
```
## Running the tests

$ mvn test

## Authors
* **John Luo** 



