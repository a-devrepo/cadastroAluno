package controllers;

import java.util.UUID;

import controllers.validators.InputValidator;
import controllers.views.ConsoleInput;
import controllers.views.ConsoleOutput;
import entities.Aluno;
import exceptions.DadosEntradaException;
import exceptions.DomainException;
import exceptions.RepositoryException;
import factories.AlunoFactory;
import services.AlunoService;

public class AlunoController {

	private final AlunoService alunoService;
	private final ConsoleOutput consoleOutput;
	private final InputValidator inputValidator;
	private final ConsoleInput consoleInput;
	private final AlunoFactory alunoFactory;

	public AlunoController(
        AlunoService alunoService,
        ConsoleOutput consoleOutput,
        InputValidator inputValidator,
        ConsoleInput consoleInput,
        AlunoFactory alunoFactory
    ) {
        this.alunoService = alunoService;
        this.consoleOutput = consoleOutput;
        this.inputValidator = inputValidator;
        this.consoleInput = consoleInput;
        this.alunoFactory = alunoFactory;
    }

	public void exibirOpcoes() {
        while (true) {
            try {
                var opcao = lerOpcaoMenu();

                switch (opcao) {
                    case "1" -> cadastrarAluno();
                    case "2" -> alterarAluno();
                    case "3" -> listarAlunos();
                    case "4" -> excluirAluno();
                    case "5" -> {
                        consoleOutput.exibir("Saindo do programa...");
                        return;
                    }
                    default -> consoleOutput.exibir("Opção inválida! Tente novamente.");
                }
            } catch (RepositoryException | DadosEntradaException | DomainException e) {
                consoleOutput.exibirComQuebraLinha(e.getMessage());
            }
        }
    }
    
    private String lerOpcaoMenu() {
        consoleOutput.exibirComQuebraLinha("1. INSERIR");
        consoleOutput.exibir("2. ALTERAR");
        consoleOutput.exibir("3. LISTAR");
        consoleOutput.exibir("4. EXCLUIR");
        consoleOutput.exibir("5. SAIR");
        return consoleInput.lerCampo("Escolha uma opção (1-5):");
    }

	private void excluirAluno() {
		var id = consoleInput.lerCampo("Digite o ID do aluno a ser excluído: ");
		inputValidator.validarId(id);
		alunoService.excluir(UUID.fromString(id));
		consoleOutput.exibirComQuebraLinha("Aluno excluído com sucesso!");
	}

	private void listarAlunos() {
		var listaAlunos = alunoService.consultar();
		consoleOutput.exibirLista(listaAlunos);
		consoleOutput.exibirComQuebraLinha("Consulta realizada com sucesso!");
	}

	private void alterarAluno() {
		var id = consoleInput.lerCampo("Digite o ID do aluno a ser alterado: ");
    inputValidator.validarId(id);
    var aluno = alunoService.buscarPorId(UUID.fromString(id));
    consoleOutput.exibir("Aluno encontrado:\n " + aluno);

    var nome = consoleInput.lerCampo("Novo nome (pressione Enter para manter o atual):");
    if (!nome.isBlank()) {
        inputValidator.validarNome(nome);
        aluno.setNome(nome);
    }

    var matricula = consoleInput.lerCampo("Nova matrícula (pressione Enter para manter a atual):");
    if (!matricula.isBlank()) {
        inputValidator.validarMatricula(matricula);
        aluno.setMatricula(matricula);
    }

    alunoService.alterar(aluno);
    consoleOutput.exibirComQuebraLinha("Aluno alterado com sucesso!");
	}

	private void cadastrarAluno() {
		var nome = consoleInput.lerCampo("Digite o nome do aluno: ");
		inputValidator.validarNome(nome);

		var cpf = consoleInput.lerCampo("Digite o CPF do aluno: ");
		inputValidator.validarCpf(cpf);

		var matricula = consoleInput.lerCampo("Digite a matrícula do aluno: ");
		inputValidator.validarMatricula(matricula);

		var aluno = alunoFactory.criar(nome, cpf, matricula);
		alunoService.inserir(aluno);
		consoleOutput.exibirComQuebraLinha("Aluno cadastrado com sucesso!");
	}
}
