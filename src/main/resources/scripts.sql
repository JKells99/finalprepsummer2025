CREATE TABLE users2 (
            id SERIAL PRIMARY KEY,
            username VARCHAR(50) UNIQUE NOT NULL,
            password TEXT NOT NULL, -- This stores the BCrypt hash
            role VARCHAR(20) NOT NULL CHECK (role IN ('Commander', 'Chief'))
);