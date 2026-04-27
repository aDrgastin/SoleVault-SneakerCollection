INSERT INTO brand(name, country, founded, logo_url) VALUES
    ('Nike', 'USA', '1964-01-25', 'https://upload.wikimedia.org/wikipedia/commons/a/a6/Logo_NIKE.svg'),
    ('Adidas', 'Germany', '1930-06-21', 'https://upload.wikimedia.org/wikipedia/commons/2/20/Adidas_Logo.svg'),
    ('Puma', 'Germany', '1955-04-03', 'https://upload.wikimedia.org/wikipedia/commons/a/ae/Puma-logo-%28text%29.svg');

INSERT INTO sneaker(model, brand_id, size, colorway, buy_price, current_value, condition) VALUES
    ('Air Force', 1, 44, 'white/green', 77, 66, 'USED'),
    ('Yeezy', 2, 41, 'black/white', 120, 105, 'USED'),
    ('Air Max', 1, 37, 'black/white', 134, 134, 'NEW');