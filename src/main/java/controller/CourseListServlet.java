package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Course;
import view.CourseDAO;

@WebServlet("/course")
public class CourseListServlet extends HttpServlet {

    private static final String[] DISPLAY_ATTRIBUTES = {
        "displayTagline", "displayCategory", "displayBasicPrice", "displayAdvancedPrice", "displayUpdateAt"
    };

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Khởi tạo một HashMap để lưu trạng thái hiển thị
        Map<String, Boolean> displaySettings = new HashMap<>();

        // Đọc trạng thái từ cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().startsWith("display")) {
                    displaySettings.put(cookie.getName(), Boolean.parseBoolean(cookie.getValue()));
                }
            }
        }

        // Kiểm tra nếu người dùng gửi dữ liệu từ form
        for (String attribute : DISPLAY_ATTRIBUTES) {
            String value = request.getParameter(attribute);
            boolean isDisplayed = value != null; // Nếu không có giá trị (null) => false
            displaySettings.put(attribute, isDisplayed);

            // Cập nhật cookie
            Cookie cookie = new Cookie(attribute, String.valueOf(isDisplayed));
            cookie.setMaxAge(60 * 60 * 24 * 30); // Lưu cookie 30 ngày
            response.addCookie(cookie);
        }

        CourseDAO courseDAO = new CourseDAO();

        // Set thuộc tính để truyền sang JSP
        request.setAttribute("displaySettings", displaySettings);

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
            courses = courseDAO.getCoursesByTitleAndCategory(searchTitle, category, page, pageSize);
            totalCourses = courseDAO.getTotalCoursesByTitleAndCategory(searchTitle, category);
        } else if (searchTitle != null && !searchTitle.isEmpty()) {
            courses = courseDAO.getCoursesByTitle(searchTitle);
            totalCourses = courses.size(); // Kết quả tìm kiếm không cần phân trang
        } else {
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
        request.setAttribute("displaySettings", displaySettings);
        request.setAttribute("displayAttributes", DISPLAY_ATTRIBUTES);

        // Forward về JSP
        request.getRequestDispatcher("/course.jsp").forward(request, response);
    }
}
