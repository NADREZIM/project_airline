package ua.nure.borisov.summaryTask4.airline.InitDataBase;

import com.ibatis.common.jdbc.ScriptRunner;
import ua.nure.borisov.summaryTask4.airline.connection.CreateSchemaConnection;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;


public class DataBaseInit {
    public void createDataBase(String s) throws SQLException {
        Connection connection = CreateSchemaConnection.getInstance().getConnection();
        try {
            ScriptRunner scriptRunner = new ScriptRunner(connection, false, false);
            Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream(s),"UTF-8"));
            scriptRunner.runScript(reader);

        } catch (Exception e) {
            System.err.println("Failed to Execute" + s
                    + " The error is " + e.getMessage());
        } finally {
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }
}
