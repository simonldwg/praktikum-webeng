package data.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import configuration.Configuration;

public abstract class SqlDaoBase {
	Connection connection;
	
	public SqlDaoBase()
	{
		try {
			Class.forName(Configuration.getDriver());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection(
					Configuration.getUrl(),
					Configuration.getUsername(),
					Configuration.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	protected Connection getConnection() {
		return connection;
	}
}
