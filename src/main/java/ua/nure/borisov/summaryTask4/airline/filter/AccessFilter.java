package ua.nure.borisov.summaryTask4.airline.filter;

import ua.nure.borisov.summaryTask4.airline.customServlet.AccessRuleContainer;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by User on 31.08.2016.
 */
public class AccessFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String currentRole = (String) ((HttpServletRequest)req).getSession().getAttribute("role");
        boolean result = AccessRuleContainer.checkAccess(((HttpServletRequest) req).getRequestURI(),currentRole);

        if(!result){
            ((HttpServletResponse)resp).sendRedirect("/error?error=AccessDenid");
            return;
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
