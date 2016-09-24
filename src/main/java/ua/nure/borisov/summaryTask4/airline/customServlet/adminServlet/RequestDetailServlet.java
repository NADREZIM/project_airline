package ua.nure.borisov.summaryTask4.airline.customServlet.adminServlet;

import ua.nure.borisov.summaryTask4.airline.customServlet.command.Command;
import ua.nure.borisov.summaryTask4.airline.customServlet.command.requestDetailInfoCommand.BaseDataLoad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/AdminPage/Requests/DetailInfo")
public class RequestDetailServlet extends HttpServlet {
    Map<String, Command> commandMap = new HashMap<String, Command>() {{
        put("baseUpload", new BaseDataLoad());
    }};

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        if (command != null && !command.isEmpty()) {
            execute(request, response);
        } else {
            request.getRequestDispatcher("/CustomView/AdminPage.jsp").forward(request, response);
        }
    }

    private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        if (command != null && !command.isEmpty()) {
            command = "baseUpload";
        }

        String pathToRedirect = null;

        Command commandToExecute = commandMap.get(command);
        if (commandToExecute == null) {
            response.sendRedirect("/error?error='command wasn't found");
        } else {
            pathToRedirect = commandToExecute.execute(request, response);
        }

        if (pathToRedirect == null) {
                request.getRequestDispatcher("/CustomView/UpdateFlightByRequest.jsp").forward(request, response);
            return;
        }
        response.sendRedirect(pathToRedirect);
    }
}
