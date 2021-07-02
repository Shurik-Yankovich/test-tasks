CREATE TYPE gender_enum as ENUM ('MALE', 'FEMALE');

CREATE CAST (varchar AS gender_enum) WITH INOUT AS IMPLICIT;

CREATE TABLE IF NOT EXISTS employee (
  employee_id SERIAL NOT NULL,
  first_name CHARACTER VARYING(30) NOT NULL,
  last_name CHARACTER VARYING(50) NOT NULL,
  department_id INT NOT NULL,
  job_title CHARACTER VARYING(100) NOT NULL,
  gender gender_enum NOT NULL,
  date_of_birth DATE NOT NULL,
  PRIMARY KEY (employee_id)
 );