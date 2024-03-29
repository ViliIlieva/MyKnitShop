-- https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.data-initialization.using-basic-sql-scripts
-- messages
INSERT INTO messages (`description`, author_id)
VALUES ('Много ни хареса шапката Мече, изключително мека и топла прежда.', 3);
INSERT INTO messages (`description`, author_id)
VALUES ('Терличките са точния размер, преждата е много мека въпреки вълната в нея.', 2);
INSERT INTO messages (`description`, author_id)
VALUES ('Благодарим за страхотното одеало, пасва перфектно на количката.', 4);

-- users_messages
INSERT INTO users_messages(`user_id`, `messages_id`)
VALUES (3, 1);
INSERT INTO users_messages(`user_id`, `messages_id`)
VALUES (2, 2);
INSERT INTO users_messages(`user_id`, `messages_id`)
VALUES (4, 3);

-- products
INSERT INTO products (name, description, img, price, category_id)
VALUES ('Плетено одеяло в размер по избор',
        'Одеало плетено на ръце от 100% мерино вълна, без химична обработка, антибактериална "дишаща" тъкан, без статично електричествол. Размери: 50 х 50 на цена 65лв. Цената се определя от размера. Ако желаете изработка на одеяло в индивидуален размер и цвят, различен от посочените, не се колебайте да се свържете с мен!',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1677589295/samples/MyKnitShop/product-1_irpbq2.jpg',
        56,
        3);
INSERT INTO products (name, description, img, price, category_id)
VALUES ('Плетени летни терличета',
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
VALUES ('Плетена шапка "Меченце"',
        'Изработена от 100% памук, бебешка прежда. Размерите са индивидуални. Ако желаете да бъдат изработени в цветове по ваш избор, не се колебайте да се свържете с мен!',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1678104408/product-4_bfwfox.jpg',
        23,
        1);
INSERT INTO products (name, description, img, price, category_id)
VALUES ('Плетена диадема "Елза"',
        'Изработена от 100% памук, бебешка прежда. Ако желаете да бъде изработена в цветове по ваш избор, не се колебайте да се свържете с мен!',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1677589295/samples/MyKnitShop/product-5_haow2h.jpg',
        30,
        1);
INSERT INTO products (name, description, img, price, category_id)
VALUES ('Плетен комплект "Снежковци"',
        'Изработен от прежда 70% вълна и 30% памук, бебешка прежда, изключително мека. Ако желаете да бъдат изработени в цветове по ваш избор, не се колебайте да се свържете с мен!',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1677589295/samples/MyKnitShop/product-6_k6sh9s.jpg',
        55,
        1);
INSERT INTO products (name, description, img, price, category_id)
VALUES ('Плетени зимни терлички',
        'Изработени от прежда 70% вълна и 30% памук. Размерите са индивидуални. Посочената цена е за бебешкия размер. Ако желаете да бъдат изработени в цветове по ваш избор, не се колебайте да се свържете с мен!',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1678104407/product-7_bo02ka.jpg',
        20,
        2);
INSERT INTO products (name, description, img, price, category_id)
VALUES ('Плетена шапка "Мишленце"',
        'Изработена от 100% памук, бебешка прежда. Размерите са индивидуални. Ако желаете да бъдат изработени в цветове по ваш избор, не се колебайте да се свържете с мен!',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1677589296/samples/MyKnitShop/product-8_beuv2w.jpg',
        23,
        1);
INSERT INTO products (name, description, img, price, category_id)
VALUES ('Плетени бебешки терлички',
        'Изработени от 70% вълна и 30% памук, изключително мека бебешка прежда. Размерите са индивидуални. Посочената цена е за бебешкия размер. Ако желаете да бъдат изработени в цветове по ваш избор, не се колебайте да се свържете с мен!',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1677589296/samples/MyKnitShop/product-9_jqid16.jpg',
        20,
        2);
INSERT INTO products (name, description, img, price, category_id)
VALUES ('Плетена шапка "Олаф"',
        'Изработена от 100% памук, бебешка прежда. Размерите са индивидуални. Ако желаете да бъдат изработени в цветове по ваш избор, не се колебайте да се свържете с мен!',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1678104146/product-10_j9qcsk.jpg',
        27,
        1);
INSERT INTO products (name, description, img, price, category_id)
VALUES ('Плетена шапка "Бухалче"',
        'Изработена от 100% памук, бебешка прежда. Размерите са индивидуални. Ако желаете да бъдат изработени в цветове по ваш избор, не се колебайте да се свържете с мен!',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1677589296/samples/MyKnitShop/product-11_aqq6uo.jpg',
        35,
        1);
INSERT INTO products (name, description, img, price, category_id)
VALUES ('Плетена шапка с "Пепа"',
        'Изработена от 100% памук, бебешка прежда. Размерите са индивидуални. Ако желаете да бъдат изработени в цветове по ваш избор, не се колебайте да се свържете с мен!',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1678104408/product-12_fw7s27.jpg',
        30,
        1);
INSERT INTO products (name, description, img, price, category_id)
VALUES ('Плетена шапка "Еднорог"',
        'Изработена от 100% памук, бебешка прежда. Размерите са индивидуални. Ако желаете да бъдат изработени в цветове по ваш избор, не се колебайте да се свържете с мен!',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1678104147/product-13_ftsody.jpg',
        35,
        1);
INSERT INTO products (name, description, img, price, category_id)
VALUES ('Плетена диадема за Принцеси',
        'Изработена от 100% памук, бебешка прежда. Ако желаете да бъде изработена в цветове по ваш избор, не се колебайте да се свържете с мен!',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1678104407/product-16_adnvcb.jpg',
        35,
        1);

INSERT INTO purchase_products(name, img, price, quantity, product_sum)
VALUES ('Бебешко мерино чувалче за количка',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1677589295/samples/MyKnitShop/product-3_bkt9hc.jpg',
        135,
        2,
        270);
INSERT INTO purchase_products(name, img, price, quantity, product_sum)
VALUES ('Плетени зимни терлички',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1678104407/product-7_bo02ka.jpg',
        20,
        1,
        20);
INSERT INTO purchase_products(name, img, price, quantity, product_sum)
VALUES ('Плетена шапка с "Пепа"',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1678104408/product-12_fw7s27.jpg',
        30,
        1,
        30);
INSERT INTO purchase_products(name, img, price, quantity, product_sum)
VALUES ('Плетена диадема "Елза"',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1677589295/samples/MyKnitShop/product-5_haow2h.jpg',
        30,
        1,
        30);
INSERT INTO purchase_products(name, img, price, quantity, product_sum)
VALUES ('Плетено одеяло в размер по избор',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1677589295/samples/MyKnitShop/product-1_irpbq2.jpg',
        56,
        3,
        168);
INSERT INTO purchase_products(name, img, price, quantity, product_sum)
VALUES ('Плетена шапка "Мишленце"',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1677589296/samples/MyKnitShop/product-8_beuv2w.jpg',
        23,
        1,
        23);
INSERT INTO purchase_products(name, img, price, quantity, product_sum)
VALUES ('Плетени летни терличета',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1677589294/samples/MyKnitShop/product-2_lvfxip.jpg',
        12,
        1,
        12);

-- users_buy_products
INSERT INTO users_purchase_product(user_id, purchase_product_id)
VALUES (2,1);
INSERT INTO users_purchase_product(user_id, purchase_product_id)
VALUES (2,2);
INSERT INTO users_purchase_product(user_id, purchase_product_id)
VALUES (2,3);
INSERT INTO users_purchase_product(user_id, purchase_product_id)
VALUES (3,4);
INSERT INTO users_purchase_product(user_id, purchase_product_id)
VALUES (4,6);
INSERT INTO users_purchase_product(user_id, purchase_product_id)
VALUES (4,5);
INSERT INTO users_purchase_product(user_id, purchase_product_id)
VALUES (5,7);

-- orders
INSERT INTO orders (`date_ordered`, `client_id`, `order_status`, `order_sum`, `message_id`)
VALUES ('2020-05-01', 2, 'COMPLETED', 320, 2);
INSERT INTO orders (`date_ordered`, `client_id`, `order_status`, `order_sum`, `message_id`)
VALUES ('2020-06-01', 3, 'COMPLETED', 30, 1);
INSERT INTO orders (`date_ordered`, `client_id`, `order_status`, `order_sum`, `message_id`)
VALUES ('2023-07-01', 4, 'COMPLETED', 191, 3);
INSERT INTO orders (`date_ordered`, `client_id`, `order_status`, `order_sum`)
VALUES ('2023-08-01', 5, 'COMPLETED', 12);
INSERT INTO orders (`date_ordered`, `client_id`, `order_status`, `order_sum`)
VALUES ('2023-05-01', 2, 'COMPLETED', 320);
INSERT INTO orders (`date_ordered`, `client_id`, `order_status`, `order_sum`)
VALUES ('2023-05-01', 2, 'OPEN', 320);
INSERT INTO orders (`date_ordered`, `client_id`, `order_status`, `order_sum`)
VALUES ('2023-06-01', 3, 'OPEN', 30);



INSERT INTO products_orders(`order_id`,`purchase_product_id`)
VALUES (1, 1);
INSERT INTO products_orders(`order_id`,`purchase_product_id`)
VALUES (1, 2);
INSERT INTO products_orders(`order_id`,`purchase_product_id`)
VALUES (1, 3);
INSERT INTO products_orders(`order_id`,`purchase_product_id`)
VALUES (2, 4);
INSERT INTO products_orders(`order_id`,`purchase_product_id`)
VALUES (3, 5);
INSERT INTO products_orders(`order_id`,`purchase_product_id`)
VALUES (3, 6);
INSERT INTO products_orders(`order_id`,`purchase_product_id`)
VALUES (4, 7);
INSERT INTO products_orders(`order_id`,`purchase_product_id`)
VALUES (5, 1);
INSERT INTO products_orders(`order_id`,`purchase_product_id`)
VALUES (5, 2);
INSERT INTO products_orders(`order_id`,`purchase_product_id`)
VALUES (5, 3);
INSERT INTO products_orders(`order_id`,`purchase_product_id`)
VALUES (6, 1);
INSERT INTO products_orders(`order_id`,`purchase_product_id`)
VALUES (6, 2);
INSERT INTO products_orders(`order_id`,`purchase_product_id`)
VALUES (6, 3);
INSERT INTO products_orders(`order_id`,`purchase_product_id`)
VALUES (7, 4);


INSERT INTO users_orders(`user_id`,`orders_id`)
VALUES (2, 1);
INSERT INTO users_orders(`user_id`,`orders_id`)
VALUES (3, 2);
INSERT INTO users_orders(`user_id`,`orders_id`)
VALUES (4, 3);
INSERT INTO users_orders(`user_id`,`orders_id`)
VALUES (5, 4);
INSERT INTO users_orders(`user_id`,`orders_id`)
VALUES (2, 5);
INSERT INTO users_orders(`user_id`,`orders_id`)
VALUES (2, 6);
INSERT INTO users_orders(`user_id`,`orders_id`)
VALUES (3, 7);
