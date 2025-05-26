package principal;

import java.util.Scanner;

import controllers.AlunoController;
import controllers.validators.InputValidator;
import controllers.views.ConsoleInput;
import controllers.views.ConsoleOutput;
import factories.AlunoFactory;
import factories.PostgreConnectionFactory;
import repositories.AlunoRepository;
import services.AlunoService;

public class Main {

	public static void main(String[] args) {
		var scanner = new Scanner(System.in);
		var dataBaseConnection = new PostgreConnectionFactory().criarDatabaseConnection();
		var alunoRepository = new AlunoRepository(dataBaseConnection);
		var alunoService = new AlunoService(alunoRepository);
		var consoleOutput = new ConsoleOutput();
		var consoleInput = new ConsoleInput(scanner, consoleOutput);
		var alunoValidator = new InputValidator();
		var alunoFactory = new AlunoFactory();
		var alunoController = new AlunoController(scanner,alunoService, consoleOutput,alunoValidator, consoleInput, alunoFactory);
		alunoController.exibirOpcoes();
	}
}
