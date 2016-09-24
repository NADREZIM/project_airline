package ua.nure.borisov.summaryTask4.airline.customServlet.adminServlet;

import ua.nure.borisov.summaryTask4.airline.customServlet.command.Command;
import ua.nure.borisov.summaryTask4.airline.customServlet.command.adminRequestCommand.RequestRejectCommand;
import ua.nure.borisov.summaryTask4.airline.customServlet.command.flightsCommand.UpdateFlightByRequest;
import ua.nure.borisov.summaryTask4.airline.dto.RequestDTO;
import ua.nure.borisov.summaryTask4.airline.service.api.RequestService;
import ua.nure.borisov.summaryTask4.airline.service.impl.RequestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/AdminPage/Requests")
public class RequestServlet extends HttpServlet {
    Map<String, Command> commandMap = new HashMap<String, Command>() {{
        put("reject", new RequestRejectCommand());
        put("updateFlightByRequest",new UpdateFlightByRequest());
    }};

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("command") != null && !request.getParameter("command").isEmpty()) {
            execute(request, response);
        } else {
            RequestService requestService = new RequestServiceImpl();
            List<RequestDTO> requestDTOList = requestService.getAllRequests();
            int requestDTOListSize = requestDTOList.size();

            request.setAttribute("allRequests", requestDTOList);
            request.setAttribute("size", requestDTOListSize);
            request.getRequestDispatcher("/CustomView/RequestPage.jsp").forward(request, response);
        }

    }

    private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        String pathToRedirect = null;
        if (command != null && !command.isEmpty()) {
            Command commandToExecute = commandMap.get(command);
            if (commandToExecute == null) {
                response.sendRedirect("/error?error='command wasn't found");
            } else {
                pathToRedirect = commandToExecute.execute(request, response);
            }
        }
        if (pathToRedirect == null) {
            request.getRequestDispatcher("/CustomView/RequestPage.jsp").forward(request, response);
            return;
        }
        response.sendRedirect(pathToRedirect);
    }
}
