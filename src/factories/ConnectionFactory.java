package factories;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	public Connection obterConexao() {
		var host = "jdbc:postgresql://localhost:5434/cadastro_aluno";
		var user = "postgresuser";
		var password = "postgrespassword";

		try {
			return DriverManager.getConnection(host, user, password);
		} catch (Exception e) {
			System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}
}
