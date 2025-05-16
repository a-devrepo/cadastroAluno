package principal;

import controllers.AlunoController;
import controllers.views.ConsoleOutput;
import repositories.AlunoRepository;
import services.AlunoService;

public class Main {

	public static void main(String[] args) {
		var alunoRepository = new AlunoRepository();
		var alunoService = new AlunoService(alunoRepository);
		var consoleOutput = new ConsoleOutput();
		var alunoController = new AlunoController(alunoService, consoleOutput);
		alunoController.exibirOpcoes();
	}

}
