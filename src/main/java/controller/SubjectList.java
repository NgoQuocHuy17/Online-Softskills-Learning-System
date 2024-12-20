package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Course;
import model.User;
import view.CourseDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "SubjectList", urlPatterns = {"/SubjectList"})
public class SubjectList extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve the logged-in user from the session
        User loggedInUser = (User) request.getSession().getAttribute("user");
        if (loggedInUser == null
                || (!"Teacher".equals(loggedInUser.getRole()) && !"Admin".equals(loggedInUser.getRole()))) {
            // Redirect to "course" page if the user is not a Teacher or Admin
            response.sendRedirect("course");
            return;
        }

        // Set the loggedInUser as an attribute to pass to the JSP
        request.setAttribute("loggedInUser", loggedInUser);

        CourseDAO courseDAO = new CourseDAO();
        String searchTitle = request.getParameter("searchTitle");
        String status = request.getParameter("status");
        String pageStr = request.getParameter("page");
        //visibility section
        
        boolean showId = Boolean.parseBoolean(request.getParameter("showId"));
        boolean showTitle = Boolean.parseBoolean(request.getParameter("showTitle"));
        boolean showCategory = Boolean.parseBoolean(request.getParameter("showCategory"));
        boolean showLessonNumber = Boolean.parseBoolean(request.getParameter("showLessonNumber"));
        boolean showLessonName = Boolean.parseBoolean(request.getParameter("showLessonName"));
        boolean showOwner = Boolean.parseBoolean(request.getParameter("showOwner"));
        boolean showStatus = Boolean.parseBoolean(request.getParameter("showStatus"));
        boolean showTagLine = Boolean.parseBoolean(request.getParameter("showTagLine"));
        boolean showDescription = Boolean.parseBoolean(request.getParameter("showDescription"));
        boolean showActions = Boolean.parseBoolean(request.getParameter("showActions"));

        request.setAttribute("showId", showId);
        request.setAttribute("showTitle", showTitle);
        request.setAttribute("showCategory", showCategory);
        request.setAttribute("showLessonNumber", showLessonNumber);
        request.setAttribute("showLessonName", showLessonName);
        request.setAttribute("showOwner", showOwner);
        request.setAttribute("showStatus", showStatus);
        request.setAttribute("showTagLine", showTagLine);
        request.setAttribute("showDescription", showDescription);
        request.setAttribute("showActions", showActions);

        // Default values for pagination and filters
        int page = (pageStr == null || pageStr.isEmpty()) ? 1 : Integer.parseInt(pageStr);
        int pageSize = request.getParameter("itemsPerPage") != null ? Integer.parseInt(request.getParameter("itemsPerPage")) : 5;

        // Retrieve list of statuses
        List<String> statuses = courseDAO.getAllStatuses();
        request.setAttribute("statuses", statuses);

        // Retrieve courses and filter based on search title and status
        List<Course> allCourses = courseDAO.getAllCourses();
        if (loggedInUser.getRole().equalsIgnoreCase("Teacher")) {
            allCourses = courseDAO.selectAllById(loggedInUser.getId());
        }

        if (searchTitle != null && !searchTitle.isEmpty()) {
            allCourses = courseDAO.getSubjectByTitle(searchTitle);
        }
        if (searchTitle != null && !searchTitle.isEmpty() && loggedInUser.getRole().equalsIgnoreCase("Teacher")) {
            allCourses = courseDAO.getCourseByTitleAndOwnerId(searchTitle, loggedInUser.getId());
        }
        if (status == null || status.isEmpty()) {
            status = "All";
        }
        boolean hide = allCourses.isEmpty();
        request.setAttribute("hide", hide);
        final String filterStatus = status;
        if (!filterStatus.equals("All")) {
            allCourses = allCourses.stream()
                    .filter(course -> filterStatus.equals(course.getStatus()))
                    .collect(Collectors.toList());
        }

        // Sort courses by ownership if the user is a "Teacher"
        List<Course> sortedCourses;
        if ("Teacher".equals(loggedInUser.getRole())) {
            // Get courses owned by the logged-in teacher
            List<Course> ownedCourses = allCourses.stream()
                    .filter(course -> course.getOwnerId() == loggedInUser.getId())
                    .collect(Collectors.toList());

            // Get other courses not owned by the teacher
            List<Course> otherCourses = allCourses.stream()
                    .filter(course -> course.getOwnerId() != loggedInUser.getId())
                    .collect(Collectors.toList());

            // Combine the lists with the teacher's courses first
            sortedCourses = new ArrayList<>();
            sortedCourses.addAll(ownedCourses);
            sortedCourses.addAll(otherCourses);
        } else {
            // Admin users see all courses in the default order
            sortedCourses = allCourses;
        }

        // Pagination logic for the sorted courses
        int totalCourses = sortedCourses.size();
        int totalPages = (int) Math.ceil((double) totalCourses / pageSize);
        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, totalCourses);
        List<Course> paginatedCourses = sortedCourses.subList(fromIndex, toIndex);

        // Set attributes for the request
        request.setAttribute("courses", paginatedCourses);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("itemsPerPage", pageSize);
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
