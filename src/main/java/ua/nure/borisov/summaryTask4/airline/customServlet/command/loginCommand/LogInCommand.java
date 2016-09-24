package ua.nure.borisov.summaryTask4.airline.customServlet.command.loginCommand;

import ua.nure.borisov.summaryTask4.airline.customServlet.command.Command;
import ua.nure.borisov.summaryTask4.airline.dao.api.UserDAO;
import ua.nure.borisov.summaryTask4.airline.dao.impl.UserDAOImpl;
import ua.nure.borisov.summaryTask4.airline.entity.User;
import ua.nure.borisov.summaryTask4.airline.exception.DAOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class LogInCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String,String> errors = new HashMap<String,String>();
        request.setAttribute("errors", errors);

        String loginField = request.getParameter("login");
        if(loginField == null || loginField.isEmpty()){
            errors.put("login","Login field must be filled");
        }

        String passwordField = request.getParameter("password");
        if(passwordField == null || passwordField.isEmpty()){
            errors.put("password","Password field must be filled");
            return null;
        }

        UserDAO userDAO = new UserDAOImpl();
        try {
            User user = userDAO.getUser(loginField,passwordField);
            if(user == null) {
                errors.put("global", "No user was found");
                return null;
            }
            request.getSession().setAttribute("userData", user);
            if(user.getRole().endsWith("disp")){
                request.getSession().setAttribute("role","disp");
                return "/DispPage";
            }
            request.getSession().setAttribute("role","admin");
        } catch (DAOException e) {
            e.printStackTrace();
            errors.put("global","Try later");
            return  null;
        }
        return "/AdminPage";
    }
}
