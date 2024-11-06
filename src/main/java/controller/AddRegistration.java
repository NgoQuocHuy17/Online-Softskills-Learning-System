package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Course;
import model.User;
import view.CourseDAO;
import view.PackageDAO;
import view.RegistrationDAO;

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
        // Lấy các tham số từ form
        String userIdStr = request.getParameter("userId");
        Integer userId = null;
        if (userIdStr != null && !userIdStr.isEmpty()) {
            int tempUserId = Integer.parseInt(userIdStr);
            if (tempUserId != 0) {
                userId = tempUserId;
            }
        }
        int packageId = Integer.parseInt(request.getParameter("packageId"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        String notes = request.getParameter("notes");

        // Lấy giá của package
        PackageDAO packageDAO = new PackageDAO();
        double totalCost = packageDAO.getPriceById(packageId);

        // Lấy id của user từ session
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            request.setAttribute("message", "Bạn cần đăng nhập để thực hiện thao tác này.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        int createdBy = currentUser.getId();

        // Thêm registration vào cơ sở dữ liệu
        RegistrationDAO registrationDAO = new RegistrationDAO();
        boolean isAdded = registrationDAO.addRegistration(userId, packageId, courseId, totalCost, createdBy, notes);

        // Thiết lập thông báo và chuyển tiếp đến trang kết quả
        if (isAdded) {
            request.setAttribute("message", "Thêm đăng ký thành công.");
        } else {
            request.setAttribute("message", "Thêm đăng ký thất bại.");
        }

        // Chuyển tiếp đến trang kết quả
        request.getRequestDispatcher("/add-registration.jsp").forward(request, response);
    }

}
