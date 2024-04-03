-- noinspection SpellCheckingInspectionForFile

insert into incrementer (id, inc) values
    ('05ea6db2-53d5-4a4d-9d59-77c42c8de5a0', 11);

insert into permission (name) values
    ('VIEW_CERTIFICATES'), ('DOWNLOAD_CERTIFICATE'), ('VIEW_REVOKED'), ('REVOKE_CERTIFICATE');     -- 1, 2, 3, 4

insert into authority (name) values
    ('ROLE_ADMIN'), ('ROLE_SIMPLE_USER');

insert into authorities_permissions (authority_id, permission_id) values
    (1, 1), (1, 2), (1, 3), (1, 4),
    (2, 1), (2, 2), (2, 3);

insert into user_entity (id, deleted, first_name, last_name, last_password_reset_date, password, username, user_role) values
    ('e47ca3f0-4906-495f-b508-4d9af7013575', false, 'John', 'Doe', '2020-06-12 21:58:58.508-07', '$2a$10$b6L2d25q4sjlzczKmvXfTeFktdJg63.oy2CKZImrFcBmUlIK/Vha2', 'admin@gmail.com', 'ADMIN'),
    ('5ef4fa34-da89-4b57-a361-b496f88a6e7e', false, 'Alice', 'Doe', '2020-06-12 21:58:58.508-07', '$2a$10$i3MbCFfhgINyUUbPrecoNeiK/bJfh37JhJIRXUeYYduCp3E4BOhf2', 'user@gmail.com', 'SIMPLE_USER');

--admin@gmail sifra admin123
--user@gmail sifra user123

insert into user_authority (user_id, authority_id) values
    ('e47ca3f0-4906-495f-b508-4d9af7013575', 1),
    ('5ef4fa34-da89-4b57-a361-b496f88a6e7e', 2);

insert into admin (id) values ('e47ca3f0-4906-495f-b508-4d9af7013575');

insert into simple_user (confirmation_time, user_status, id, is_user_confirm_account) values
    ('2020-07-20T06:30:00', 'APPROVED', '5ef4fa34-da89-4b57-a361-b496f88a6e7e', true);