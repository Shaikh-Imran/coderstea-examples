package howto;

import java.sql.*;

public class SimpleDatabaseOperation {

    public int getCountOfUsersFromDb(String url, String username, String passowrd) {
        try (
                Connection conn = DriverManager.getConnection(url, username, passowrd);
                Statement stmt = conn.createStatement();
                ResultSet resultSet = stmt.executeQuery("SELECt count(*) FROM employee")
        ) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}