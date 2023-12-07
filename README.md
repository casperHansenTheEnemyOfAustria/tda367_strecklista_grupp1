# tda367_strecklista_grupp1
This project is an attempt at rewriting the currently existing inventory management system used by committées at the IT-program.
Planned improvements are:
- User system
- Usergroups
- More intuitive UI/UX
- Better tracking of inventory
- TBD


## Group members:
- Simon Westlin Green
- Casper Hansen
- Albin Skeppstedt
- Hanna Forsling
- Samael Pooyan

## Install and run instructions for feedback group:
running this program is best done on linux since we have scripts prepared for testing
### postgres
- install postgres with the default settings and user "postgres"
- set up a user called postgres with no password
- go into the postgres directory and locate the "pg_hba.conf" file
- got add trust as the authmethods for all users
- run "sudo createdb strecklista"
- now navigate to the "backend" directory
- type in "sudo ./initdb.sh" this will create a sample database
### backend
- navigate to the "backend" directory
- run "./mvnw spring-boot:run"
### http requests
- install the postman extension for vs code or use the postman app
- now you can send http requests to the backend
- the requests are sent to "http://localhost:8080"
- the requests are sent to the following endpoints (all being post requests for security reasons):

#### user
- /login
- - example body:
```json
{
    "userName": "admin",
    "password": "admin"
}
```
- - this returns a sessionID which you will use for following requests

- /getProducts
- - example body:
```json
{
    "sessionID": "sessionID"
}
```
- /getProduct
- - example body:
```json
{
    "sessionID": "sessionID",
    "data": {
        "id": "a product id"
    }
}
```
- /getCart
- - example body:
```json
{
    "sessionID": "sessionID"
}
```
- /addToCart
- - example body:
```json
{
    "sessionID": "sessionID",
    "data": {
        "productID": "a product id"
    }
}
```
- /removeFromCart
- - example body:
```json
{
    "sessionID": "sessionID",
    "data": {
        "productID": "a product id"
    }
}
```
- /getSaldo
- - example body:
```json
{
    "sessionID": "sessionID"
}
```
- /completePurchase
- - example body:
```json
{
    "sessionID": "sessionID"
}
```
- /logout
- - example body:
```json
{
    "sessionID": "sessionID"
}
```

#### admin
- /createProduct
- - example body:
```json
{
    "auth": "something",
    "data": {
        "productName": "a product name",
        "price": "a product price",
        "committeeID": "a committee id",
        "amount": "a product amount"

    }
}
```
- /createUser
- /createCommittee
- /addUserToCommittee



 if you run into any postgres problems please contact one of the group members
