--CREATE USER poc_wm IDENTIFIED BY password;
--GRANT CONNECT, RESOURCE, DBA, WM_ADMIN_ROLE TO poc_wm;

CREATE TABLE CODE_TYPE
(
   type_id NUMBER(10)   NOT NULL,
   descr   VARCHAR2(50) NOT NULL,
   CONSTRAINT code_type_pk PRIMARY KEY (type_id)
);

CREATE TABLE CODE
(
   code_id    NUMBER(10)   NOT NULL,
   code_descr VARCHAR2(50) NOT NULL,
   type_id    NUMBER(10),
   CONSTRAINT employees_pk PRIMARY KEY (code_id),
   CONSTRAINT fk_type
   FOREIGN KEY (type_id)
   REFERENCES CODE_TYPE (type_id)
);
