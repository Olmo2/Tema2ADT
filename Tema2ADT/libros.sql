


/*Creacion de tablas*/
CREATE TABLE autores  (
  id int(11) PRIMARY KEY NOT NULL,
  nombre varchar(70),
  pais varchar(100) 
) ENGINE=Innodb ;

CREATE TABLE libros (
  id int(11) PRIMARY KEY NOT NULL,
  titulo varchar(50),
  autor_id int(11) ,
  FOREIGN KEY (autor_id) REFERENCES autores(id)
) ENGINE=Innodb ;


/*Inserciones de datos*/

INSERT INTO autores (id, nombre, pais) VALUES
(1, 'J.D. Salinger', 'USA'),
(2, 'F. Scott. Fitzgerald', 'USA'),
(3, 'Jane Austen', 'UK'),
(4, 'Scott Hanselman', 'USA'),
(5, 'Jason N. Gaylord', 'USA'),
(6, 'Pranav Rastogi', 'India'),
(7, 'Todd Miranda', 'USA'),
(8, 'Valle inclán', 'ESP');

INSERT INTO libros (id, titulo,autor_id) VALUES
(1, 'The Catcher in the Rye',1),
(2, 'Nine Stories',2),
(3, 'Franny and Zooey',3),
(4, 'The Great Gatsby',4),
(5, 'Tender id the Night',5),
(6, 'Pride and Prejudice',6),
(7, 'Professional ASP.NET 4.5 in C# and VB',7);

DELIMITER //
CREATE PROCEDURE manolo()
BEGIN
select* from autores where pais = "ESP";
END //
delimiter;