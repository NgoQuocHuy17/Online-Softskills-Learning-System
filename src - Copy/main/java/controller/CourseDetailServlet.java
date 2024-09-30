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
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Course not found.");
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid course ID.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing course ID.");
        }
    }
}
