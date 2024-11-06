package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Course;
import view.CourseDAO;

@WebServlet(name = "AddRegistration", urlPatterns = {"/AddRegistration"})
public class AddRegistration extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CourseDAO courseDAO = new CourseDAO();

        // Lấy danh sách tất cả các khóa học từ cơ sở dữ liệu
        List<Course> courses = courseDAO.getAllCourses();

        // Gửi danh sách các khóa học tới trang add-registration.jsp
        request.setAttribute("courses", courses);

        // Chuyển tiếp tới trang add-registration.jsp
        request.getRequestDispatcher("/add-registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Xử lý logic thêm đăng ký ở đây
    }
}
