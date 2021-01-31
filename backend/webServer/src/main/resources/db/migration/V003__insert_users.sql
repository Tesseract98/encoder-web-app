
--Guest(login = guest, password = 123456)
--User(login = user, password = user)
--Admin(login = admin, password = password)

insert into users (id, email, login, password, name, surname, patronymic)
values
    (15, 'admin@google.com', 'admin', '$2y$12$fS/mfKBWHXfIwX4waePxyumKKzj7dzhnPV4VEky2uCyQMzwH4pN0S',
    'Вася', 'Василенко', 'Васильевич'),
    (16, 'user@yandex.ru', 'user', '$2y$12$xEmtP1S4m/7KaIw7XxQ61Oh0auA84knYywxwhPpI5vB2X7hjl2Tnu',
    'Марк', 'Балашов', 'Вадимович'),
    (17, 'guest@mail.com', 'guest', '$2y$12$gxIulKlXULoD98tC7PjeTumYqO41LcG7OYkGVVIB0tYMdHT.m6XjS',
    'Anonym', 'Anonymous', null);
