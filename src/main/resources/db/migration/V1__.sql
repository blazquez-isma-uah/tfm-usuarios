CREATE TABLE instrument
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    instrument_name VARCHAR(255) NOT NULL,
    voice           VARCHAR(255) NOT NULL,
    CONSTRAINT pk_instrument PRIMARY KEY (id)
);

CREATE TABLE `role`
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    name          VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE user
(
    id                  BIGINT AUTO_INCREMENT NOT NULL,
    first_name          VARCHAR(255) NOT NULL,
    last_name           VARCHAR(255) NULL,
    second_last_name    VARCHAR(255) NULL,
    email               VARCHAR(255) NOT NULL,
    password_hash       VARCHAR(255) NULL,
    birth_date          date NULL,
    band_join_date      date NULL,
    system_signup_date  date NULL,
    last_login_date     datetime NULL,
    active              BIT(1)       NOT NULL,
    phone               VARCHAR(255) NULL,
    notes               VARCHAR(255) NULL,
    profile_picture_url VARCHAR(255) NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_instruments
(
    instruments_id BIGINT NOT NULL,
    users_id       BIGINT NOT NULL,
    CONSTRAINT pk_user_instruments PRIMARY KEY (instruments_id, users_id)
);

CREATE TABLE user_roles
(
    user_id  BIGINT NOT NULL,
    roles_id BIGINT NOT NULL,
    CONSTRAINT pk_user_roles PRIMARY KEY (user_id, roles_id)
);

ALTER TABLE instrument
    ADD CONSTRAINT uc_02daaf6f0e93d4aaefde0e689 UNIQUE (instrument_name, voice);

ALTER TABLE `role`
    ADD CONSTRAINT uc_role_name UNIQUE (name);

ALTER TABLE user
    ADD CONSTRAINT uc_user_email UNIQUE (email);

ALTER TABLE user_instruments
    ADD CONSTRAINT fk_useins_on_instrument FOREIGN KEY (instruments_id) REFERENCES instrument (id);

ALTER TABLE user_instruments
    ADD CONSTRAINT fk_useins_on_user FOREIGN KEY (users_id) REFERENCES user (id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (roles_id) REFERENCES `role` (id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES user (id);