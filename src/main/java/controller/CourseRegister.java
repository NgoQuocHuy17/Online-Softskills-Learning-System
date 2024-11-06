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

    // Khởi tạo các đối tượng DAO để thao tác với cơ sở dữ liệu
    private final CourseDAO courseDAO = new CourseDAO();
    private final PackageDAO packageDAO = new PackageDAO();
    private final RegistrationDAO registrationDAO = new RegistrationDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy các tham số courseId và choosingPackageId từ yêu cầu
        String courseIdParam = request.getParameter("courseId");
        String choosingPackageIdParam = request.getParameter("choosingPackageId");

        // Kiểm tra nếu courseId được truyền từ yêu cầu
        if (courseIdParam != null) {
            int courseId = Integer.parseInt(courseIdParam); // Chuyển đổi courseId thành số nguyên
            Course course = courseDAO.getCourseById(courseId); // Lấy thông tin khóa học từ courseId
            List<Package> packages = packageDAO.getPackagesByCourseId(courseId); // Lấy danh sách gói học theo courseId

            // Đặt các thông tin của khóa học và các gói học vào yêu cầu để chuyển tiếp sang trang JSP
            request.setAttribute("course", course);
            request.setAttribute("packages", packages);

            // Nếu choosingPackageId được truyền, lấy giá của gói học tương ứng
            if (choosingPackageIdParam != null) {
                int choosingPackageId = Integer.parseInt(choosingPackageIdParam); // Chuyển đổi choosingPackageId thành số nguyên
                double price = packageDAO.getPriceById(choosingPackageId); // Lấy giá từ PackageDAO
                request.setAttribute("selectedPackagePrice", price); // Đặt giá vào yêu cầu
                request.setAttribute("choosingPackageId", choosingPackageId); // Đặt ID gói học đã chọn vào yêu cầu
            }

            // Chuyển tiếp yêu cầu đến trang JSP hiển thị chi tiết đăng ký khóa học
            request.getRequestDispatcher("course-register.jsp").forward(request, response);
        } else {
            // Nếu courseId không tồn tại, hiển thị thông báo lỗi
            request.setAttribute("message", "Course không được tìm thấy");
            request.getRequestDispatcher("/course-register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy các tham số courseId, choosingPackageId, và userId từ yêu cầu
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int packageId = Integer.parseInt(request.getParameter("choosingPackageId"));
        int userId = Integer.parseInt(request.getParameter("userId"));

        // Lấy thông tin khóa học và danh sách gói học
        Course course = courseDAO.getCourseById(courseId);
        List<Package> packages = packageDAO.getPackagesByCourseId(courseId);

        // Đặt thông tin khóa học và danh sách gói học vào yêu cầu để chuyển tiếp sau
        request.setAttribute("course", course);
        request.setAttribute("packages", packages);

        // Lấy tổng chi phí của gói học từ PackageDAO dựa trên packageId
        double totalCost = packageDAO.getPriceById(packageId);

        // Thêm đăng ký vào cơ sở dữ liệu thông qua RegistrationDAO
        boolean isAdded = registrationDAO.addRegistration(userId, packageId, courseId, totalCost, userId, "Registered by user");

        if (isAdded) {
            // Nếu đăng ký thành công, đặt thông báo thành công vào yêu cầu và chuyển tiếp đến JSP
            request.setAttribute("message", "Đăng ký khóa học thành công.");
            request.getRequestDispatcher("course-register.jsp").forward(request, response);
        } else {
            // Nếu đăng ký thất bại, đặt thông báo lỗi vào yêu cầu và chuyển tiếp đến JSP
            request.setAttribute("message", "Đăng ký khóa học thất bại, thử lại sau.");
            request.getRequestDispatcher("course-register.jsp").forward(request, response);
        }
    }
}
