CREATE TABLE produto(
	id SERIAL PRIMARY KEY,
	descricao	TEXT not null,
	categoria VARCHAR(200) not null, 
	preco NUMERIC(10,2) not null CHECK (preco>0),
	quantidade INTEGER not null,
 	status BOOLEAN DEFAULT TRUE
);

INSERT INTO produto (descricao, categoria, quantidade)
VALUES ('Produto sem preço', 'PERIFERICOS', 10);

INSERT INTO produto (descricao, categoria, preco, quantidade)
VALUES ('Produto de graça', 'PERIFERICOS', 0, 5);

INSERT INTO produto (descricao, categoria, preco, quantidade)
VALUES ('Teclado mecânico', 'PERIFERICOS', 259.90, 15);

SELECT * FROM produto;