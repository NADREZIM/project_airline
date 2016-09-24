package ua.nure.borisov.summaryTask4.airline.customServlet.adminServlet;

import ua.nure.borisov.summaryTask4.airline.customServlet.command.Command;
import ua.nure.borisov.summaryTask4.airline.customServlet.command.adminEmployeeCommand.*;
import ua.nure.borisov.summaryTask4.airline.dto.EmployeeDTO;
import ua.nure.borisov.summaryTask4.airline.service.api.EmployeeService;
import ua.nure.borisov.summaryTask4.airline.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/AdminPage/Employee")
public class EmployeeServlet extends HttpServlet {
    Map<String,Command> commandMap = new HashMap<String,Command>(){{
        put("createEmployee",new CreateEmployeeCommand());
        put("deleteEmployee", new DeleteEmployeeCommand());
        put("updateEmployee", new UpdateEmployeeCommand());
        put("drawForm", new DrawForm());
    }};
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        if(command!=null && !command.isEmpty()){
            execute(request,response);
        } else {
            EmployeeService employeeService = new EmployeeServiceImpl();
            List<EmployeeDTO> allEmployees = employeeService.getAllEmployees();
            request.setAttribute("allEmployees",allEmployees);
            request.getRequestDispatcher("/CustomView/EmployeePage.jsp").forward(request,response);
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
            request.getRequestDispatcher("/CustomView/EmployeePage.jsp").forward(request,response);
            return;
        }
        if (pathToRedirect.equals("draw")){
            return;
        }
        response.sendRedirect(pathToRedirect);
    }
}
