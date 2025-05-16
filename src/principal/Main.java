package principal;

import controllers.AlunoController;
import controllers.views.ConsoleOutput;
import factories.ConnectionFactory;
import repositories.AlunoRepository;
import services.AlunoService;

public class Main {

	public static void main(String[] args) {
		var connectionFactory = new ConnectionFactory();
		var alunoRepository = new AlunoRepository(connectionFactory);
		var alunoService = new AlunoService(alunoRepository);
		var consoleOutput = new ConsoleOutput();
		var alunoController = new AlunoController(alunoService, consoleOutput);
		alunoController.exibirOpcoes();
	}
}
