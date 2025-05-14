CREATE TABLE aluno(
	id_aluno		UUID			PRIMARY KEY,
	nome			VARCHAR(150)	NOT NULL,
	matricula		VARCHAR(50)		NOT NULL,
	cpf				CHAR(11)		NOT NULL
);