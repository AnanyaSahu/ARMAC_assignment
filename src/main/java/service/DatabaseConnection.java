package service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.json.JSONObject;

public class DatabaseConnection {

    public Session connectDB(){
        String jdbcUrl = "";

        // Open a new session
        try {
            JSONObject awsSecrets = AWSSecret.getSecret();
            System.out.println(awsSecrets);
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

            jdbcUrl =jdbcUrl.concat("jdbc:mysql://").concat(awsSecrets.get("host").toString())
                   .concat(":").concat(awsSecrets.get("port").toString())
                   .concat("/").concat(awsSecrets.get("dbname").toString());

            configuration.setProperty("hibernate.connection.url",jdbcUrl);
            configuration.setProperty("hibernate.connection.username",  awsSecrets.get("username").toString());
            configuration.setProperty("hibernate.connection.password", awsSecrets.get("password").toString());

            SessionFactory sessionFactory = configuration
                    .buildSessionFactory();

            Session session = sessionFactory.openSession();
            System.out.println("Connected to database successfully!");
            return session;
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        } finally {
            // Close the SessionFactory when done
            //sessionFactory.close();
        }
    }
}
