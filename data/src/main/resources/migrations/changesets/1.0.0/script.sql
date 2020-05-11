CREATE DOMAIN sex AS char(1)
    CHECK (
            VALUE ~ 'M'
            OR VALUE ~ 'F'
        );

CREATE TABLE "patients"
(
    "patient_id"      serial      NOT NULL,
    "last_name"       varchar(50) NOT NULL,
    "first_name"      varchar(50) NOT NULL,
    "second_name"     varchar(50) NULL,
    "sex"             sex         NOT NULL,
    "birthday"        DATE        NOT NULL,
    "policy"          bigint      NOT NULL,
    "serial_passport" int         NOT NULL,
    "number_passport" int         NOT NULL,
    CONSTRAINT "patients_pk" PRIMARY KEY ("patient_id")
);

CREATE TABLE "roles"
(
    "role_id" int         NOT NULL,
    "name"    varchar(50) NOT NULL,
    CONSTRAINT "roles_pk" PRIMARY KEY ("role_id")
);

CREATE TABLE "users"
(
    "user_id"  serial      NOT NULL,
    "username" varchar(50) NOT NULL,
    "password" char(60)    NOT NULL,
    "role"     int         NOT NULL,
    CONSTRAINT "users_pk" PRIMARY KEY ("user_id"),
    CONSTRAINT "users_fk0" FOREIGN KEY ("role") REFERENCES "roles" ("role_id")
);

CREATE TABLE "doctors"
(
    "doctor_id"   serial      NOT NULL,
    "user_id"     int         NOT NULL,
    "last_name"   varchar(50) NOT NULL,
    "first_name"  varchar(50) NOT NULL,
    "second_name" varchar(50) NULL,
    CONSTRAINT "doctors_pk" PRIMARY KEY ("doctor_id"),
    CONSTRAINT "doctors_fk0" FOREIGN KEY ("user_id") REFERENCES "users" ("user_id")
);

CREATE TABLE "drugs"
(
    "drug_id"               serial       NOT NULL,
    "name"                  varchar(50)  NOT NULL,
    "method_of_taking"      varchar(40)  NOT NULL,
    "dosage"                varchar(30)  NOT NULL,
    "description_of_action" varchar(200) NOT NULL,
    "side_effects"          varchar(200) NOT NULL,
    CONSTRAINT "drugs_pk" PRIMARY KEY ("drug_id")
);

CREATE TABLE "diagnoses"
(
    "diagnosis_id"   serial       NOT NULL,
    "diagnosis_name" varchar(100) NOT NULL,
    CONSTRAINT "diagnoses_pk" PRIMARY KEY ("diagnosis_id")
);

CREATE TABLE "appointments"
(
    "appointment_id"   serial       NOT NULL,
    "patient_id"       int          NOT NULL,
    "doctor_id"        int          NOT NULL,
    "place"            varchar(150) NOT NULL,
    "appointment_date" TIMESTAMP    NOT NULL,
    "symptoms"         varchar(200) NOT NULL,
    CONSTRAINT "appointments_pk" PRIMARY KEY ("appointment_id"),
    CONSTRAINT "appointments_fk0" FOREIGN KEY ("patient_id") REFERENCES "patients" ("patient_id"),
    CONSTRAINT "appointments_fk1" FOREIGN KEY ("doctor_id") REFERENCES "doctors" ("doctor_id")
);

CREATE TABLE "patient_diagnoses"
(
    "appointment_id" int NOT NULL,
    "diagnosis_id"   int NOT NULL,
    CONSTRAINT "patient_diagnoses_pk" PRIMARY KEY ("appointment_id", "diagnosis_id"),
    CONSTRAINT "patient_diagnoses_fk0" FOREIGN KEY ("appointment_id") REFERENCES "appointments" ("appointment_id"),
    CONSTRAINT "patient_diagnoses_fk1" FOREIGN KEY ("diagnosis_id") REFERENCES "diagnoses" ("diagnosis_id")
);

CREATE TABLE "prescriptions_of_drugs"
(
    "appointment_id" int NOT NULL,
    "drug_id"        int NOT NULL,
    CONSTRAINT "prescriptions_of_drugs_pk" PRIMARY KEY ("appointment_id", "drug_id"),
    CONSTRAINT "prescriptions_of_drugs_fk0" FOREIGN KEY ("appointment_id") REFERENCES "appointments" ("appointment_id"),
    CONSTRAINT "prescriptions_of_drugs_fk1" FOREIGN KEY ("drug_id") REFERENCES "drugs" ("drug_id")
);
