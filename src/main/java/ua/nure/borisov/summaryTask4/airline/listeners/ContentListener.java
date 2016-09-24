package ua.nure.borisov.summaryTask4.airline.listeners;


import ua.nure.borisov.summaryTask4.airline.InitDataBase.DataBaseInit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;


public class ContentListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String realPath = servletContextEvent.getServletContext().getRealPath("resources/sqlDataBaseCreation/sqlScript.sql");
        DataBaseInit dataBase = new DataBaseInit();
        try {
            dataBase.createDataBase(realPath);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
