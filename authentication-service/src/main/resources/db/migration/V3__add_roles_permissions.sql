INSERT INTO "permissions" ("id", "name")
VALUES (1, 'WORKSHOP_CREATE_WORKSHOP'),
       (2, 'WORKSHOP_JOIN_WORKSHOP'),
       (3, 'WORKSHOP_WORKSHOP_JOIN_REQUEST_APPROVE_REJECT'),
       (4, 'WORKSHOP_WORKSHOP_JOIN_REQUESTS_GET_ALL');

INSERT INTO "roles" ("id", "name")
VALUES (1, 'ROLE_OWNER_CANDIDATE'),
       (2, 'ROLE_MECHANIC_CANDIDATE'),
       (3, 'ROLE_OWNER'),
       (4, 'ROLE_MECHANIC');

INSERT INTO "roles_permissions" ("permission_id", "role_id")
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 3);

