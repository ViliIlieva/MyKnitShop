-- https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.data-initialization.using-basic-sql-scripts
-- some test users
INSERT INTO users (username, first_name, last_name, password, email)
VALUES ('petar', 'Petar', 'Petrov', '12345', 'user@user');

INSERT INTO users (username, first_name, last_name, password, email)
VALUES ('ivan', 'Ivan', 'Ivanov', '12345', 'ivan@user');

INSERT INTO users (username, first_name, last_name, password, email)
VALUES ('bogomil', 'Bogomil', 'Bogomolov', '12345', 'bogomil@user');

-- user roles
INSERT INTO users_roles (`user_id`, `role_id`)
VALUES (3, 2);
INSERT INTO users_roles (`user_id`, `role_id`)
VALUES (4, 2);
INSERT INTO users_roles (`user_id`, `role_id`)
VALUES (5, 2);

-- messages
INSERT INTO messages (`description`, author_id)
VALUES ('Много ни хареса шапката Мече, изключително мека и топла прежда', 3);
INSERT INTO messages (`description`, author_id)
VALUES ('Терличките са точния размер, преждата е много мека въпреки вълната в нея', 2);
INSERT INTO messages (`description`, author_id)
VALUES ('Благодарим за страхотното одеало, пасва перфектно на количката', 4);

-- products
INSERT INTO products (name, description, img, price, category_id)
VALUES ('Плетено одеяло',
        'Одеало плетено на ръце от 100% мерино вълна, без химична обработка, антибактериална "дишаща" тъкан, без статично електричествол. Размери: 50 х 50 на цена 65лв. Цената се определя от размера. Ако желаете изработка на одеяло в индивидуален размер и цвят, различен от посочените, не се колебайте да се свържете с мен!',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1677589295/samples/MyKnitShop/product-1_irpbq2.jpg', 56, 3);

INSERT INTO products (name, description, img, price, category_id)
VALUES ('Плетени летни терлички',
        'Изработени от 100% памук, бебешка прежда. Размерите са индивидуални. Посочената цена е за бебешкия размер Ако желаете да бъдат изработени в цветове по ваш избор, не се колебайте да се свържете с мен!',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1677589294/samples/MyKnitShop/product-2_lvfxip.jpg',
        12,
        2);
INSERT INTO products (name, description, img, price, category_id)
VALUES ('Бебешко мерино чувалче за количка',
        'Изработено от 100% мека мерино вълна, ръчно плетиво, антибактериална "дишашта" тъкан, каишки за закопчаване от еко кожа, Размер: 80х40см.',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1677589295/samples/MyKnitShop/product-3_bkt9hc.jpg',
        135,
        3);
INSERT INTO products (name, description, img, price, category_id)
VALUES ('Плетена шапка мече',
        'Изработена от 100% памук, бебешка прежда. Размерите са индивидуални. Ако желаете да бъдат изработени в цветове по ваш избор, не се колебайте да се свържете с мен!',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1677589295/samples/MyKnitShop/product-4_kuskn6.jpg',
        23,
        1);
INSERT INTO products (name, description, img, price, category_id)
VALUES ('Плетена диадема Елза',
        'Изработена от 100% памук, бебешка прежда. Размерите са индивидуални. Ако желаете да бъдат изработени в цветове по ваш избор, не се колебайте да се свържете с мен!',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1677589295/samples/MyKnitShop/product-5_haow2h.jpg',
        30,
        1);
INSERT INTO products (name, description, img, price, category_id)
VALUES ('Плетен зимен комплект "Снежковци"',
        'Изработен от прежда 70% вълна и 30% памук, бебешка прежда, изключително мека. Ако желаете да бъдат изработени в цветове по ваш избор, не се колебайте да се свържете с мен!',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1677589295/samples/MyKnitShop/product-6_k6sh9s.jpg',
        55,
        1);
INSERT INTO products (name, description, img, price, category_id)
VALUES ('Плетени терлици',
        'Изработени от прежда 70% вълна и 30% памук. Размерите са индивидуални. Посочената цена е за бебешкия размер. Ако желаете да бъдат изработени в цветове по ваш избор, не се колебайте да се свържете с мен!',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1677589296/samples/MyKnitShop/product-7_owloum.jpg',
        20,
        2);
INSERT INTO products (name, description, img, price, category_id)
VALUES ('Плетена шапка Мишленце',
        'Изработена от 100% памук, бебешка прежда. Размерите са индивидуални. Ако желаете да бъдат изработени в цветове по ваш избор, не се колебайте да се свържете с мен!',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1677589296/samples/MyKnitShop/product-8_beuv2w.jpg',
        23,
        1);
INSERT INTO products (name, description, img, price, category_id)
VALUES ('Плетени терлички',
        'Изработени от 70% вълна и 30% памук, изключително мека бебешка прежда. Размерите са индивидуални. Посочената цена е за бебешкия размер. Ако желаете да бъдат изработени в цветове по ваш избор, не се колебайте да се свържете с мен!',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1677589296/samples/MyKnitShop/product-9_jqid16.jpg',
        20,
        2);
INSERT INTO products (name, description, img, price, category_id)
VALUES ('Плетена шапка Олаф',
        'Изработена от 100% памук, бебешка прежда. Размерите са индивидуални. Ако желаете да бъдат изработени в цветове по ваш избор, не се колебайте да се свържете с мен!',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1677589296/samples/MyKnitShop/product-10_xamnyh.jpg',
        27,
        1);
INSERT INTO products (name, description, img, price, category_id)
VALUES ('Плетена шапка бухалче',
        'Изработена от 100% памук, бебешка прежда. Размерите са индивидуални. Ако желаете да бъдат изработени в цветове по ваш избор, не се колебайте да се свържете с мен!',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1677589296/samples/MyKnitShop/product-11_aqq6uo.jpg',
        35,
        1);
INSERT INTO products (name, description, img, price, category_id)
VALUES ('Плетена шапка с Пепа',
        'Изработена от 100% памук, бебешка прежда. Размерите са индивидуални. Ако желаете да бъдат изработени в цветове по ваш избор, не се колебайте да се свържете с мен!',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1677589296/samples/MyKnitShop/product-12_ldbzbk.jpg',
        30,
        1);
INSERT INTO products (name, description, img, price, category_id)
VALUES ('Плетена шапка еднорог',
        'Изработена от 100% памук, бебешка прежда. Размерите са индивидуални. Ако желаете да бъдат изработени в цветове по ваш избор, не се колебайте да се свържете с мен!',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1677589296/samples/MyKnitShop/product-13_dthuff.jpg',
        35,
        1);
INSERT INTO products (name, description, img, price, category_id)
VALUES ('Плетено одеяло',
        'Изработена от 100% памук, бебешка прежда. Размерите са индивидуални. Ако желаете да бъдат изработени в цветове по ваш избор, не се колебайте да се свържете с мен!',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1677589294/samples/MyKnitShop/product-16_gjm5nf.jpg',
        35,
        1);

-- users_buy_products
INSERT INTO users_buy_product(user_id, buy_product_id)
VALUES (2,3);
INSERT INTO users_buy_product(user_id, buy_product_id)
VALUES (2,7);
INSERT INTO users_buy_product(user_id, buy_product_id)
VALUES (2,12);
INSERT INTO users_buy_product(user_id, buy_product_id)
VALUES (3,5);
INSERT INTO users_buy_product(user_id, buy_product_id)
VALUES (4,8);
INSERT INTO users_buy_product(user_id, buy_product_id)
VALUES (4,1);
INSERT INTO users_buy_product(user_id, buy_product_id)
VALUES (5,2);

-- orders
INSERT INTO orders (`date_ordered`, `order_number`, `client_id`)
VALUES ('2023-05-01', 1, 2);
INSERT INTO orders (`date_ordered`, `order_number`, `client_id`)
VALUES ('2023-06-01', 2, 3);
INSERT INTO orders (`date_ordered`, `order_number`, `client_id`)
VALUES ('2023-07-01', 3, 4);
INSERT INTO orders (`date_ordered`, `order_number`, `client_id`)
VALUES ('2023-08-01', 4, 5);

