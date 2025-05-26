package entities;

import java.util.Objects;
import java.util.UUID;

import exceptions.DadosEntradaException;
import exceptions.DomainException;

public class Aluno {

	private UUID id;
	private String nome;
	private String matricula;
	private String cpf;

	public Aluno() {
	}

	public Aluno(UUID id, String nome, String matricula, String cpf) {
		this.id = id;
		this.nome = nome;
		this.matricula = matricula;
		this.cpf = cpf;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		if(Objects.isNull(id)) {
			throw new DomainException("Id não pode ser nulo");
		}
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome == null || nome.isBlank() || nome.length() < 3) {
			throw new DadosEntradaException("Nome inválido");
		}
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		if (matricula == null || matricula.isBlank()) {
			throw new DadosEntradaException("Matrícula inválida");
		}
		this.matricula = matricula;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		if (cpf == null || cpf.isBlank() || cpf.length() != 11) {
			throw new DadosEntradaException("CPF inválido");
		}
		if (!cpf.matches("\\d+")) {
			throw new DadosEntradaException("CPF deve conter apenas números");
		}
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "\nID.................:" + id + 
				"\nNome...............:" + nome + 
				"\nMatrícula..........:" + matricula+
				"\nCPF................:" + cpf;
	}
}
