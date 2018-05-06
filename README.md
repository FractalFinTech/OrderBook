# Order Book
Order Book is a subsystem of an exchange, normally is called the trade engine. 

It is at its initial stage of development at the moment.

## Purpose

- There is not much open source trade engine out there at the moment, with that, this project would like to address that by the creation of this project. 
- Have a trade engine that can be used by anyone wanting to understand what a trade engine looks like, or simply use it in an exchange system.

## Objective

- Be able to handle large amount of transactions per hour using cheap/affordable off-the-shelf resources.

## Building It

This project uses Maven to build the project.

```
mvn package
```

To run the package:

```
java -jar target/orderbook-0.0.1-SNAPSHOT.jar
```

## Test Using Curl

Add item to order book list
```
curl http://localhost:8080/market/add/item/Apple
```

Get list
```
curl http://localhost:8080/market/list/get
```


Get market offer list
```
curl -d "name=Apple" -X POST http://localhost:8080/market/offer/get
```

Get market bid list
```
curl -d "name=Apple" -X POST http://localhost:8080/market/bid/get
```




## Refences

- https://en.wikipedia.org/wiki/Order_book_(trading)
- https://www.youtube.com/watch?v=zeg3B7gMCNQ
- https://www.investopedia.com/terms/o/order-book.asp
