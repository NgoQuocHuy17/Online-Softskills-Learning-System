package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Course;
import view.CourseDAO;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import model.User;

/**
 *
 * @author ngoqu
 */
@WebServlet(name = "SubjectList", urlPatterns = {"/SubjectList"})
public class SubjectList extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check if the user is logged in and has the appropriate role
        User loggedInUser = (User) request.getSession().getAttribute("user");
        if (loggedInUser == null
                || (!"Teacher".equals(loggedInUser.getRole()) && !"Admin".equals(loggedInUser.getRole()))) {
            // Redirect to "course" page if the user is not a Teacher or Admin
            response.sendRedirect("course");
            return;
        }

        // Existing code for fetching and displaying subjects
        CourseDAO courseDAO = new CourseDAO();
        String searchTitle = request.getParameter("searchTitle");
        String status = request.getParameter("status");
        String pageStr = request.getParameter("page");

        // Default values for pagination and filters
        int page = (pageStr == null || pageStr.isEmpty()) ? 1 : Integer.parseInt(pageStr);
        int pageSize = 5;

        // Retrieve courses and filter based on search title and status
        List<Course> allCourses = courseDAO.getAllCourses();
        List<String> statuses = courseDAO.getAllStatuses();
        request.setAttribute("statuses", statuses);

        if (searchTitle != null && !searchTitle.isEmpty()) {
            allCourses = courseDAO.getSubjectByTitle(searchTitle);
        }

        if (status == null || status.isEmpty()) {
            status = "All";
        }

        final String filterStatus = status;
        if (!filterStatus.equals("All")) {
            allCourses = allCourses.stream()
                    .filter(course -> filterStatus.equals(course.getStatus()))
                    .collect(Collectors.toList());
        }

        // Pagination logic
        int totalCourses = allCourses.size();
        int totalPages = (int) Math.ceil((double) totalCourses / pageSize);
        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, totalCourses);
        List<Course> paginatedCourses = allCourses.subList(fromIndex, toIndex);

        // Set attributes for the request
        request.setAttribute("courses", paginatedCourses);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("searchTitle", searchTitle);
        request.setAttribute("status", filterStatus);

        RequestDispatcher dispatcher = request.getRequestDispatcher("subjectList.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Handles Subject List with role-based access control";
    }
}
