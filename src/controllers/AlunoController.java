package controllers;

import java.util.Scanner;
import java.util.UUID;

import entities.Aluno;
import exceptions.RepositoryException;
import repositories.AlunoRepository;

public class AlunoController {

	AlunoRepository alunoRepository = new AlunoRepository();
	Scanner scanner = new Scanner(System.in);

	public void exibirOpcoes() {
		var executando = true;

		while (executando) {
			try {
			
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
				
			} catch (RepositoryException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println("Erro inesperado: " + e.getMessage());
			}
		}
	}
 
	private void excluirAluno() {
		System.out.print("Digite o ID do aluno a ser excluído: ");
		var id = scanner.nextLine();
		alunoRepository.excluir(UUID.fromString(id));
		System.out.println("Aluno excluído com sucesso!");
	}

	private void listarAlunos() {
		alunoRepository.consultar();
		System.out.println("Consulta realizada com sucesso!");

	}

	private void alterarAluno() {
		System.out.print("Digite o ID do aluno a ser alterado: ");
		var id = scanner.nextLine();
		var aluno = alunoRepository.consultarPorId(UUID.fromString(id));
		System.out.println("Aluno encontrado:\n " + aluno);

		aluno.setId(UUID.fromString(id));
		System.out.print("Digite o novo nome do aluno: ");
		aluno.setNome(scanner.nextLine());
		System.out.print("Digite a nova matrícula do aluno: ");
		aluno.setMatricula(scanner.nextLine());
		alunoRepository.alterar(aluno);
		System.out.println("\nAluno alterado com sucesso!");
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
