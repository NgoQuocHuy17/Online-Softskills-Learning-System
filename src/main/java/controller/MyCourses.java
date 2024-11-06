package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import model.Course;
import model.User;
import view.CourseDAO;
import view.UserCourseDAO;

@WebServlet(name = "MyCourses", urlPatterns = {"/MyCourses"})
public class MyCourses extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Retrieve the logged-in user from the session
        User user = (User) session.getAttribute("user");
        int userId = user.getId();

        // Initialize DAOs
        UserCourseDAO userCourseDAO = new UserCourseDAO();
        CourseDAO courseDAO = new CourseDAO();

        // Get pagination parameters
        int page = 1; // Default page
        int pageSize = 5; // Default page size
        String pageParam = request.getParameter("page");
        String pageSizeParam = request.getParameter("pageSize");

        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                page = 1; // Fallback to default if parsing fails
            }
        }

        if (pageSizeParam != null && !pageSizeParam.isEmpty()) {
            try {
                pageSize = Integer.parseInt(pageSizeParam);
            } catch (NumberFormatException e) {
                pageSize = 5; // Fallback to default if parsing fails
            }
        }

        // Get all course IDs that the user has access to with pagination
        List<Integer> courseIds = userCourseDAO.getCourseIdsByUserId(userId, page, pageSize);

        // Fetch course details using course IDs
        List<Course> courseList = new ArrayList<>();
        for (int courseId : courseIds) {
            Course course = courseDAO.getCourseById(courseId);
            if (course != null) {
                courseList.add(course);
            }
        }

        // Set the list of courses as an attribute and forward to myCourses.jsp
        request.setAttribute("courseList", courseList);

        // Set pagination attributes
        int totalCourses = userCourseDAO.getTotalCoursesByUserId(userId); // You need to implement this method
        int totalPages = (int) Math.ceil((double) totalCourses / pageSize);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("pageSize", pageSize);

        // Retrieve show field parameters with default values
        boolean showTitle = true;
        boolean showTagline = true;
        boolean showDescription = true;
        boolean showCategory = true;

        showTitle = request.getParameter("showTitle") != null;
        showTagline = request.getParameter("showTagline") != null;
        showDescription = request.getParameter("showDescription") != null;
        showCategory = request.getParameter("showCategory") != null;

        // Set show field parameters as attributes
        request.setAttribute("showTitle", showTitle);
        request.setAttribute("showTagline", showTagline);
        request.setAttribute("showDescription", showDescription);
        request.setAttribute("showCategory", showCategory);

        // Forward to JSP
        request.getRequestDispatcher("myCourses.jsp").forward(request, response);
    }
}
