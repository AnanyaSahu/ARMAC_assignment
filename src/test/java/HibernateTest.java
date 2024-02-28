import org.json.JSONObject;
import org.junit.Test;
import service.AWSSecret;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HibernateTest {
    @Test
    public void testDbConnection() {
        // JDBC URL for connecting to MySQL database
//        JSONObject awsSecrets = AWSSecret.getSecret();
//
//        String jdbcUrl ="".concat("jdbc:mysql://").concat(awsSecrets.get("host").toString())
//                .concat(":").concat(awsSecrets.get("port").toString())
//                .concat("/").concat(awsSecrets.get("dbname").toString());
//
//        String username = awsSecrets.get("username").toString();
//        String password = awsSecrets.get("password").toString();
//
//        try {
//
//            // Load MySQL JDBC driver
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            // Establish database connection
//            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
//
//            // Print a success message if the connection is successful
//            System.out.println("Connected to the database successfully!");
//
//            // Close the connection
//            connection.close();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
