CREATE TABLE "Autor"(
	"cpf" VARCHAR(11) NOT NULL,
	"nome" VARCHAR(120) NOT NULL,
	"dataNascimento" DATE NOT NULL,
	CONSTRAINT autor_pk PRIMARY KEY ("cpf"),
);

CREATE TABLE "Editora"(
	"id" INTEGER,
	"nome" VARCHAR(120),
	"endereco" VARCHAR(255),
	CONSTRAINT editora_pk PRIMARY KEY ("id"),
);

CREATE TABLE "Livro"(
	"id" INTEGER NOT NULL,
	"titulo" VARCHAR(50) NOT NULL,
	"anoLancamento" INTEGER NOT NULL,
	"idEditora" INTEGER,
	CONSTRAINT livro_pk PRIMARY KEY ("id"),
	CONSTRAINT livro_editora_fk FOREIGN KEY ("idEditora") REFERENCES "Editora" ("id")
);

CREATE TABLE "AutorLivro"(
	"cpfAutor" VARCHAR(11) NOT NULL,
	"idLivro" INTEGER NOT NULL,
	CONSTRAINT autor_livro_pk PRIMARY KEY ("cpfAutor", "idLivro"),
	CONSTRAINT autor_fk FOREIGN KEY ("cpfAutor") REFERENCES "Autor" ("cpf"),
	CONSTRAINT livro_fk FOREIGN KEY ("idLivro") REFERENCES "Livro" ("id")
);
