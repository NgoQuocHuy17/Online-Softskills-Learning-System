package controller;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.User;

/**
 *
 * @author Minh
 */
@WebFilter({"/login.jsp", "/logout.jsp", "/register.jsp", "/forgot-password.jsp", "/new-password.jsp"})
public class FilterLoggedIn implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);
        User user = null;

        // Check if session exists and user is logged in
        if (session != null) {
            user = (User) session.getAttribute("user");
        }

        //if user is logged in redirect to home
        if (user != null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/home");
            return;
        }

        // Allow access to other pages
        chain.doFilter(request, response);
    }
}
