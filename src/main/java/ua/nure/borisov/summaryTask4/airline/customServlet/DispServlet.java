package ua.nure.borisov.summaryTask4.airline.customServlet;

import ua.nure.borisov.summaryTask4.airline.customServlet.command.Command;
import ua.nure.borisov.summaryTask4.airline.customServlet.command.dispCommand.CreateRequest;
import ua.nure.borisov.summaryTask4.airline.customServlet.command.dispCommand.LoadAvailableEmployees;
import ua.nure.borisov.summaryTask4.airline.customServlet.command.dispCommand.CreateFlight;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/DispPage")
public class DispServlet extends HttpServlet {
    Map<String,Command> commandMap = new HashMap<String,Command>(){{
        put("createFlight", new CreateFlight());
        put("loadEmployees",new LoadAvailableEmployees());
        put("createRequest",new CreateRequest());
    }};
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            execute(request,response);
    }
    private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String command = request.getParameter("command");

        if(command == null || command.isEmpty()){
            command = "loadEmployees";
        }
        String pathToRedirect = null;
            Command commandToExecute = commandMap.get(command);
            if(commandToExecute == null){
                response.sendRedirect("/error?error='command wasn't found");
            } else {
                pathToRedirect = commandToExecute.execute(request,response);
            }
        if(pathToRedirect == null){
            request.getRequestDispatcher("/CustomView/DispPage.jsp").forward(request,response);
            return;
        }
        response.sendRedirect(pathToRedirect);
    }
}
