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
@WebFilter({"/blog-list.html", "/profile.jsp", "/profile.html", "/changepass", "/bloglist", "/home"})
public class GuestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);

        
        String path = httpRequest.getRequestURI();
        System.out.println(path);
        
        if (session == null || session.getAttribute("user") == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
            return;
        }
        
        User user = (User) session.getAttribute("user");
        String role = user.getRole();
        
        
        if (path.equals(httpRequest.getContextPath() + "/login.jsp") || path.equals(httpRequest.getContextPath() + "/login")) {
            chain.doFilter(request, response);
            return;
        }

        

        if (path.startsWith(httpRequest.getContextPath() + "/blog-list.html") && !("USER".equals(role) || !"ADMIN".equals(role))) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
            return;
        }

        chain.doFilter(request, response);
    }
}
