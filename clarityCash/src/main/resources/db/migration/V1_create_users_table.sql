-- Habilita extensão pgcrypto (mais moderna que uuid-ossp)
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE users (
                       id UUID PRIMARY KEY,

                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(150) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,

                       role VARCHAR(50) NOT NULL DEFAULT 'USER',

                       enabled BOOLEAN DEFAULT TRUE,

                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP
);

CREATE INDEX idx_users_email ON users(email);
