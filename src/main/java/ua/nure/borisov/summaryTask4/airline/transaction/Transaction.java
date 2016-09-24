package ua.nure.borisov.summaryTask4.airline.transaction;

import ua.nure.borisov.summaryTask4.airline.connection.ConnectionDB;
import ua.nure.borisov.summaryTask4.airline.dao.impl.CrewDAOImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by User on 09.08.2016.
 */
public class Transaction {
    private static final Logger LOGGER = Logger.getLogger(CrewDAOImpl.class.getName());

    public static Connection startTransaction() {
        Connection connection = null;
        try {
            connection = ConnectionDB.getInstance().getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Exception: ", e);
        }
        return connection;
    }

    public static void endTransaction(Connection connection) {
        try {
            connection.setAutoCommit(true);
            connection.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Exception: ", e);
        }
    }

    public static void rollBackConnection(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Transaction rollback", e);
        }
    }

}
