package factories;

import java.util.UUID;

import entities.Aluno;

public class AlunoFactory {

	public Aluno criar(String nome, String cpf, String matricula) {
		return new Aluno(UUID.randomUUID(), nome, matricula, cpf);
	}
}
