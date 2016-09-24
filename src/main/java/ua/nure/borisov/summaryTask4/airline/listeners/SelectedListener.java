package ua.nure.borisov.summaryTask4.airline.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Arrays;
import java.util.List;

/**
 * Created by User on 16.08.2016.
 */
public class SelectedListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        List<String> cities = Arrays.asList(
                sc.getInitParameter("cities").split(" "));
        sc.setAttribute("cities", cities);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
