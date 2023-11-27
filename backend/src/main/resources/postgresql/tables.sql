-- ENTITIES

CREATE TABLE Users (
    id TEXT PRIMARY KEY,
    phone_number CHAR(10) NOT NULL,
    user_name TEXT NOT NULL,
    user_nick TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL
);


CREATE TABLE Committees (
    id TEXT PRIMARY KEY,
    group_name TEXT NOT NULL,
    year CHAR(2),
    UNIQUE(group_name, year)
);

CREATE TABLE Products (
    id TEXT PRIMARY KEY,
    name TEXT NOT NULL UNIQUE,
    price FLOAT NOT NULL,
    amount INT
);


-- RELATIONS

CREATE TABLE Transaction (
    id TEXT PRIMARY KEY,
    user_id TEXT REFERENCES Users(id) NOT NULL,
    product_id TEXT REFERENCES Product(id) NOT NULL,
    transaction_time TIME NOT NULL,
    transaction_date DATE NOT NULL
);

CREATE TABLE UserInCommittee (
    user_id TEXT REFERENCES User(id) NOT NULL,
    committee_id TEXT REFERENCES Committee(id) NOT NULL,
    saldo INT NOT NULL,
    PRIMARY KEY(user_id, committee_id)
);

CREATE TABLE ProductInCommittee (
    committee_id TEXT REFERENCES Committee(id) NOT NULL,
    product_id TEXT REFERENCES Product(id) NOT NULL,
    PRIMARY KEY(committee_id, product_id)
    
);
