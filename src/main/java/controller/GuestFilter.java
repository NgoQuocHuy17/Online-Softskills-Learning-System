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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD:src/main/java/controller/FilterGuest.java
@WebFilter({"/profile-settings.jsp", "/my-registrations.jsp", "/my-courses.jsp", "/subject-list.jsp", "/subject-details.jsp", "blog-list.jsp"})
public class FilterGuest implements Filter {
=======
@WebFilter({"/blog-list.html", "/profile.jsp", "/profile.html", "/changepass"})
public class GuestFilter implements Filter {
>>>>>>> parent of 7104985 (updated Filters):src/main/java/controller/GuestFilter.java
=======
@WebFilter({"/blog-list.html", "/profile.jsp", "/profile.html", "/changepass"})
public class GuestFilter implements Filter {
>>>>>>> parent of 7104985 (updated Filters)
=======
@WebFilter({"/blog-list.html", "/profile.jsp", "/profile.html", "/changepass"})
public class GuestFilter implements Filter {
>>>>>>> parent of 7104985 (updated Filters)

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);

        User user = (User) session.getAttribute("user");
        String role = user.getRole();
        
        String path = httpRequest.getRequestURI();
        System.out.println(path);
        
        if (path.equals(httpRequest.getContextPath() + "/login.jsp") || path.equals(httpRequest.getContextPath() + "/login")) {
            chain.doFilter(request, response);
            return;
        }

        if (session == null || session.getAttribute("user") == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
            return;
        }

        if (path.startsWith(httpRequest.getContextPath() + "/blog-list.html") && !("USER".equals(role) || !"ADMIN".equals(role))) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
            return;
        }

        chain.doFilter(request, response);
    }
}
