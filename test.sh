#!/bin/sh

curl http://localhost:8080/market/add/item/diner0


printf "\n"
curl -H "Content-Type: application/json" -d '{"name":"diner0", "price":"101", "qty":"20"}' -X POST http://localhost:8080/market/bid/add

printf "\n"
curl -H "Content-Type: application/json" -d '{"name":"diner0", "price":"100", "qty":"12"}' -X POST http://localhost:8080/market/bid/add

printf "\n"
curl -H "Content-Type: application/json" -d '{"name":"diner0", "price":"98", "qty":"12"}' -X POST http://localhost:8080/market/bid/add

printf "\n"
curl -H "Content-Type: application/json" -d '{"name":"diner0", "price":"97", "qty":"24"}' -X POST http://localhost:8080/market/bid/add


printf "\n"
curl -H "Content-Type: application/json" -d '{"name":"diner0", "price":"99", "qty":"15"}' -X POST http://localhost:8080/market/offer/add

printf "\n"
curl -H "Content-Type: application/json" -d '{"name":"diner0", "price":"100", "qty":"10"}' -X POST http://localhost:8080/market/offer/add

printf "\n"
curl -H "Content-Type: application/json" -d '{"name":"diner0", "price":"98", "qty":"5"}' -X POST http://localhost:8080/market/offer/add

printf "\n"
curl -H "Content-Type: application/json" -d '{"name":"diner0", "price":"97.5", "qty":"9"}' -X POST http://localhost:8080/market/offer/add

printf "\n"
curl -H "Content-Type: application/json" -d '{"name":"diner0", "price":"97", "qty":"18"}' -X POST http://localhost:8080/market/offer/add

printf "\n"
curl -H "Content-Type: application/json" -d '{"name":"diner0", "price":"110", "qty":"21"}' -X POST http://localhost:8080/market/offer/add


printf "\nBid List\n"
curl -H "Content-Type: application/json" -d '{"name":"diner0"}' -X POST http://localhost:8080/market/bid/get

printf "\nOffer List\n"
curl -H "Content-Type: application/json" -d '{"name":"diner0"}' -X POST http://localhost:8080/market/offer/get


