INSERT INTO brand(name, country, founded, logo_url) VALUES
    ('Nike', 'USA', '1964-01-25', 'https://upload.wikimedia.org/wikipedia/commons/a/a6/Logo_NIKE.svg'),
    ('Adidas', 'Germany', '1930-06-21', 'https://upload.wikimedia.org/wikipedia/commons/2/20/Adidas_Logo.svg'),
    ('Puma', 'Germany', '1955-04-03', 'https://upload.wikimedia.org/wikipedia/commons/a/ae/Puma-logo-%28text%29.svg');

INSERT INTO sneaker(model, brand_id, size, colorway, buy_price, current_value, condition) VALUES
    ('Air Force', 1, 44, 'white/green', 77, 66, 'USED'),
    ('Yeezy', 2, 41, 'black/white', 120, 105, 'USED'),
    ('Air Max', 1, 37, 'black/white', 134, 134, 'NEW');

INSERT INTO price_history(sneaker_id, price, recorded_at, source) VALUES
    (1, 250.00, NOW(), 'StockX'),
    (1, 285.00, NOW(), 'GOAT'),
    (3, 35.99, NOW(), 'GOAT');

INSERT INTO want_list_item(model, brand, target_price, notes) VALUES
    ('Yeezy', 'Adidas', 47.77, 'too expensive'),
    ('Air Max', 'Nike', 44.55, '');

INSERT INTO sneaker_image(url, tag, description, sneaker_id) VALUES
    ('https://newcop.com/cdn/shop/files/yeezy-boost-350-V2-mx-dark-sea-salt-1.png?v=1701536747', null, 'Black Yeezys', 2),
    ('https://static.nike.com/a/images/t_web_pdp_936_v2/f_auto/c46298a8-3a0d-4146-8aaa-1c7003ee6489/AIR+MAX+97+NBY.png', null, 'White Air Max 97', 3),
    ('https://i1.t4s.cz/products/921522-035/nike-air-max-97-gs-725602-921522-038-960.webp', null, 'Green/black Air Max 97', 3);

INSERT INTO "user"(username, password, first_name, last_name) VALUES
    ('admin', '$2a$12$xLTAGi1yj9rgtMup2elQ8OZ9sMmpzAsqdeRVXbT6WsGBnVHb9nO7y', 'Admin', ''),
    ('pero', '$2a$12$Mw5hOgqKWsdrovS3A2YQW..1o0wJEpl8tiF0iW8Sl6c1/wsTB5S2e', 'Pero', 'Perić'),
    ('guest', '$2a$12$ifpFo/Tn/sRtd3JyqePKF.SCthMWHOrBGcEj/4/RwxDlelVSCZ/sC', 'Guest', 'Guest');

INSERT INTO authority(name) VALUES ('ROLE_ADMIN'), ('ROLE_USER'), ('ROLE_GUEST');

INSERT INTO user_authority(user_id, authority_id) VALUES (1, 1), (2, 2), (3, 3);
