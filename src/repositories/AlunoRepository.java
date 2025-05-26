package repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import entities.Aluno;
import exceptions.RepositoryException;
import factories.DataBaseConnection;

public class AlunoRepository {

	private final DataBaseConnection dataBaseConnection;

	public AlunoRepository(DataBaseConnection dataBaseConnection) {
		this.dataBaseConnection = dataBaseConnection;
	}

	public void inserir(Aluno aluno) {
		try (var connection = dataBaseConnection.obterConexao()) {

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
		try (var connection = dataBaseConnection.obterConexao()) {

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
		try (var connection = dataBaseConnection.obterConexao()) {

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

	public List<Aluno> consultar() {
		try (var connection = dataBaseConnection.obterConexao()) {

			var statement = connection.prepareStatement("select id_aluno, nome, matricula, cpf from aluno");
			var resultSet = statement.executeQuery();

			if (!resultSet.next()) {
				throw new RepositoryException("\nNenhum aluno encontrado.");
			}

			var listaAlunos = new ArrayList<Aluno>();
			do {
				var aluno = new Aluno();
				aluno.setId((UUID) resultSet.getObject("id_aluno"));
				aluno.setNome(resultSet.getString("nome"));
				aluno.setMatricula(resultSet.getString("matricula"));
				aluno.setCpf(resultSet.getString("cpf"));
				listaAlunos.add(aluno);
			} while (resultSet.next());

			return listaAlunos;

		} catch (SQLException e) {
			throw new RepositoryException("\nErro ao consultar alunos: " + e.getMessage());
		}
	}

	public Aluno consultarPorId(UUID id) {

		try (var connection = dataBaseConnection.obterConexao()) {
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
