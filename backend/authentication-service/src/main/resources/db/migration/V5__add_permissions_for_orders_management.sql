INSERT INTO "permissions" ("id", "name")
VALUES (6, 'ORDER_UPDATE_ORDER'),
       (7, 'ORDER_CREATE_ORDER'),
       (8, 'ORDER_GET_ALL_ORDERS'),
       (9, 'ORDER_GET_ORDER_DETAILS');


INSERT INTO "roles_permissions" ("permission_id", "role_id")
VALUES (6, 3),
       (6, 4),
       (7, 3),
       (7, 4),
       (8, 3),
       (8, 4),
       (9, 3),
       (9, 4);

