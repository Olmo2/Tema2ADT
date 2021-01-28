create table departamentos(
    dept_no tinyint(2) PRIMARY KEY NOT NULL,
    dnombre varchar(15),
    loc varchar(15));

create table empleados(
    emp_no smallint(4) PRIMARY KEY NOT NULL,
    apellido varchar(10),
    oficio varchar(10),
    dir smallint(6),
    fecha_alt date,
    salario float(6,2),
    comision float(6,2),
    dept_no tinyint(2),
    FOREIGN KEY (dept_no) REFERENCES departamentos(dept_no));
    