package principal;

import controllers.AlunoController;
import controllers.validators.AlunoValidator;
import controllers.views.ConsoleOutput;
import factories.PostgreConnectionFactory;
import repositories.AlunoRepository;
import services.AlunoService;

public class Main {

	public static void main(String[] args) {
		var dataBaseConnection = new PostgreConnectionFactory().criarDatabaseConnection();
		var alunoRepository = new AlunoRepository(dataBaseConnection);
		var alunoService = new AlunoService(alunoRepository);
		var consoleOutput = new ConsoleOutput();
		var alunoValidator = new AlunoValidator();
		var alunoController = new AlunoController(alunoService, consoleOutput,alunoValidator);
		alunoController.exibirOpcoes();
	}
}
