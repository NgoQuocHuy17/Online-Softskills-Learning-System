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
import view.PackageDAO;
import model.Package;
import view.RegistrationDAO;

@WebServlet(name = "CourseRegister", urlPatterns = {"/CourseRegister"})
public class CourseRegister extends HttpServlet {

    private final CourseDAO courseDAO = new CourseDAO();
    private final PackageDAO packageDAO = new PackageDAO();
    private final RegistrationDAO registrationDAO = new RegistrationDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseIdParam = request.getParameter("courseId");
        String choosingPackageIdParam = request.getParameter("choosingPackageId");

        if (courseIdParam != null) {
            int courseId = Integer.parseInt(courseIdParam);
            Course course = courseDAO.getCourseById(courseId);
            List<Package> packages = packageDAO.getPackagesByCourseId(courseId);

            request.setAttribute("course", course);
            request.setAttribute("packages", packages);

            if (choosingPackageIdParam != null) {
                int choosingPackageId = Integer.parseInt(choosingPackageIdParam);
                double price = packageDAO.getPriceById(choosingPackageId); // Lấy giá tương ứng từ DAO
                request.setAttribute("selectedPackagePrice", price);
                request.setAttribute("choosingPackageId", choosingPackageId);
            }

            request.getRequestDispatcher("course-register.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Course không được tìm thấy");
            request.getRequestDispatcher("/course-register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy các tham số từ yêu cầu
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int packageId = Integer.parseInt(request.getParameter("choosingPackageId"));
        int userId = Integer.parseInt(request.getParameter("userId"));

        Course course = courseDAO.getCourseById(courseId);
        List<Package> packages = packageDAO.getPackagesByCourseId(courseId);

        request.setAttribute("course", course);
        request.setAttribute("packages", packages);

        // Lấy tổng chi phí từ PackageDAO
        double totalCost = packageDAO.getPriceById(packageId);

        // Thêm đăng ký vào cơ sở dữ liệu
        boolean isAdded = registrationDAO.addRegistration(userId, packageId, courseId, totalCost, userId, "Registered by user");

        if (isAdded) {
            // Chuyển hướng đến trang thành công nếu thêm đăng ký thành công
            request.setAttribute("message", "Đăng ký khóa học thành công.");
            request.getRequestDispatcher("course-register.jsp").forward(request, response);
        } else {
            // Chuyển hướng đến trang lỗi nếu thêm đăng ký thất bại
            request.setAttribute("message", "Đăng ký khóa học thất bại, thử lại sau.");
            request.getRequestDispatcher("course-register.jsp").forward(request, response);
        }
    }
}
