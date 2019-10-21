package mulicupteas.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;

public class JdbcConnection {

	// static global DataSource object.
	private static DataSource dataSource;

	public static void main(String[] args) {
		String query = "SELECT COUNT(*) FROM employee";

		//Using try-with-resources for auto closing connection, pstmt, and rs.
		try (Connection connection = getConnection();
		     PreparedStatement pstmt = connection.prepareStatement(query);
		     ResultSet rs = pstmt.executeQuery();
		) {
			if (rs.next()) {
				System.out.println("Total employees are " + rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//Call the get connection method.
	static Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}

	//Get the DataSource. If not available create the new one
	//It is not threadsafe. I didn't wanted to complicate things.
	private static DataSource getDataSource() {
		if (null == dataSource) {
			System.out.println("No DataSource is available. We will create a new one.");
			createDataSource();
		}
		return dataSource;
	}

	//To create a DataSource and assigning it to variable dataSource.
	private static void createDataSource() {
		HikariConfig hikariConfig = getHikariConfig();
		System.out.println("Configuration is ready.");
		System.out.println("Creating the HiakriDataSource and assigning it as the global");
		HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);

		dataSource = hikariDataSource;
	}

	 //returns HikariConfig containing JDBC connection properties
	 //which will be used by HikariDataSource object.
	private static HikariConfig getHikariConfig() {
		System.out.println("Creating the config with HikariConfig with maximum pool size of 5");
		HikariConfig hikaConfig = new HikariConfig();

		//This is same as passing the Connection info to the DriverManager class.
		//your jdbc url. in my case it is mysql.
		hikaConfig.setJdbcUrl("jdbc:mysql://localhost:3306/test");
		//username
		hikaConfig.setUsername("root");
		//password
		hikaConfig.setPassword("IWontTellYouThis ;)");
		//driver class name
		hikaConfig.setDriverClassName("com.mysql.jdbc.Driver");

		// Information about the pool
		//pool name. This is optional you don't have to do it.
		hikaConfig.setPoolName("MysqlPool-1");

		//the maximum connection which can be created by or resides in the pool
		hikaConfig.setMaximumPoolSize(5);

		//how much time a user can wait to get a connection from the pool.
		//if it exceeds the time limit then a SQlException is thrown
		hikaConfig.setConnectionTimeout(Duration.ofSeconds(30).toMillis());

		//The maximum time a connection can sit idle in the pool.
		// If it exceeds the time limit it is removed form the pool.
		// If you don't want to retire the connections simply put 0.
		hikaConfig.setIdleTimeout(Duration.ofMinutes(2).toMillis());

		return hikaConfig;
	}
}
