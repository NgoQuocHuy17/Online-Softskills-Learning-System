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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CourseDAO courseDAO = new CourseDAO();

        // Fetch all categories
        List<String> categories = courseDAO.getAllCategories();
        request.setAttribute("categories", categories);

        // Get searchTitle and category from request
        String searchTitle = request.getParameter("searchTitle");
        String category = request.getParameter("category");

        if (category == null || category.isEmpty()) {
            category = "All"; // Default category is 'All'
        }

        // Handle pagination
        int page = 1;
        int pageSize = 5;
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                page = 1; // Default to page 1 if invalid page number
            }
        }

        List<Course> courses;
        int totalCourses;

        // Nếu cả searchTitle và category đều tồn tại
        if ((searchTitle != null && !searchTitle.isEmpty()) && !"All".equals(category)) {
            // Lọc theo tiêu đề và category
            courses = courseDAO.getCoursesByTitleAndCategory(searchTitle, category, page, pageSize);
            totalCourses = courseDAO.getTotalCoursesByTitleAndCategory(searchTitle, category);
        } else if (searchTitle != null && !searchTitle.isEmpty()) {
            // Nếu chỉ tìm kiếm theo tiêu đề
            courses = courseDAO.getCoursesByTitle(searchTitle);
            totalCourses = courses.size(); // Kết quả tìm kiếm không cần phân trang
        } else {
            // Nếu chỉ lọc theo category
            courses = courseDAO.getCoursesByCategory(category, page, pageSize);
            totalCourses = courseDAO.getTotalCoursesByCategory(category);
        }

        // Tính toán số trang
        int totalPages = (int) Math.ceil((double) totalCourses / pageSize);
        if (totalPages < 1) {
            totalPages = 1;
        }

        // Set attributes
        request.setAttribute("courses", courses);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("category", category);
        request.setAttribute("searchTitle", searchTitle);

        // Forward to JSP
        request.getRequestDispatcher("/course.jsp").forward(request, response);
    }
}
