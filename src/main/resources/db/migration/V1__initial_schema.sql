CREATE TABLE coffeemachines
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE recipes
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL UNIQUE,
    order_count INT DEFAULT 0
);

CREATE TABLE ingredients
(
    id        SERIAL PRIMARY KEY,
    name      VARCHAR(255) NOT NULL UNIQUE,
    quantity  INT          NOT NULL,
    recipe_id INT REFERENCES recipes (id) ON DELETE CASCADE not null
);

CREATE TABLE orders
(
    id         SERIAL PRIMARY KEY,
    recipe_id  INT REFERENCES recipes (id) ON DELETE CASCADE NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE coffee_machine_recipes
(
    coffee_machine_id INT REFERENCES coffeemachines (id) ON DELETE CASCADE NOT NULL,
    recipe_id         INT REFERENCES recipes (id) ON DELETE CASCADE NOT NULL,
    PRIMARY KEY (coffee_machine_id, recipe_id)
);
