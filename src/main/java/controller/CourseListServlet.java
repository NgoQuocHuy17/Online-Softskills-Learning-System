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

@WebServlet("/course")
public class CourseListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CourseDAO courseDAO = new CourseDAO();

        // Fetch all categories
        List<String> categories = courseDAO.getAllCategories();
        request.setAttribute("categories", categories);

        // Get selected category from request
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

        // Fetch courses based on category and pagination
        List<Course> courses = courseDAO.getCoursesByCategory(category, page, pageSize);
        int totalCourses = courseDAO.getTotalCoursesByCategory(category);

        // Log to check what is being retrieved
        System.out.println("Category: " + category);
        System.out.println("Total Courses: " + totalCourses);
        if (courses.isEmpty()) {
            System.out.println("No courses found for category: " + category);
        } else {
            courses.forEach(course -> System.out.println("Course: " + course.getTitle()));
        }

        // Calculate total pages
        int totalPages = (int) Math.ceil((double) totalCourses / pageSize);

        // Ensure totalPages is at least 1
        if (totalPages < 1) {
            totalPages = 1;
        }

        // Set attributes to forward to JSP
        request.setAttribute("courses", courses);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("category", category);

        // Forward to JSP
        request.getRequestDispatcher("/course.jsp").forward(request, response);
    }

}
