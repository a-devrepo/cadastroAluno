package repositories;

import java.sql.SQLException;
import java.util.UUID;
import entities.Aluno;
import exceptions.RepositoryException;
import factories.ConnectionFactory;
import factories.DataBaseConnection;

public class AlunoRepository {

	private final DataBaseConnection connectionFactory;
	
	public AlunoRepository(DataBaseConnection connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	public void inserir(Aluno aluno) {
		try (var connection = connectionFactory.obterConexao()) {

			var statement = connection
					.prepareStatement("insert into aluno (id_aluno, nome, matricula, cpf) values(?,?,?,?)");
			statement.setObject(1, aluno.getId());
			statement.setString(2, aluno.getNome());
			statement.setString(3, aluno.getMatricula());
			statement.setString(4, aluno.getCpf());
			statement.execute();

		} catch (SQLException e) {
			throw new RepositoryException("\nErro ao consultar alunos: " + e.getMessage());
		}
	}

	public void alterar(Aluno aluno) {
		try (var connection = connectionFactory.obterConexao()) {

			var statement = connection.prepareStatement("update aluno set nome=?, matricula=?, cpf=? where id_aluno=?");

			statement.setString(1, aluno.getNome());
			statement.setString(2, aluno.getMatricula());
			statement.setString(3, aluno.getCpf());
			statement.setObject(4, aluno.getId());
			statement.execute();

		} catch (SQLException e) {
			throw new RepositoryException("\nErro ao consultar alunos: " + e.getMessage());
		}
	}

	public void excluir(UUID id) {
		try (var connection = connectionFactory.obterConexao()) {

			var statement = connection.prepareStatement("delete from aluno where id_aluno=?");

			statement.setObject(1, id);
			var alunoExcluido = statement.executeUpdate() > 0;

			if (!alunoExcluido) {
				throw new RepositoryException("\nNenhum aluno encontrado com o ID fornecido.");
			}

		} catch (SQLException e) {
			throw new RepositoryException("\nErro ao consultar alunos: " + e.getMessage());
		}
	}

	public void consultar() {
		try (var connection = connectionFactory.obterConexao()) {

			var statement = connection.prepareStatement("select id_aluno, nome, matricula, cpf from aluno");
			var resultSet = statement.executeQuery();

			if (!resultSet.next()) {
				throw new RepositoryException("\nNenhum aluno encontrado.");
			}

			do {
				System.out.println("\nID.................: " + resultSet.getObject("id_aluno"));
				System.out.println("NOME...............: " + resultSet.getString("nome"));
				System.out.println("MATRICULA..........: " + resultSet.getString("matricula"));
				System.out.println("CPF................: " + resultSet.getString("cpf"));
			} while (resultSet.next());

		} catch (SQLException e) {
			throw new RepositoryException("\nErro ao consultar alunos: " + e.getMessage());
		}
	}

	public Aluno consultarPorId(UUID id) {

		try (var connection = connectionFactory.obterConexao()) {
			var statement = connection
					.prepareStatement("select id_aluno, nome, matricula, cpf from aluno where id_aluno=?");
			statement.setObject(1, id);
			var resultSet = statement.executeQuery();

			if (!resultSet.next()) {
				throw new RepositoryException("Nenhum aluno encontrado com o ID fornecido.");
			}

			var aluno = new Aluno();
			aluno.setId((UUID) resultSet.getObject("id_aluno"));
			aluno.setNome(resultSet.getString("nome"));
			aluno.setMatricula(resultSet.getString("matricula"));
			aluno.setCpf(resultSet.getString("cpf"));
			return aluno;

		} catch (SQLException e) {
			throw new RepositoryException("\nErro ao consultar aluno: " + e.getMessage());
		}
	}
}
