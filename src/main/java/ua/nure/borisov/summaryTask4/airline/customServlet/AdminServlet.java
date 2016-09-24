package ua.nure.borisov.summaryTask4.airline.customServlet;

import ua.nure.borisov.summaryTask4.airline.customServlet.command.Command;
import ua.nure.borisov.summaryTask4.airline.customServlet.command.adminCommand.EmployeeCommand;
import ua.nure.borisov.summaryTask4.airline.customServlet.command.adminCommand.FlightsCommand;
import ua.nure.borisov.summaryTask4.airline.customServlet.command.adminCommand.GetRequests;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/AdminPage")
public class AdminServlet extends HttpServlet {
    Map<String,Command> commandMap = new HashMap<String,Command>(){{
        put("request", new GetRequests());
        put("flights", new FlightsCommand());
        put("employee", new EmployeeCommand());
    }};

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        if(command!=null && !command.isEmpty()){
            execute(request,response);
        } else {
            request.getRequestDispatcher("/CustomView/AdminPage.jsp").forward(request,response);
        }
    }
    private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String command = request.getParameter("command");
        String pathToRedirect = null;
        if(command != null && !command.isEmpty()){
            Command commandToExecute = commandMap.get(command);
            if(commandToExecute == null){
                response.sendRedirect("/error?error='command wasn't found");
            } else {
                pathToRedirect = commandToExecute.execute(request,response);
            }
        }
        if(pathToRedirect == null){
            request.getRequestDispatcher("/CustomView/AdminPage.jsp").forward(request,response);
            return;
        }
        response.sendRedirect(pathToRedirect);
    }

}
