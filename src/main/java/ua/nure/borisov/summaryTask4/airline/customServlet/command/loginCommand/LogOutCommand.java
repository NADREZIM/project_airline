package ua.nure.borisov.summaryTask4.airline.customServlet.command.loginCommand;

import ua.nure.borisov.summaryTask4.airline.customServlet.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("userData",null);
        request.getSession().setAttribute("role",null);
        return "/login";
    }
}
