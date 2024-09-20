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
@WebFilter({"/admin/*"})
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);

        String path = httpRequest.getRequestURI();
        System.out.println(path);
        
        if (path.equals(httpRequest.getContextPath() + "/login.jsp") || path.equals(httpRequest.getContextPath() + "/login")) {
            chain.doFilter(request, response);
            return;
        }

        if (session == null || session.getAttribute("email") == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        String role = user.getRole();

        if (path.startsWith(httpRequest.getContextPath() + "/admin") && !"ADMIN".equals(role)) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/user");
            return;
        }

        if (path.startsWith(httpRequest.getContextPath() + "/user") && !("USER".equals(role) || "ADMIN".equals(role))) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
            return;
        }

        chain.doFilter(request, response);
    }
}
