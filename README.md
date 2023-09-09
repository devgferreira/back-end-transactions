# Back-End Transactions

### About

This project is focused on educational purposes and is implemented using Java, Spring Boot, Swagger, MongoDB, Lombok, and Model Mapper. It simulates the creation of users and money transactions between them.

### Instructions

- Recommendations: Use Postman to create transactions.

### URLs

| HTTP Method | URL                                     | Description                             |
|-------------|-----------------------------------------|-----------------------------------------|
| GET         | http://localhost:8080/users            | Returns all registered users            |
| GET         | http://localhost:8080/{document}            | Returns the document user           |
| POST        | http://localhost:8080/users            | Creates a new user                      |
| POST        | http://localhost:8080/transaction      | Perform a transaction between users    |
| SWAGGER     | http://localhost:8080/swagger-ui/index.html# | Swagger Interface                   |

### Observation
- If you are using Postman to create transactions, I recommend using the following request body:
 ```json
{
    "sender": "User ID",
    "receiver": "User ID",
    "amount": 1
}
```
- Please note that you don't need to include an ID as it is generated automatically.


