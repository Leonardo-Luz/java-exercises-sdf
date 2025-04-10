CREATE TABLE pessoa (
	id SERIAL NOT NULL,
	nome VARCHAR(120),
	datanasc DATE,
	CONSTRAINT pessoa_pk PRIMARY KEY (id)
);

--- TEST DATABASE INSERTS
INSERT INTO pessoa (nome, datanasc) VALUES
('Roberto', '1985-03-15'),
('Jose', '1978-11-20'),
('Claudio', '1992-06-01');
