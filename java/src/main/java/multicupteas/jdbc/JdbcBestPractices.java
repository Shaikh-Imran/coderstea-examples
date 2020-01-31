package multicupteas.jdbc;

import java.sql.*;

public class JdbcBestPractices {
    static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("your-sql-driver-name");

        return DriverManager.getConnection("url", "user", "pass");

    }
}

//DAO class for handling database
class EmployeeDao {

    public int getCountOfEmployee() {

        // writing all the things in the try-with-resources
        // every object will be closed automatically
        try (Connection con =
                     JdbcBestPractices.getConnection(); // seperate connection creation function
             PreparedStatement pstmt = con.prepareStatement("SELECT COUNT(*) FROM employee");
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()){
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public void insertNewEmployee(){
String insertQuery = " SELECT id FROM employee WHERE name  = ? "; // '?' will be replaced with the values
try (Connection con = JdbcBestPractices.getConnection();
     PreparedStatement pstmt = con.prepareStatement(insertQuery)
     ) {
    //fetch only 1000 data at a time
    pstmt.setFetchSize(1000);

    //setting name value
    pstmt.setString(1,"name");
    ResultSet rs = pstmt.executeQuery();
    while(rs.next()){
        //todo
    }

    rs.close();

} catch (Exception e) {
    e.printStackTrace();
}
    }

}
