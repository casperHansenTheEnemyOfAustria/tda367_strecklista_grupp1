-- ENTITIES

CREATE TABLE Person (
    id TEXT PRIMARY KEY,
    phone_number CHAR(10) NOT NULL,
    person_name TEXT NOT NULL,
    person_nick TEXT NOT NULL
);

CREATE TABLE Committee (
    id TEXT PRIMARY KEY,
    group_name TEXT NOT NULL,
    year CHAR(2),
    UNIQUE(group_name, year)
);

CREATE TABLE Product (
    id TEXT PRIMARY KEY,
    name TEXT NOT NULL UNIQUE,
    price FLOAT NOT NULL,
    amount INT
);


-- RELATIONS

CREATE TABLE Transaction (
    id TEXT PRIMARY KEY,
    person_id TEXT REFERENCES Person(id) NOT NULL,
    product_id TEXT REFERENCES Product(id) NOT NULL,
    transaction_time TIME NOT NULL,
    transaction_date DATE NOT NULL
);

CREATE TABLE PersonInCommittee (
    person_id TEXT REFERENCES Person(id) NOT NULL,
    committee_id TEXT REFERENCES Committee(id) NOT NULL,
    saldo INT NOT NULL,
    PRIMARY KEY(person_id, committee_id)
);

CREATE TABLE ProductInCommittee (
    committee_id TEXT REFERENCES Committee(id) NOT NULL,
    product_id TEXT REFERENCES Product(id) NOT NULL,
    PRIMARY KEY(committee_id, product_id)
    
);
