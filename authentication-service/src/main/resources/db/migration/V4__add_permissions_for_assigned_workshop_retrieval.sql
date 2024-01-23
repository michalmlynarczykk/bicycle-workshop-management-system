INSERT INTO "permissions" ("id", "name")
VALUES (5, 'WORKSHOP_GET_ASSIGNED_WORKSHOP');

INSERT INTO "roles_permissions" ("permission_id", "role_id")
VALUES (5, 3),
       (5, 4);

