package controllers;

import java.util.Scanner;
import java.util.UUID;

import entities.Aluno;
import exceptions.RepositoryException;
import repositories.AlunoRepository;
import services.AlunoService;
import views.ConsoleOutput;

public class AlunoController {

	private final AlunoService alunoService;
	private final ConsoleOutput consoleOutput;
	private final Scanner scanner = new Scanner(System.in);

	public AlunoController(AlunoService alunoService, ConsoleOutput consoleOutput) {
		this.alunoService = alunoService;
		this.consoleOutput = consoleOutput;
	}

	public void exibirOpcoes() {
		var executando = true;

		while (executando) {
			try {

				consoleOutput.exibir("1. INSERIR");
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
			} catch (Exception e) {
				consoleOutput.exibir("Erro inesperado: " + e.getMessage());
			}
		}
	}

	private void excluirAluno() {
		consoleOutput.exibirTextoParaEntrada("Digite o ID do aluno a ser excluído: ");
		var id = scanner.nextLine();
		alunoService.excluir(UUID.fromString(id));
		consoleOutput.exibir("Aluno excluído com sucesso!");
	}

	private void listarAlunos() {
		alunoService.consultar();
		consoleOutput.exibir("Consulta realizada com sucesso!");
	}

	private void alterarAluno() {
		consoleOutput.exibirTextoParaEntrada("Digite o ID do aluno a ser alterado: ");
		var id = scanner.nextLine();
		var aluno = alunoService.buscarPorId(UUID.fromString(id));
		consoleOutput.exibir("Aluno encontrado:\n " + aluno);

		consoleOutput.exibirTextoParaEntrada("Digite o novo nome do aluno: ");
		aluno.setNome(scanner.nextLine());
		consoleOutput.exibirTextoParaEntrada("Digite a nova matrícula do aluno: ");
		aluno.setMatricula(scanner.nextLine());
		alunoService.alterar(aluno);
		consoleOutput.exibir("\nAluno alterado com sucesso!");
	}

	private void cadastrarAluno() {

		var aluno = new Aluno();
		consoleOutput.exibirTextoParaEntrada("Digite o nome do aluno: ");
		aluno.setNome(scanner.nextLine());
		consoleOutput.exibirTextoParaEntrada("Digite a matrícula do aluno: ");
		aluno.setMatricula(scanner.nextLine());
		consoleOutput.exibirTextoParaEntrada("Digite o CPF do aluno: ");
		aluno.setCpf(scanner.nextLine());
		alunoService.inserir(aluno);
		consoleOutput.exibir("\nAluno inserido com sucesso!");
	}
}
