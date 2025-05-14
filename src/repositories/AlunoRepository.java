package repositories;

import java.util.UUID;

import entities.Aluno;
import factories.ConnectionFactory;

public class AlunoRepository {

	ConnectionFactory connectionFactory = new ConnectionFactory();

	public void inserir(Aluno aluno) {
		try {
			var connection = connectionFactory.obterConexao();
			var statement = connection
					.prepareStatement("insert into aluno (id_aluno, nome, matricula, cpf) values(?,?,?,?)");
			statement.setObject(1, aluno.getId());
			statement.setString(2, aluno.getNome());
			statement.setString(3, aluno.getMatricula());
			statement.setString(4, aluno.getCpf());
			statement.execute();

			connection.close();
		} catch (Exception e) {
			System.out.println("\nErro ao inserir aluno: " + e.getMessage());
		}
	}

	public void alterar(Aluno aluno) {
		try {

			var connection = connectionFactory.obterConexao();
			var statement = connection.prepareStatement("update aluno set nome=?, matricula=?, cpf=? where id_aluno=?");

			statement.setString(1, aluno.getNome());
			statement.setString(2, aluno.getMatricula());
			statement.setString(3, aluno.getCpf());
			statement.setObject(4, aluno.getId());
			statement.execute();

			connection.close();
			System.out.println("\nAluno alterado com sucesso!");
		} catch (Exception e) {
			System.out.println("\nErro ao alterar aluno: " + e.getMessage());
		}
	}

	public void excluir(UUID id) {
		try {
			var connection = connectionFactory.obterConexao();
			var statement = connection.prepareStatement("delete from aluno where id_aluno=?");

			statement.setObject(1, id);
			statement.execute();

			connection.close();
			System.out.println("\nAluno excluído com sucesso!");
		} catch (Exception e) {
			System.out.println("\nErro ao excluir aluno: " + e.getMessage());
		}
	}

	public void consultar() {
		try {
			var connection = connectionFactory.obterConexao();
			var statement = connection.prepareStatement("select id_aluno, nome, matricula, cpf from aluno");
			var resultSet = statement.executeQuery();

			if (resultSet != null) {
				while (resultSet.next()) {
					System.out.println("\nID.............: " + resultSet.getObject("id_aluno"));
					System.out.println("NOME...........: " + resultSet.getString("nome"));
					System.out.println("MATRICULA..........: " + resultSet.getString("matricula"));
					System.out.println("CPF.....: " + resultSet.getString("cpf"));
				}
			}

			connection.close();
			System.out.println("\nConsulta realizada com sucesso!");
		} catch (Exception e) {
			System.out.println("\nErro ao consultar aluno: " + e.getMessage());
		}
	}

	public void consultarPorId(UUID id) {
		try {
			var connection = connectionFactory.obterConexao();
			var statement = connection.prepareStatement("select nome, matricula, cpf from aluno where id_aluno=?");
			statement.setObject(1, id);
			var resultSet = statement.executeQuery();

			if (resultSet != null) {
				while (resultSet.next()) {
					System.out.println("\nID.............: " + resultSet.getObject("id_aluno"));
					System.out.println("NOME...........: " + resultSet.getString("nome"));
					System.out.println("MATRICULA..........: " + resultSet.getString("matricula"));
					System.out.println("CPF.....: " + resultSet.getString("cpf"));
				}
			}

			connection.close();
			System.out.println("\nConsulta realizada com sucesso!");
		} catch (Exception e) {
			System.out.println("\nErro ao consultar aluno: " + e.getMessage());
		}
	}
}
