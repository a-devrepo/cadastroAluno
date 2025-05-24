package factories;

public class PostgreConnectionFactory implements ConnectionFactory {

	@Override
	public DataBaseConnection criarDatabaseConnection() {
		return new PostgreConnection();
	}

}
