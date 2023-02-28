-- https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.data-initialization.using-basic-sql-scripts

-- products
INSERT INTO products ( name, description, img, price, category_id)
VALUES
    ('Плетено одеяло',
     'Одеало плетено на ръце от 100% мерино вълна, без химична обработка, антибактериална "дишаща" тъкан, без статично електричествол. Размери: 50 х 50 на цена 65лв. Цената се определя от размера. Ако желаете изработка на одеяло в индивидуален размер и цвят, различен от посочените, не се колебайте да се свържете с мен!',
     'https://res.cloudinary.com/dmowldibf/image/upload/v1677589295/samples/MyKnitShop/product-1_irpbq2.jpg',
     56,
     3);

INSERT INTO products ( name, description, img, price, category_id)
VALUES
    ('Плетени летни терлички',
     'Изработени от 100% памук, бебешка прежда. Размерите са индивидуални. Посочената цена е за бебешкия размер Ако желаете да бъдат изработени в цветове по ваш избор, не се колебайте да се свържете с мен!',
     'https://res.cloudinary.com/dmowldibf/image/upload/v1677589294/samples/MyKnitShop/product-2_lvfxip.jpg',
     12,
     2);
INSERT INTO products ( name, description, img, price, category_id)
VALUES
    ('Бебешко мерино чувалче за количка',
     'Изработено от 100% мека мерино вълна, ръчно плетиво, антибактериална "дишашта" тъкан, каишки за закопчаване от еко кожа, Размер: 80х40см.',
     'https://res.cloudinary.com/dmowldibf/image/upload/v1677589295/samples/MyKnitShop/product-3_bkt9hc.jpg',
     135,
     3);
INSERT INTO products ( name, description, img, price, category_id)
VALUES
    ('Плетена шапка мече',
     'Изработена от 100% памук, бебешка прежда. Размерите са индивидуални. Ако желаете да бъдат изработени в цветове по ваш избор, не се колебайте да се свържете с мен!',
     'https://res.cloudinary.com/dmowldibf/image/upload/v1677589295/samples/MyKnitShop/product-4_kuskn6.jpg',
     23,
     1);
INSERT INTO products ( name, description, img, price, category_id)
VALUES
    ('Плетена диадема Елза',
     'Изработена от 100% памук, бебешка прежда. Размерите са индивидуални. Ако желаете да бъдат изработени в цветове по ваш избор, не се колебайте да се свържете с мен!',
     'https://res.cloudinary.com/dmowldibf/image/upload/v1677589295/samples/MyKnitShop/product-5_haow2h.jpg',
     30,
     1);
INSERT INTO products ( name, description, img, price, category_id)
VALUES
    ('Плетен зимен комплект "Снежковци"',
     'Изработен от прежда 70% вълна и 30% памук, бебешка прежда, изключително мека. Ако желаете да бъдат изработени в цветове по ваш избор, не се колебайте да се свържете с мен!',
     'https://res.cloudinary.com/dmowldibf/image/upload/v1677589295/samples/MyKnitShop/product-6_k6sh9s.jpg',
     55,
     1);
INSERT INTO products ( name, description, img, price, category_id)
VALUES
    ('Плетени терлици',
     'Изработени от прежда 70% вълна и 30% памук. Размерите са индивидуални. Посочената цена е за бебешкия размер. Ако желаете да бъдат изработени в цветове по ваш избор, не се колебайте да се свържете с мен!',
     'https://res.cloudinary.com/dmowldibf/image/upload/v1677589296/samples/MyKnitShop/product-7_owloum.jpg',
     20,
     2);
INSERT INTO products ( name, description, img, price, category_id)
VALUES
    ('Плетена шапка Мишленце',
     'Изработена от 100% памук, бебешка прежда. Размерите са индивидуални. Ако желаете да бъдат изработени в цветове по ваш избор, не се колебайте да се свържете с мен!',
     'https://res.cloudinary.com/dmowldibf/image/upload/v1677589296/samples/MyKnitShop/product-8_beuv2w.jpg',
     23,
     1);
INSERT INTO products ( name, description, img, price, category_id)
VALUES
    ('Плетени терлички',
     'Изработени от 70% вълна и 30% памук, изключително мека бебешка прежда. Размерите са индивидуални. Посочената цена е за бебешкия размер. Ако желаете да бъдат изработени в цветове по ваш избор, не се колебайте да се свържете с мен!',
     'https://res.cloudinary.com/dmowldibf/image/upload/v1677589296/samples/MyKnitShop/product-9_jqid16.jpg',
     20,
     2);
INSERT INTO products ( name, description, img, price, category_id)
VALUES
    ('Плетена шапка Олаф',
     'Изработена от 100% памук, бебешка прежда. Размерите са индивидуални. Ако желаете да бъдат изработени в цветове по ваш избор, не се колебайте да се свържете с мен!',
     'https://res.cloudinary.com/dmowldibf/image/upload/v1677589296/samples/MyKnitShop/product-10_xamnyh.jpg',
     27,
     1);
INSERT INTO products ( name, description, img, price, category_id)
VALUES
    ('Плетена шапка бухалче',
     'Изработена от 100% памук, бебешка прежда. Размерите са индивидуални. Ако желаете да бъдат изработени в цветове по ваш избор, не се колебайте да се свържете с мен!',
     'https://res.cloudinary.com/dmowldibf/image/upload/v1677589296/samples/MyKnitShop/product-11_aqq6uo.jpg',
     35,
     1);
INSERT INTO products ( name, description, img, price, category_id)
VALUES
    ('Плетена шапка с Пепа',
     'Изработена от 100% памук, бебешка прежда. Размерите са индивидуални. Ако желаете да бъдат изработени в цветове по ваш избор, не се колебайте да се свържете с мен!',
     'https://res.cloudinary.com/dmowldibf/image/upload/v1677589296/samples/MyKnitShop/product-12_ldbzbk.jpg',
     30,
     1);
INSERT INTO products ( name, description, img, price, category_id)
VALUES
    ('Плетена шапка еднорог',
     'Изработена от 100% памук, бебешка прежда. Размерите са индивидуални. Ако желаете да бъдат изработени в цветове по ваш избор, не се колебайте да се свържете с мен!',
     'https://res.cloudinary.com/dmowldibf/image/upload/v1677589296/samples/MyKnitShop/product-13_dthuff.jpg',
     35,
     1);
INSERT INTO products ( name, description, img, price, category_id)
VALUES
    ('Плетено одеяло',
     'Изработена от 100% памук, бебешка прежда. Размерите са индивидуални. Ако желаете да бъдат изработени в цветове по ваш избор, не се колебайте да се свържете с мен!',
     'https://res.cloudinary.com/dmowldibf/image/upload/v1677589294/samples/MyKnitShop/product-16_gjm5nf.jpg',
     35,
     1);

