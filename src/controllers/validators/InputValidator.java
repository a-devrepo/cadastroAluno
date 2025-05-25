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
		if (nome == null || nome.isBlank() || nome.length() < 3) {
			throw new DadosEntradaException("Nome inválido");
		}
	}

	public void validarMatricula(String matricula) {
		if (matricula == null || matricula.isBlank()) {
			throw new DadosEntradaException("Matrícula inválida");
		}
	}

	public void validarCpf(String cpf) {
		if (cpf == null || cpf.isBlank() || cpf.length() != 11) {
			throw new DadosEntradaException("CPF inválido");
		}
		if (!cpf.matches("\\d+")) {
			throw new DadosEntradaException("CPF deve conter apenas números");
		}
	}
}