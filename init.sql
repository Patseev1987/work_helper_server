CREATE TABLE IF NOT EXISTS users_table
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    username VARCHAR(255)                            NOT NULL,
    password VARCHAR(255)                            NOT NULL,
    role     VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_users_table PRIMARY KEY (id)
);

ALTER TABLE users_table
    ADD CONSTRAINT uc_users_table_username UNIQUE (username);

CREATE TABLE tools_table
(
    code            VARCHAR(255) NOT NULL,
    name            VARCHAR(255),
    description     VARCHAR(255),
    additional_info VARCHAR(255),
    icon            VARCHAR(255),
    type            VARCHAR(255),
    place_shelf     VARCHAR(255),
    place_column    VARCHAR(255),
    place_row       VARCHAR(255),
    CONSTRAINT pk_tools_table PRIMARY KEY (code)
);

CREATE TABLE workers_table
(
    id         BIGINT NOT NULL,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    patronymic VARCHAR(255),
    type       VARCHAR(255),
    join_date  date,
    department VARCHAR(255),
    login      VARCHAR(255),
    CONSTRAINT pk_workers_table PRIMARY KEY (id)
);

CREATE TABLE storage_records_table
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    tool_code VARCHAR(255),
    worker_id BIGINT,
    amount    INTEGER,
    CONSTRAINT pk_storage_records_table PRIMARY KEY (id)
);

ALTER TABLE storage_records_table
    ADD CONSTRAINT FK_STORAGE_RECORDS_TABLE_ON_TOOL_CODE FOREIGN KEY (tool_code) REFERENCES tools_table (code);

ALTER TABLE storage_records_table
    ADD CONSTRAINT FK_STORAGE_RECORDS_TABLE_ON_WORKER FOREIGN KEY (worker_id) REFERENCES workers_table (id);


CREATE TABLE transactions_table
(
    id               BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    sender_id        BIGINT,
    receiver_id      BIGINT,
    amount           INTEGER,
    tool_code        VARCHAR(255),
    transaction_date date,
    CONSTRAINT pk_transactions_table PRIMARY KEY (id)
);

ALTER TABLE transactions_table
    ADD CONSTRAINT FK_TRANSACTIONS_TABLE_ON_RECEIVER FOREIGN KEY (receiver_id) REFERENCES workers_table (id);

ALTER TABLE transactions_table
    ADD CONSTRAINT FK_TRANSACTIONS_TABLE_ON_SENDER FOREIGN KEY (sender_id) REFERENCES workers_table (id);

ALTER TABLE transactions_table
    ADD CONSTRAINT FK_TRANSACTIONS_TABLE_ON_TOOL_CODE FOREIGN KEY (tool_code) REFERENCES tools_table (code);

