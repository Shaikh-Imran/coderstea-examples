package howto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchInsertionInJdbc {

    public static void main(String[] args) {
        final String empInsertSql = "INSERT INTO employee (id, name, salary) VALUES(?,?,?)";
        try (
                // set up the connection in another function. This is just for example
                Connection con = DriverManager.getConnection("");
                PreparedStatement pstmt = con.prepareStatement(empInsertSql)
        ) {

            for (int i = 0; i < 1_000_000; i++) {
                // replace with your data setup
                pstmt.setInt(1, i);
                pstmt.setString(2, "name_" + i);
                pstmt.setInt(3, i * 100);
                pstmt.addBatch();
                pstmt.clearParameters();

                // I a using the batch of 2000 records at a time
                if (i % 2000 == 0) {
                    int[] results = pstmt.executeBatch();
                    // loop to check the result of each record
                    // need index as we might have to identify the failed record
                    // with some calculation with batch and i's value
                    for (int k = 0; k < results.length; k++) {
                        // if it is a minus value, then the execution failed for that record
                        if (results[k] <= 0) {
                            // if it failed for this record do something
                        }

                    }
                }
            }
            // to insert whatever is remained in the batch
            int[] results = pstmt.executeBatch();
            // loop to check the result of each record
            for (int k = 0; k < results.length; k++) {
                // if it is a minus value, then the execution failed for that record
                if (results[k] <= 0) {
                    // if it failed for this record do something
                }
            }

            System.out.println("The data is inserted successfully with batches of 2000");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
