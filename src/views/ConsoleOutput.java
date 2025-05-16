package views;

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
}
