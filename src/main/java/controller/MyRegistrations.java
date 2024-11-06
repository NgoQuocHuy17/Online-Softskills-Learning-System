package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

import model.Course;
import model.Registration;
import model.User;
import model.Package;
import view.RegistrationDAO;
import view.CourseDAO;
import view.PackageDAO;

@WebServlet(name = "MyRegistrations", urlPatterns = {"/MyRegistrations"})
public class MyRegistrations extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RegistrationDAO registrationDAO = new RegistrationDAO();
        CourseDAO courseDAO = new CourseDAO();
        PackageDAO packageDAO = new PackageDAO();

        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        String category = request.getParameter("category");
        String searchTerm = request.getParameter("searchTerm");

        // Get pageSize from request, default to 5 if not provided or invalid
        int pageSize = 5; // Default pageSize
        String pageSizeParam = request.getParameter("pageSize");
        if (pageSizeParam != null && !pageSizeParam.isEmpty()) {
            try {
                pageSize = Integer.parseInt(pageSizeParam);
            } catch (NumberFormatException e) {
                pageSize = 5; // Fallback to default if parsing fails
            }
        }

        // Get page from request, default to 1 if not provided
        int page = 1; // Default page
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                page = 1; // Fallback to default if parsing fails
            }
        }

        // Fetch registrations with pagination
        List<Registration> registrations = registrationDAO.getRegistrationsByUserIdWithPagination(userId, page, pageSize, category, searchTerm);

        // Retrieve course and package details for each registration
        for (Registration registration : registrations) {
            Course course = courseDAO.getCourseById(registration.getCourseId());
            request.setAttribute("course_" + registration.getId(), course);

            Package pkg = packageDAO.getPackageById(registration.getPackageId());
            request.setAttribute("package_" + registration.getId(), pkg);
        }

        // Calculate total pages
        int totalRegistrations = registrationDAO.getTotalRegistrations(userId, category, searchTerm);
        int totalPages = (int) Math.ceil((double) totalRegistrations / pageSize);

        // Set attributes for JSP
        request.setAttribute("registrations", registrations);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("categories", courseDAO.getAllCategories());

        // Retrieve "show fields" parameters
        // Set default values to true
        boolean showTitle = true;
        boolean showCategory = true;
        boolean showPackage = true;
        boolean showCost = true;
        boolean showStatus = true;
        boolean showValid = true;

        // Override defaults if parameters are present
        showTitle = request.getParameter("showTitle") != null;
        showCategory = request.getParameter("showCategory") != null;
        showPackage = request.getParameter("showPackage") != null;
        showCost = request.getParameter("showCost") != null;
        showStatus = request.getParameter("showStatus") != null;
        showValid = request.getParameter("showValid") != null;

        // Set attributes to pass to JSP
        request.setAttribute("showTitle", showTitle);
        request.setAttribute("showCategory", showCategory);
        request.setAttribute("showPackage", showPackage);
        request.setAttribute("showCost", showCost);
        request.setAttribute("showStatus", showStatus);
        request.setAttribute("showValid", showValid);

        // Forward to JSP
        request.getRequestDispatcher("/myRegistrations.jsp").forward(request, response);
    }
}
