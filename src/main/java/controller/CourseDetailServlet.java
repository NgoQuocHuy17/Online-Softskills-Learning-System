package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CourseDetail;
import view.CourseDetailDAO;

import java.io.IOException;

@WebServlet(name = "CourseDetailServlet", urlPatterns = {"/course-detail"})
public class CourseDetailServlet extends HttpServlet {

    private CourseDetailDAO courseDetailDAO;

    @Override
    public void init() throws ServletException {
        courseDetailDAO = new CourseDetailDAO(); // Khởi tạo DAO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseIdParam = request.getParameter("courseId");

        if (courseIdParam != null) {
            try {
                int courseId = Integer.parseInt(courseIdParam);
                CourseDetail courseDetail = courseDetailDAO.select(courseId); // Lấy thông tin chi tiết khóa học

                if (courseDetail != null) {
                    request.setAttribute("courseDetail", courseDetail); // Đặt thuộc tính cho JSP
                    request.getRequestDispatcher("course-detail.jsp").forward(request, response);
                } else {
                    // Thêm thông báo lỗi nếu không tìm thấy khóa học
                    request.setAttribute("errorMessage", "Course not found.");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            } catch (NumberFormatException e) {
                // Xử lý khi courseId không hợp lệ
                request.setAttribute("errorMessage", "Invalid course ID.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else {
            // Xử lý khi không có courseId trong request
            request.setAttribute("errorMessage", "Missing course ID.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
