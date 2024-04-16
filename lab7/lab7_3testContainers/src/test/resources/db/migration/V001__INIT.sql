CREATE TABLE books (
    id                  SERIAL          NOT NULL PRIMARY KEY,
    title               VARCHAR(255)    NOT NULL,
    author              VARCHAR(255)    NOT NULL,
    year                Int             NOT NULL
);