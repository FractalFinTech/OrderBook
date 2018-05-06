# Order Book
Order Book is a subsystem of an exchange. It is at its initial stage of development at the moment.

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
curl -d  http://localhost:8080/market/add/item/Apple
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
