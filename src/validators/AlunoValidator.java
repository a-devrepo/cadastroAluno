package validators;

import java.util.UUID;

import exceptions.DadosInvalidosException;

public class AlunoValidator {

	public static void validarId(String id) {
		if (id == null || id.isBlank()) {
			throw new DadosInvalidosException("ID inválido");
		}
		try {
			UUID.fromString(id);
		} catch (IllegalArgumentException e) {
			throw new DadosInvalidosException("ID inválido");
		}
	}

	public static void validarNome(String nome) {
		if (nome == null || nome.isBlank() || nome.length() < 3) {
			throw new DadosInvalidosException("Nome inválido");
		}
	}

	public static void validarMatricula(String matricula) {
		if (matricula == null || matricula.isBlank()) {
			throw new DadosInvalidosException("Matrícula inválida");
		}
	}

	public static void validarCpf(String cpf) {
		if (cpf == null || cpf.isBlank() || cpf.length() != 11) {
			throw new DadosInvalidosException("CPF inválido");
		}
		if (!cpf.matches("\\d+")) {
			throw new DadosInvalidosException("CPF deve conter apenas números");
		}	 
	}
}