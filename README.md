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
running this program is best done on linux/wsl since we have scripts prepared for testing
### postgres
- install postgres with the default settings and user "postgres"
- set its ip to localhost:5432
- set up a user called postgres with no password
- go into the postgres directory and locate the "pg_hba.conf" file
- got add trust as the authmethods for all users
- run "sudo createdb strecklista"
- now navigate to the "backend" directory
- type in "sudo ./initdb.sh" this will create a sample database
- now you can run the backend
### backend
for our backend we are using spring boot
- you will need maven
- navigate to the "backend" directory
- run "./mvnw spring-boot:run"
### flutter
for out frontend were using flutter. it is still not fully compiled but runnable
- install flutter
- navigate to the "frontend" directory
- install the flutter debugger extension for vs code
- selecte google chrome as the debugger in the bottom right
- run the debugger
- for the best experiemce; use the mobile view in chrome
- now you can run the frontend

### http requests
there are for dorect access 
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

- /increaseProductAmount
- - example body:
```json
{
    "sessionID": "sessionID",
    "data": {
        "productID": "a product id",
        "amount": "an amount"
    }
}
```
- /getName
- - example body:
```json
{
    "sessionID": "sessionID"
}
```
- /getOrderHistory
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
- - example body:
```json
{
    "auth": "something",
    "data": {
        "userName": "a user name",
        "password": "a user password",
    }
}
```
- /createCommittee
- - example body:
```json
{
    "auth": "something",
    "data": {
        "name": "a committee name",
        "year": "a committee year"
    }
}
```
- /addUserToGroup
- - example body:
```json
{
    "auth": "something",
    "data": {
        "userNick": "a user name(nick)",
        "committeeID": "a committee id"
    }
}
```
- /updateProductAmount
- - example body:
```json
{
    "auth": "something",
    "data": {
        "productID": "a product id",
        "amount": "an amount"
    }
}
```
- /updateUserSaldo
- - example body:
```json
{
    "auth": "something",
    "data": {
        "userNick": "a user name(nick)",
        "committeeID": "a committee id",
        "saldo": "a saldo"
    }
}
```

