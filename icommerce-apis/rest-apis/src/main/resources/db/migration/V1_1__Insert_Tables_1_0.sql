INSERT INTO `users` (`display_name`, `email`, `password`, `address`)
VALUES ('Tester 01', 'tester01@gmail.com', '$2a$10$VsVDYr2TlLR26YHh0uorEO7RSmeGdzMYVrsUCnDR4YBf6jMx73JHq', 'HCM City'),
       ('Tester 02', 'tester02@gmail.com', '$2a$10$7XvZuKEpaqqgkLzcERonm.GXxSkYALShm99MNpvoiB4XnmuMGkDWK', 'HCM City'),
       ('Tester 03', 'tester03@gmail.com', '$2a$10$GhmBQ/LITVOvRFoHqrLzUeHuCFAM19/dqUqFulFRjYRhfvneJ7p2i', 'HCM City'),
       ('Tester 04', 'tester04@gmail.com', '$2a$10$7/.vRYGiYbip3f6swvdosO6pOogtTYQKhMgi9duhmTc/L0fNT3GEe', 'HCM City'),
       ('Tester 05', 'tester05@gmail.com', '$2a$10$gxcpieqE6g99aBHkXJVej.GGM9WWBdZChRd4HtI59edfZ6kbmCXG2', 'HCM City'),
       ('Tester 06', 'tester06@gmail.com', '$2a$10$3XumqbsDJLxpx4EQs0RIW.XCgeTplrRUIaJ0/mjo7aoZH0rR12iMG', 'HCM City');

INSERT INTO `product_category` (`category_code`, `category_english`)
VALUES ('code_01', 'Electronic'),
       ('code_02', 'Computer/Laptop'),
       ('code_03', 'Book');

INSERT INTO `product_brand` (`brand`)
VALUES ('Bifan'),
       ('Samsung'),
       ('Panasonic'),
       ('HP'),
       ('DELL'),
       ('Mac/Macbook'),
       ('William Shakespeare'),
       ('J. K. Rowling'),
       ('Leo Tolstoy');

INSERT INTO `product_colour` (`colour_code`, `colour_english`, `hex`)
VALUES ('colour_01', 'White', '0x111111'),
       ('colour_02', 'Black', '0x000000'),
       ('colour_03', 'Red', '0xFF0000'),
       ('colour_04', 'Blue', '0x0000FF'),
       ('colour_05', 'Green', '0x008000');

INSERT INTO `product` (`sku`, `brand_id`, `category_code`, `colour_code`, `display_name`, `price`)
VALUES ('FAN-MED-WHI-COT-01', 1, 'code_01', 'colour_02', 'Fan 01', 150000),
       ('FAN-MED-WHI-COT-02', 1, 'code_01', 'colour_04', 'Fan 01', 150000),
       ('FAN-MED-WHI-COT-03', 1, 'code_01', 'colour_05', 'Fan 01', 150000),
       ('FAN-MED-WHI-COT-01', 2, 'code_01', 'colour_02', 'Fan 02', 190000),
       ('FAN-MED-WHI-COT-02', 2, 'code_01', 'colour_01', 'Fan 02', 190000),
       ('FAN-MED-WHI-COT-03', 2, 'code_01', 'colour_04', 'Fan 02', 190000),
       ('FAN-MED-WHI-COT-01', 3, 'code_01', 'colour_02', 'Fan 03', 170000),
       ('FAN-MED-WHI-COT-02', 3, 'code_01', 'colour_02', 'Fan 03', 170000),
       ('FAN-MED-WHI-COT-03', 3, 'code_01', 'colour_01', 'Fan 03', 170000),
       ('LAP-MED-WHI-COT-01', 4, 'code_02', 'colour_02', 'HP 01', 17000000),
       ('LAP-MED-WHI-COT-02', 4, 'code_02', 'colour_02', 'HP 02', 27000000),
       ('LAP-MED-WHI-COT-03', 4, 'code_02', 'colour_01', 'HP 03', 30000000),
       ('LAP-MED-WHI-COT-01', 5, 'code_02', 'colour_02', 'Lap 01', 17000000),
       ('LAP-MED-WHI-COT-02', 5, 'code_02', 'colour_05', 'Lap 02', 25000000),
       ('LAP-MED-WHI-COT-03', 5, 'code_02', 'colour_03', 'Lap 03', 29000000),
       ('LAP-MED-WHI-COT-01', 6, 'code_02', 'colour_03', 'Mac 01', 40000000),
       ('LAP-MED-WHI-COT-02', 6, 'code_02', 'colour_04', 'Mac 02', 41000000),
       ('LAP-MED-WHI-COT-03', 6, 'code_02', 'colour_05', 'Mac 03', 43000000);


