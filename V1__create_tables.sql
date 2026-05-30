CREATE TYPE user_role AS ENUM ('ADMIN', 'DRIVER', 'CUSTOMER');

CREATE TYPE ride_status AS ENUM (
    'PENDING',
    'ACCEPTED',
    'IN_PROGRESS',
    'COMPLETED',
    'CANCELLED'
);

CREATE TABLE users
(
    id         UUID PRIMARY KEY      DEFAULT gen_random_uuid(),
    name       VARCHAR(100) NOT NULL,
    email      VARCHAR(150) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    role       user_role    NOT NULL,
    created_at TIMESTAMP    NOT NULL DEFAULT now()
);

CREATE TABLE rides
(
    id          UUID PRIMARY KEY      DEFAULT gen_random_uuid(),
    customer_id UUID         NOT NULL REFERENCES users (id),
    driver_id   UUID REFERENCES users (id),
    origin      VARCHAR(255) NOT NULL,
    destination VARCHAR(255) NOT NULL,
    status      ride_status  NOT NULL DEFAULT 'PENDING',
    version     BIGINT       NOT NULL DEFAULT 0, -- Optimistic Lock (Passo 8)
    created_at  TIMESTAMP    NOT NULL DEFAULT now(),
    updated_at  TIMESTAMP    NOT NULL DEFAULT now()
);

CREATE INDEX idx_rides_customer ON rides (customer_id);
CREATE INDEX idx_rides_driver ON rides (driver_id);
CREATE INDEX idx_rides_status ON rides (status);