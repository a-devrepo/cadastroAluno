package controllers.views;

import java.util.List;

import entities.Aluno;

public class ConsoleOutput {

	public void exibir(String texto) {
		System.out.println(texto);
	}

	public void exibirTextoParaEntrada(String texto) {
		System.out.print(texto);
	}

	public void exibirComQuebraLinha(String texto) {
		System.out.println("\n" + texto);
	}

	public void exibirLista(List<Aluno> listaAlunos) {
		for (Aluno aluno : listaAlunos) {
			exibir(aluno.toString());
		}
	}
}
