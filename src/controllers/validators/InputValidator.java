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
}