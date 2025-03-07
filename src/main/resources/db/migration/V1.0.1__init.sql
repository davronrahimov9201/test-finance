CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE "public"."users" (
    "id" uuid NOT NULL DEFAULT uuid_generate_v4(),
    "username" varchar(64) NOT NULL,
    "first_name" varchar(32),
    "last_name" varchar(32),
    "balance" decimal(10,2) NOT NULL DEFAULT 0,
    "created_date" timestamp NOT NULL DEFAULT now(),
    "modified_date" timestamp NOT NULL DEFAULT now(),
    "deleted_date" timestamp,
    PRIMARY KEY ("id"),
    CONSTRAINT "users_balance_check1" CHECK (balance >= 0)
);

CREATE UNIQUE INDEX "users_username_uk1" ON "public"."users" (
    "username"
) WHERE deleted_date IS NULL;

COMMENT ON COLUMN "public"."users"."id" IS 'Уникальный ID';

COMMENT ON COLUMN "public"."users"."username" IS 'Имя пользователя';

COMMENT ON COLUMN "public"."users"."first_name" IS 'Имя';

COMMENT ON COLUMN "public"."users"."last_name" IS 'Фамилия';

COMMENT ON COLUMN "public"."users"."balance" IS 'Баланс';

COMMENT ON COLUMN "public"."users"."created_date" IS 'Дата создания';

COMMENT ON COLUMN "public"."users"."modified_date" IS 'Дата изменения';

COMMENT ON COLUMN "public"."users"."deleted_date" IS 'Дата удаления';

COMMENT ON TABLE "public"."users" IS 'Пользователь';

DROP TYPE IF EXISTS "public"."transaction_type";
CREATE TYPE "public"."transaction_type" AS ENUM (
    'INCOME',
    'OUTCOME'
);

DROP TYPE IF EXISTS "public"."transaction_status";
CREATE TYPE "public"."transaction_status" AS ENUM (
    'PENDING',
    'SUCCESS',
    'FAILED'
);

CREATE TABLE "public"."transactions" (
    "id" uuid NOT NULL DEFAULT uuid_generate_v4(),
    "user_id" uuid NOT NULL,
    "amount" decimal(10,2) NOT NULL,
    "transaction_type" transaction_type NOT NULL,
    "status" transaction_status NOT NULL,
    "created_date" timestamp NOT NULL,
    "modified_date" timestamp NOT NULL,
    "deleted_date" timestamp,
    PRIMARY KEY ("id"),
    CONSTRAINT "transactions_user_id_fk1" FOREIGN KEY ("user_id") REFERENCES "public"."users"  ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT "transactions_amount_check1" CHECK (amount >= 0)
);

COMMENT ON COLUMN "public"."transactions"."id" IS 'Уникальный ID';

COMMENT ON COLUMN "public"."transactions"."user_id" IS 'ID пользователя';

COMMENT ON COLUMN "public"."transactions"."amount" IS 'Сумма (в копейках)';

COMMENT ON COLUMN "public"."transactions"."transaction_type" IS 'Тип транзакции';

COMMENT ON COLUMN "public"."transactions"."status" IS 'Статус';

COMMENT ON COLUMN "public"."transactions"."created_date" IS 'Дата создания';

COMMENT ON COLUMN "public"."transactions"."modified_date" IS 'Дата изменения';

COMMENT ON COLUMN "public"."transactions"."deleted_date" IS 'Дата удаления';

COMMENT ON TABLE "public"."transactions" IS 'Транзакция';


INSERT INTO "public"."users" ("id", "username", "first_name", "last_name", "balance", "created_date", "modified_date", "deleted_date")
VALUES
    ('d1cb3901-2589-4a4d-b07c-5ab5c972dc3c', 'johndoe', 'John', 'Doe', 210.90, now(), now(), NULL),
    ('35314458-07b1-4848-bedd-87f83d52beb7', 'alan1', 'Alan', 'Holland', 0.0, now(), now(), NULL),
    ('7d0ec1d0-b834-4d44-83a7-c92de7edd7ed', 'nick', 'Nick', null, 3100.0, now(), now(), NULL);


INSERT INTO "public"."transactions"
("id", "user_id", "amount", "transaction_type", "status", "created_date", "modified_date", "deleted_date")
VALUES
    (uuid_generate_v4(), 'd1cb3901-2589-4a4d-b07c-5ab5c972dc3c', 300.14, 'INCOME', 'SUCCESS', now(), now(), NULL),
    (uuid_generate_v4(), 'd1cb3901-2589-4a4d-b07c-5ab5c972dc3c', 45.96, 'INCOME', 'PENDING', now(), now(), NULL),
    (uuid_generate_v4(), 'd1cb3901-2589-4a4d-b07c-5ab5c972dc3c', 89.24, 'OUTCOME', 'SUCCESS', now(), now(), NULL),
    (uuid_generate_v4(), '35314458-07b1-4848-bedd-87f83d52beb7', 132.00, 'INCOME', 'SUCCESS', now(), now(), NULL),
    (uuid_generate_v4(), '35314458-07b1-4848-bedd-87f83d52beb7', 2540.73, 'INCOME', 'FAILED', now(), now(), NULL),
    (uuid_generate_v4(), '35314458-07b1-4848-bedd-87f83d52beb7', 132.00, 'OUTCOME', 'SUCCESS', now(), now(), NULL),
    (uuid_generate_v4(), '7d0ec1d0-b834-4d44-83a7-c92de7edd7ed', 3100.00, 'INCOME', 'SUCCESS', now(), now(), NULL);
