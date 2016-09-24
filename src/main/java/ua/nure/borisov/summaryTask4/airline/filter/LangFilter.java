package ua.nure.borisov.summaryTask4.airline.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by User on 30.08.2016.
 */
@WebFilter
public class LangFilter implements Filter {
    public LangFilter() {
        // TODO Auto-generated constructor stub
    }

    public void destroy() {
        // TODO Auto-generated method stub
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println(req.getHeader("Referer"));
        String lang = req.getParameter("lang");
        if (lang != null) {
            req.getSession().setAttribute("lang", lang);
            ((HttpServletResponse) response).sendRedirect(req.getHeader("Referer"));
            return;
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }

}
