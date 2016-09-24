package ua.nure.borisov.summaryTask4.airline.connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

public class ConnectionDB {
    private static ConnectionDB dataSource;
    private ComboPooledDataSource cpds;

    private ConnectionDB() {
        cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass("com.mysql.jdbc.Driver");
            cpds.setJdbcUrl("jdbc:mysql://localhost:3306/airline");
            cpds.setUser("root");
            cpds.setPassword("root");
            cpds.setMinPoolSize(5);
            cpds.setAcquireIncrement(5);
            cpds.setMaxPoolSize(20);
            cpds.setMaxStatements(180);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionDB getInstance() {
        if (dataSource == null) {
            dataSource = new ConnectionDB();
        }
        return dataSource;
    }

    public java.sql.Connection getConnection() throws SQLException {
        return this.cpds.getConnection();
    }
}
