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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ngoqu
 */
@WebServlet(name = "SubjectList", urlPatterns = {"/SubjectList"})
public class SubjectList extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        CourseDAO courseDAO = new CourseDAO();
        String searchTitle = request.getParameter("searchTitle");
       
        String status = request.getParameter("status");
        String pageStr = request.getParameter("page"); // Get the page number from the request

        // Set default values for pagination and filters
        int page = (pageStr == null || pageStr.isEmpty()) ? 1 : Integer.parseInt(pageStr);
        int pageSize = 5; // Number of courses per page
       
        // Default status
        // Retrieve the list of all courses and statuses for filtering
        List<Course> allCourses = courseDAO.getAllCourses();
        List<String> statuses = courseDAO.getAllStatuses();
        request.setAttribute("statuses", statuses);

        // Filter courses by search title (if provided)
        if (searchTitle != null && !searchTitle.isEmpty()) {
            allCourses = courseDAO.getSubjectByTitle(searchTitle);
        }

        // Filter courses by status (if not "All")
         if (status == null || status.isEmpty()) {
            status = "All";
        }
          List<Course> filteredCourses = new ArrayList<>();
        if (!status.equals("All")) {
            for (Course course : allCourses) {
                if (course.getStatus().equals(status)) {
                    filteredCourses.add(course);
                }
            }
            allCourses = filteredCourses; // Update allCourses to be the filtered list
        }

        // Pagination logic
        int totalCourses = allCourses.size();
        int totalPages = (int) Math.ceil((double) totalCourses / pageSize);
        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, totalCourses);

        // Get the courses for the current page
        List<Course> paginatedCourses = allCourses.subList(fromIndex, toIndex);

        // Set the attributes for the request
        request.setAttribute("courses", paginatedCourses);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("searchTitle", searchTitle);
        request.setAttribute("status", status);

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
        return "Short description";
    }
}
