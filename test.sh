#!/bin/sh

curl http://localhost:8080/market/add/item/diner0

curl -H "Content-Type: application/json" -d '{"name":"diner0", "price":"100", "qty":"20"}' -X POST http://localhost:8080/market/bid/add
