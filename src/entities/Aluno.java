package entities;

import java.util.UUID;

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
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + ", matricula=" + matricula + ", cpf=" + cpf + "]";
	}
}
