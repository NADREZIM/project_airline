package ua.nure.borisov.summaryTask4.airline.connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

/**
 * Created by User on 25.09.2016.
 */
public class CreateSchemaConnection {
    private static CreateSchemaConnection dataSource;
    private ComboPooledDataSource cpds;

    private CreateSchemaConnection() {
        cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass("com.mysql.jdbc.Driver");
            cpds.setJdbcUrl("jdbc:mysql://localhost:3306");
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

    public static CreateSchemaConnection getInstance() {
        if (dataSource == null) {
            dataSource = new CreateSchemaConnection();
        }
        return dataSource;
    }

    public java.sql.Connection getConnection() throws SQLException {
        return this.cpds.getConnection();
    }
}
