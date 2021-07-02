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

INSERT INTO employee (employee_id, first_name, last_name, department_id, job_title, gender, date_of_birth)
VALUES (DEFAULT, 'John', 'Smith', 1112, 'Senior java developer', 'MALE', '1995-05-25');

INSERT INTO employee (employee_id, first_name, last_name, department_id, job_title, gender, date_of_birth)
VALUES (DEFAULT, 'Joan', 'Smith', 1111, 'Human recruiter', 'MALE', '1997-09-01');

INSERT INTO employee (employee_id, first_name, last_name, department_id, job_title, gender, date_of_birth)
VALUES (DEFAULT, 'Abraham', 'Lincoln', 1115, 'Senior C# developer', 'MALE', '1986-12-31');