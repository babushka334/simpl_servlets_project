package by.mops.servlets;


import by.mops.models.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/userHomePage")
public class ServletFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);

        String loginURL = request.getContextPath() + "/login";
        String registerURL = request.getContextPath() + "/register";

        boolean loggedIn = session != null && session.getAttribute("User") != null;
        boolean loginRequest = request.getRequestURI().equals(loginURL) || request.getRequestURI().equals(loginURL + ".html");
        boolean registerRequest = request.getRequestURI().equals(registerURL) || request.getRequestURI().equals(registerURL + ".html");

        if(loggedIn || loginRequest || registerRequest) {
            if(loggedIn && !((User)session.getAttribute("User")).getIsAdmin()){
                response.sendRedirect("userPage");
            }
            filterChain.doFilter(req, resp);
        }
        else {
            response.sendRedirect("login");
        }




    }





    @Override
    public void destroy() {
    }
}
