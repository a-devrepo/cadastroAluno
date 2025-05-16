package principal;

import controllers.AlunoController;
import repositories.AlunoRepository;
import services.AlunoService;
import views.ConsoleOutput;

public class Main {

	public static void main(String[] args) {
		var alunoRepository = new AlunoRepository();
		var alunoService = new AlunoService(alunoRepository);
		var consoleOutput = new ConsoleOutput();
		var alunoController = new AlunoController(alunoService, consoleOutput);
		alunoController.exibirOpcoes();
	}

}
