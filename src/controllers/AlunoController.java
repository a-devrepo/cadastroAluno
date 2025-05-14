package controllers;

import java.util.Scanner;
import java.util.UUID;

import entities.Aluno;
import repositories.AlunoRepository;

public class AlunoController {

	AlunoRepository alunoRepository = new AlunoRepository();
	Scanner scanner = new Scanner(System.in);

	public void exibirOpcoes() {
		var executando = true;

		while (executando) {

			System.out.println("\n=== MENU PRINCIPAL ===");
			System.out.println("1. INSERIR");
			System.out.println("2. ALTERAR");
			System.out.println("3. LISTAR");
			System.out.println("4. EXCLUIR");
			System.out.println("5. SAIR");
			System.out.print("Escolha uma opção (1-5): ");

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
		}
	}

	private void excluirAluno() {
		System.out.print("Digite o ID do aluno a ser excluído: ");
		var id = scanner.nextLine();
		alunoRepository.excluir(UUID.fromString(id));
	}

	private void listarAlunos() {
		alunoRepository.consultar();

	}

	private void alterarAluno() {
		// TODO Auto-generated method stub

	}

	private void cadastrarAluno() {

		var aluno = new Aluno();
		System.out.print("Digite o nome do aluno: ");
		aluno.setNome(scanner.nextLine());
		System.out.print("Digite a matrícula do aluno: ");
		aluno.setMatricula(scanner.nextLine());
		System.out.print("Digite o CPF do aluno: ");
		aluno.setCpf(scanner.nextLine());
		aluno.setId(UUID.randomUUID());
		alunoRepository.inserir(aluno);
		System.out.println("\nAluno inserido com sucesso!");
	}
}
