-- ENTITIES

CREATE TABLE User(
    id INT PRIMARY KEY,
    phone_number CHAR(10) NOT NULL,
    user_name TEXT NOT NULL,
    user_nick TEXT NOT NULL
);

CREATE TABLE UserGroup (
    id INT PRIMARY KEY,
    group_name TEXT NOT NULL,
    year CHAR(2),
    UNIQUE(group_name, year)
);

CREATE TABLE Product (
    id INT KEY,
    name TEXT NOT NULL UNIQUE,
    price FLOAT NOT NULL
);


-- RELATIONS

CREATE TABLE Transaction (
    id INT PRIMARY KEY,
    user REFERENCES User(id),
    product REFERENCES Product(id)
    transaction_time TIME NOT NULL,
    transaction_date DATE NOT NULL
);

CREATE TABLE UserInGroup (
    user REFERENCES User(id),
    userGroup REFERENCES UserGroup(id),
    saldo INT NOT NULL,
    PRIMARY KEY(user, userGroup)
);

CREATE TABLE ProductInUserGroup (
    userGroup REFERENCES UserGroup(),
    product REFERENCES Product(id),
    PRIMARY KEY(userGroup, product),
    amount INT
);