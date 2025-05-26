package controllers.validators;

import java.util.UUID;

import exceptions.DadosEntradaException;

public class InputValidator {

	public void validarId(String id) {
		if (id == null || id.isBlank()) {
			throw new DadosEntradaException("ID inválido");
		}
		try {
			UUID.fromString(id);
		} catch (IllegalArgumentException e) {
			throw new DadosEntradaException("ID inválido");
		}
	}
	
	public void validarNome(String nome) {
		if (nome == null || nome.isBlank()) {
			throw new DadosEntradaException("O nome não pode estar vazio.");
		}
	}

	public void validarMatricula(String matricula) {
		if (matricula == null || matricula.isBlank()) {
			throw new DadosEntradaException("A matrícula não pode estar vazia.");
		}
		if (matricula.length() < 6 || matricula.length() > 10) {
			throw new DadosEntradaException("A matrícula deve ter entre 6 e 10 caracteres.");
		}
	}

	public void validarCpf(String cpf) {
		if (cpf == null || cpf.isBlank()) {
			throw new DadosEntradaException("O CPF não pode estar vazio.");
		}
		if (!cpf.matches("\\d{11}")) {
			throw new DadosEntradaException("O CPF deve conter exatamente 11 dígitos numéricos.");
		}
	}
}