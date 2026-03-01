CREATE TABLE categories(
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    name VARCHAR(50) NOT NULL UNIQUE,

    CONSTRAINT fk_category_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE transactions(
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    category_id UUID NOT NULL,
    amount DECIMAL(19, 2) NOT NULL CHECK ( amount > 0 ),
    type VARCHAR(20) NOT NULL CHECK ( type IN ('INCOME', 'EXPENSE')),
    date DATE NOT NULL,
    description VARCHAR(255),
    idempotency_key UUID NOT NULL UNIQUE,

    CONSTRAINT fk_transaction_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_transaction_category FOREIGN KEY (category_id) REFERENCES categories(id)
);