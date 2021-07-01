# Microservices - Operation Change

## Presentation
Gerer les operations de change. Une opération de change se caractérise par {"id transaction": 12345, "devise source": "EUR", "devise destination": "USD",
"montant": 1000, "date": "2021-05-25", "taux": 1.22} signifiant qu'en date du 25 mai 2021, une opération de change, identifiée par le numéro 12345, de 1000 euros
en dollard US a été réalisée au taux EUR/USD = 1.22.

## Diagramme de classe

![Screenshot](DiagrammeDeClassOperationChange.png)

# Installation
## Set up the Spring Boot Application
```bash
./mvnw package && java -jar target/operationchange
```
## Set up Docker
```bash
docker build -t springio/operationchange .
docker run -p 8080:8080 -t springio/operationchange
```
## Methods

| Methods   | Urls                                                      | Actions                                                    |
| :--------:|:----------------------------------------------------------| :----------------------------------------------------------|
| POST      | /operation-change                                         | create new Operation Change                                |
| GET       | /operation-change                                         | retrieve all Operation Change                              |
| GET       | /operation-change/id/{id_transaction}                     | retrieve Operation Change by {id_transaction}              |
| GET       | /operation-change/montant/{montant}                       | retrieve Operation Change by {montant}                     |
| GET       | /operation-change/date/{date}                             | retrieve Operation Change by {date}                        |
| GET       | /operation-change/taux/{taux}                             | retrieve Operation Change by {taux}                        |
| GET       | /operation-change/source/{source}/dest/{dest}             | retrieve Operation Change by {source} and {dest}           |
| GET       | /operation-change/source/{source}/dest/{dest}/date/{date} | retrieve Operation Change by {source} and {dest} and {date}|


# Utilisation
## POST
### create new Operation Change
```bash
curl -X POST -H "Content-type: application/json" -d "{\"source\" : \"EUR\", \"dest\" : \"USD\", \"montant\" : 500, \"date\": \"2021-06-23\"}" "http://localhost:8080/operation-change"
```
{"source":"EUR","dest":"USD","montant":500,"taux":1.19,"date":"2021-06-23"}
### create new Operation Change without rate
```bash
curl -X POST -H "Content-type: application/json" -d "{\"source\" : \"EUR\", \"dest\" : \"USD\", \"montant\" : 500, \"taux\" : 1.19, \"date\": \"2021-06-23\"}" "http://localhost:8080/operation-change"
```
{"source":"EUR","dest":"USD","montant":500,"taux":1.19,"date":"2021-06-23"}
## GET
### retrieve all Operation Change
```bash
curl -X GET "http://localhost:8080/operation-change"
```
[{"source":"EUR","dest":"USD","montant":1000,"taux":1.19,"date":"2021-06-21"},{"source":"EUR","dest":"USD","montant":800,"taux":1.19,"date":"2021-06-21"},{"source":"EUR","dest":"USD","montant":1000,"taux":1.19,"date":"2021-06-24"}, ...]
### retrieve Operation Change by {id_transaction}
```bash
curl -X GET "http://localhost:8080/operation-change/id/1234"
```
{"source":"EUR","dest":"USD","montant":1000,"taux":1.19,"date":"2021-06-21"}
### retrieve Operation Change by {montant}
```bash
curl -X GET "http://localhost:8080/operation-change/montant/1000"
```
[{"source":"EUR","dest":"USD","montant":1000,"taux":1.19,"date":"2021-06-21"},{"source":"EUR","dest":"USD","montant":1000,"taux":1.19,"date":"2021-06-24"},{"source":"USD","dest":"GBP","montant":1000,"taux":0.72,"date":"2021-06-25"},{"source":"EUR","dest":"JPY","montant":1000,"taux":132.08,"date":"2021-06-22"}]
### retrieve Operation Change by {date}
```bash
curl -X GET "http://localhost:8080/operation-change/date/2021-06-21"
```
[{"source":"EUR","dest":"USD","montant":1000,"taux":1.19,"date":"2021-06-21"},{"source":"EUR","dest":"USD","montant":800,"taux":1.19,"date":"2021-06-21"},{"source":"EUR","dest":"JPY","montant":800,"taux":131.41,"date":"2021-06-21"}]
### retrieve Operation Change by {taux}
```bash
curl -X GET "http://localhost:8080/operation-change/taux/132.30"
```
[{"source":"EUR","dest":"JPY","montant":200,"taux":132.30,"date":"2021-06-23"},{"source":"EUR","dest":"JPY","montant":500,"taux":132.30,"date":"2021-05-23"},{"source":"EUR","dest":"JPY","montant":150,"taux":132.30,"date":"2021-05-23"}]
### retrieve Operation Change by {source} and {dest}
```bash
curl -X GET "http://localhost:8080/operation-change/source/EUR/dest/JPY"
```
[{"source":"EUR","dest":"JPY","montant":200,"taux":132.30,"date":"2021-06-23"},{"source":"EUR","dest":"JPY","montant":500,"taux":132.30,"date":"2021-05-23"},{"source":"EUR","dest":"JPY","montant":150,"taux":132.30,"date":"2021-05-23"},{"source":"EUR","dest":"JPY","montant":800,"taux":131.41,"date":"2021-06-21"},{"source":"EUR","dest":"JPY","montant":1000,"taux":132.08,"date":"2021-06-22"}]
### retrieve Operation Change by {source} and {dest} and {date}
```bash
curl -X GET "http://localhost:8080/operation-change/source/EUR/dest/USD/date/2021-06-23"
```
[{"source":"EUR","dest":"USD","montant":500,"taux":1.19,"date":"2021-06-23"},{"source":"EUR","dest":"USD","montant":500,"taux":1.19,"date":"2021-06-23"},{"source":"EUR","dest":"USD","montant":500,"taux":1.19,"date":"2021-06-23"},{"source":"EUR","dest":"USD","montant":500,"taux":1.19,"date":"2021-06-23"}]