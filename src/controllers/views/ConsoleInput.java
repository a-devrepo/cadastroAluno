package controllers.views;

import java.util.Scanner;
import java.util.UUID;

import controllers.validators.InputValidator;
import entities.Aluno;
import exceptions.DadosEntradaException;

public class ConsoleInput {

	private final ConsoleOutput consoleOutput;
	private final Scanner scanner;

	 public ConsoleInput(Scanner scanner, ConsoleOutput consoleOutput) {
		this.scanner = scanner;
		this.consoleOutput = consoleOutput;
	}

	public String lerCampo(String mensagem) {
		try {
			consoleOutput.exibirTextoParaEntrada(mensagem);
			return scanner.nextLine();
		} catch (Exception e) {
			throw new DadosEntradaException("Erro ao ler o campo: " + e.getMessage());
		}
	}
}
