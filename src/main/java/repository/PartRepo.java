package repository;

import model.Part;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import service.DatabaseConnection;

public class PartRepo {

    public void savePartData(Part part){
        DatabaseConnection dcSession= new DatabaseConnection();
        Session session = dcSession.connectDB();

        // Begin a transaction
        Transaction transaction = session.beginTransaction();
        try {
            // Create an instance of your entity
            Part savePart = new Part(part.getCode(),
                    part.getDescription(),
                    part.getPurchaseTimePlanned(),
                    part.getCost(),
                    part.getDemandForecast(),
                    part.getDemandPLCDO(),
                    part.getMin());

            // Save the entity
            session.save("Part",savePart);

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            // Rollback the transaction if an exception occurs
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            session.close();
//            sessionFactory.close();
        }

    }

    public Double getAvgPurchaseTimePlanned(){
        DatabaseConnection dcSession= new DatabaseConnection();
        Session session = dcSession.connectDB();

        // Begin a transaction
        Transaction transaction = session.beginTransaction();
        try {
            Query<Double> query = session.createQuery("select avg(purchaseTimePlanned) from Part", Double.class);
            // Execute the query and get the result
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            session.close();
//            sessionFactory.close();
        }

    }

    public Double getMaxCost() {
        DatabaseConnection dcSession= new DatabaseConnection();
        Session session = dcSession.connectDB();

        // Begin a transaction
        Transaction transaction = session.beginTransaction();
        try {
            Query<Double> query = session.createQuery("select max(cost) from Part", Double.class);
            // Execute the query and get the result
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            session.close();
//            sessionFactory.close();
        }
    }
}
