package controllers;

import java.util.Scanner;
import java.util.UUID;

import controllers.validators.AlunoValidator;
import controllers.views.ConsoleOutput;
import entities.Aluno;
import exceptions.DadosInvalidosException;
import exceptions.RepositoryException;
import services.AlunoService;

public class AlunoController {

	private final AlunoService alunoService;
	private final ConsoleOutput consoleOutput;
	private final AlunoValidator alunoValidator;
	private final Scanner scanner = new Scanner(System.in);

	public AlunoController(AlunoService alunoService, ConsoleOutput consoleOutput, AlunoValidator alunoValidator) {
		this.alunoService = alunoService;
		this.consoleOutput = consoleOutput;
		this.alunoValidator = alunoValidator;
	}

	public void exibirOpcoes() {
		var executando = true;

		while (executando) {
			try {

				consoleOutput.exibirComQuebraLinha("1. INSERIR");
				consoleOutput.exibir("2. ALTERAR");
				consoleOutput.exibir("3. LISTAR");
				consoleOutput.exibir("4. EXCLUIR");
				consoleOutput.exibir("5. SAIR");
				consoleOutput.exibirTextoParaEntrada("Escolha uma opção (1-5):");

				var opcao = scanner.nextLine();

				switch (opcao) {
				case "1":
					cadastrarAluno();
					break;
				case "2":
					alterarAluno();
					break;
				case "3":
					listarAlunos();
					break;
				case "4":
					excluirAluno();
					break;
				case "5":
					System.out.println("Saindo do programa...");
					executando = false;
					break;
				default:
					System.out.println("Opção inválida! Tente novamente.");
				}

			} catch (RepositoryException e) {
				consoleOutput.exibir(e.getMessage());
			} catch (DadosInvalidosException e) {
				consoleOutput.exibir(e.getMessage());
			}
		}
	}

	private void excluirAluno() {
		consoleOutput.exibirTextoParaEntrada("Digite o ID do aluno a ser excluído: ");
		var id = scanner.nextLine();
		alunoValidator.validarId(id);
		alunoService.excluir(UUID.fromString(id));
		consoleOutput.exibirComQuebraLinha("Aluno excluído com sucesso!");
	}

	private void listarAlunos() {
		alunoService.consultar();
		consoleOutput.exibirComQuebraLinha("Consulta realizada com sucesso!");
	}

	private void alterarAluno() {
		consoleOutput.exibirTextoParaEntrada("Digite o ID do aluno a ser alterado: ");
		var id = scanner.nextLine();
		alunoValidator.validarId(id);
		var aluno = alunoService.buscarPorId(UUID.fromString(id));
		consoleOutput.exibir("Aluno encontrado:\n " + aluno);

		consoleOutput.exibirTextoParaEntrada("Digite o novo nome do aluno: ");
		var nome = scanner.nextLine();
		alunoValidator.validarNome(nome);
		aluno.setNome(nome);

		consoleOutput.exibirTextoParaEntrada("Digite a nova matrícula do aluno: ");
		var matricula = scanner.nextLine();
		alunoValidator.validarMatricula(matricula);
		aluno.setMatricula(matricula);

		alunoService.alterar(aluno);
		consoleOutput.exibirComQuebraLinha("Aluno alterado com sucesso!");
	}

	private void cadastrarAluno() {

		var aluno = new Aluno();
		consoleOutput.exibirTextoParaEntrada("Digite o nome do aluno: ");
		var nome = scanner.nextLine();
		alunoValidator.validarNome(nome);
		aluno.setNome(nome);

		consoleOutput.exibirTextoParaEntrada("Digite a matrícula do aluno: ");
		var matricula = scanner.nextLine();
		alunoValidator.validarMatricula(matricula);
		aluno.setMatricula(matricula);

		consoleOutput.exibirTextoParaEntrada("Digite o CPF do aluno: ");
		var cpf = scanner.nextLine();
		alunoValidator.validarCpf(cpf);
		aluno.setCpf(cpf);

		alunoService.inserir(aluno);
		consoleOutput.exibirComQuebraLinha("Aluno inserido com sucesso!");
	}
}
