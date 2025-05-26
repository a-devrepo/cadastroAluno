package services;

import java.util.List;
import java.util.UUID;

import entities.Aluno;
import repositories.AlunoRepository;

public class AlunoService {

	private final AlunoRepository alunoRepository;

	public AlunoService(AlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}

	public void inserir(Aluno aluno) {
		alunoRepository.inserir(aluno);
	}

	public void alterar(Aluno aluno) {
		alunoRepository.alterar(aluno);
	}

	public void excluir(UUID id) {
		alunoRepository.excluir(id);
	}

	public Aluno buscarPorId(UUID id) {
		return alunoRepository.consultarPorId(id);
	}

	public List<Aluno> consultar() {
		return alunoRepository.consultar();
	}
}
