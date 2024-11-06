package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.CourseDAO;
import model.Course;
import java.io.IOException;
import java.util.List;

// File: CourseListServlet.java
@WebServlet("/course")
public class CourseListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CourseDAO courseDAO = new CourseDAO();

        List<String> categories = courseDAO.getAllCategories();
        request.setAttribute("categories", categories);

        String searchTitle = request.getParameter("searchTitle");
        String category = request.getParameter("category");

        if (category == null || category.isEmpty()) {
            category = "All";
        }

        int page = 1;
        int pageSize = 5;
        String pageParam = request.getParameter("page");
        String pageSizeParam = request.getParameter("pageSize");

        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        if (pageSizeParam != null && !pageSizeParam.isEmpty()) {
            try {
                pageSize = Integer.parseInt(pageSizeParam);
            } catch (NumberFormatException e) {
                pageSize = 5;
            }
        }

        List<Course> courses;
        int totalCourses;

        if ((searchTitle != null && !searchTitle.isEmpty()) && !"All".equals(category)) {
            courses = courseDAO.getCoursesByTitleAndCategory(searchTitle, category, page, pageSize);
            totalCourses = courseDAO.getTotalCoursesByTitleAndCategory(searchTitle, category);
        } else if (searchTitle != null && !searchTitle.isEmpty()) {
            courses = courseDAO.getCoursesByTitle(searchTitle);
            totalCourses = courses.size();
        } else {
            courses = courseDAO.getCoursesByCategory(category, page, pageSize);
            totalCourses = courseDAO.getTotalCoursesByCategory(category);
        }

        int totalPages = (int) Math.ceil((double) totalCourses / pageSize);
        if (totalPages < 1) {
            totalPages = 1;
        }

        boolean showTagline = request.getParameter("showTagline") != null;
        boolean showCategory = request.getParameter("showCategory") != null;
        boolean showBasicPrice = request.getParameter("showBasicPrice") != null;
        boolean showAdvancedPrice = request.getParameter("showAdvancedPrice") != null;

        request.setAttribute("showTagline", showTagline);
        request.setAttribute("showCategory", showCategory);
        request.setAttribute("showBasicPrice", showBasicPrice);
        request.setAttribute("showAdvancedPrice", showAdvancedPrice);

        request.setAttribute("courses", courses);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("category", category);
        request.setAttribute("searchTitle", searchTitle);

        request.getRequestDispatcher("/course.jsp").forward(request, response);
    }

}
