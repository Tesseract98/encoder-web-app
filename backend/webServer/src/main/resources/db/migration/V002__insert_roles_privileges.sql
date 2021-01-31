insert into privilege (id, name)
values
    (1, 'CAN_USE_CRIPTOGRAPHY'),
    (2, 'CAN_USE_STEGANOGRAPHY'),
    (3, 'CAN_USE_CODING'),
    (4, 'CAN_USE_PERSONAL_ACCOUNT');

insert into role (id, name)
values
    (1, 'ROLE_USER'),
    (2, 'ROLE_ADMIN'),
    (3, 'ROLE_GUEST');

insert into roles_privileges (role_fk, privilege_fk)
select 2, id from privilege;

insert into roles_privileges (role_fk, privilege_fk)
values
    (1, 1),
    (1, 2),
    (1, 4),

    (3, 1);
