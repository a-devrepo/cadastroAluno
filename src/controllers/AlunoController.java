package controllers;

import java.util.Scanner;
import java.util.UUID;

import entities.Aluno;
import repositories.AlunoRepository;

public class AlunoController {

	AlunoRepository alunoRepository = new AlunoRepository();
	
	public void cadastrarAluno() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("\n****************CADASTRO DE ALUNOS****************");
		
		System.out.print("Nome: ");
		String nome = scanner.nextLine();
		
		System.out.print("Matricula: ");
		String matricula = scanner.nextLine();
		
		System.out.print("CPF: ");
		String cpf = scanner.nextLine();
		
		var aluno = new Aluno(UUID.randomUUID(), nome, matricula, cpf);
		alunoRepository.inserir(aluno);
		
	}
}
