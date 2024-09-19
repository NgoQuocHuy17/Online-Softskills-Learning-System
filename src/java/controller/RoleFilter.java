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

/**
 *
 * @author Minh
 */

@WebFilter("/*")
public class RoleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);

        String path = httpRequest.getRequestURI();
        
        if (path.equals("/login.jsp") || path.equals("/login")) {
            chain.doFilter(request, response);
            return;
        }

        if (session == null || session.getAttribute("email") == null) {
            httpResponse.sendRedirect("login.jsp");
            return;
        }

        String role = (String) session.getAttribute("role");

        if (path.startsWith("/admin") && !"ADMIN".equals(role)) {
            httpResponse.sendRedirect("user");
            return;
        }

        if (path.startsWith("/user") && !("USER".equals(role) || "ADMIN".equals(role))) {
            httpResponse.sendRedirect("login.jsp");
            return;
        }

        chain.doFilter(request, response);
    }
}
