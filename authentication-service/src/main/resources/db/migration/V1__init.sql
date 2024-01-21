CREATE TABLE permissions
(
    id   INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(80),
    CONSTRAINT pk_permissions PRIMARY KEY (id)
);

ALTER TABLE permissions
    ADD CONSTRAINT uc_permissions_name UNIQUE (name);

CREATE TABLE roles
(
    id   INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(30),
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE roles_permissions
(
    permission_id INTEGER NOT NULL,
    role_id       INTEGER NOT NULL,
    CONSTRAINT pk_roles_permissions PRIMARY KEY (permission_id, role_id)
);

ALTER TABLE roles
    ADD CONSTRAINT uc_roles_name UNIQUE (name);

ALTER TABLE roles_permissions
    ADD CONSTRAINT fk_rolper_on_permission FOREIGN KEY (permission_id) REFERENCES permissions (id);

ALTER TABLE roles_permissions
    ADD CONSTRAINT fk_rolper_on_role FOREIGN KEY (role_id) REFERENCES roles (id);


CREATE TABLE users
(
    id                      UUID         NOT NULL,
    workshop_id             UUID,
    first_name              VARCHAR(50)  NOT NULL,
    last_name               VARCHAR(70)  NOT NULL,
    email                   VARCHAR(255) NOT NULL,
    password                VARCHAR(255) NOT NULL,
    account_non_expired     BOOLEAN,
    account_non_locked      BOOLEAN,
    credentials_non_expired BOOLEAN,
    enabled                 BOOLEAN,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE users_roles
(
    role_id INTEGER NOT NULL,
    user_id UUID    NOT NULL,
    CONSTRAINT pk_users_roles PRIMARY KEY (role_id, user_id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES users (id);