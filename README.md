myRetail Restful service

Technologies
JDK 1.8.0

Mongo 3.6.3

Maven 3.5.2

SpringBoot 2.0.1.RELEASE

EhCache

Mongo DB database and collection
database=myretail

mongodb host=localhost

mongodb port=27017

mongo collection name = prices

use myretail

db.prices.insert({ "_id" : 13860428, "value" : 13.49, "currencyCode" : "USD" })

db.prices.insert({ "_id" : 16696652, "value" : 32.0, "currencyCode" : "USD" })

Build, Test and Run application
cd myretail

Then run

mvn clean package

Then run the jar

java -jar target/myretail-0.0.1-SNAPSHOT.jar

Application will start running on port 8080

Calling myretail api services
Performing GET request on http://localhost:8080/products/13860428 will return json object with product information and pricing information.

GET http://localhost:8082/products/13860428

Response:-

{ "productId": "13860428", "title": "The Big Lebowski (Blu-ray)", "price": { "value": 13.49, "currencyCode": "USD", "id": "13860428" } }

To perform PUT operation, send JSON object with updated price in request body, it will return JSON object with updated pricing information.

PUT http://localhost:8080/product/13860428

Request Body:-

{ "id": 13860428, "productPrice": { "price": 10.00, "currencyCode": "USD" } }

Response :-

{ "productId": "13860428", "title": "The Big Lebowski (Blu-ray)", "price": { "value": 10.00, "currencyCode": "USD", "id": "13860428" } }