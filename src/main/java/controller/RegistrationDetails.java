package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.*;
import model.Package;
import view.*;

@WebServlet(name = "RegistrationDetails", urlPatterns = {"/RegistrationDetails"})
public class RegistrationDetails extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin registrationId từ tham số request
        int registrationId = Integer.parseInt(request.getParameter("registrationId"));

        // Tạo đối tượng RegistrationDAO và lấy thông tin đăng ký từ cơ sở dữ liệu theo registrationId
        RegistrationDAO registrationDAO = new RegistrationDAO();
        Registration registration = registrationDAO.getRegistrationById(registrationId);

        // Chuyển đổi java.sql.Date thành java.time.LocalDate
        LocalDate validFrom = registration.getValidFrom().toLocalDate();
        LocalDate validTo = registration.getValidTo().toLocalDate();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Định dạng ngày tháng theo mẫu yyyy-MM-dd
        String validFromStr = validFrom.format(formatter); // Định dạng thời gian validFrom
        String validToStr = validTo.format(formatter); // Định dạng thời gian validTo

        // Khởi tạo các đối tượng cần thiết cho việc lấy thông tin chi tiết người dùng và liên hệ
        User userParam = null;
        List<UserContact> emails = null;
        List<UserContact> phones = null;

        // Lấy thông tin userId từ đối tượng registration
        Integer userId = registration.getUserId();
        if (userId != null && userId != 0) {
            // Lấy thông tin người dùng từ UserDAO
            UserDAO userDAO = new UserDAO();
            userParam = userDAO.getUserById(userId);

            if (userParam != null) {
                // Lấy email và số điện thoại của người dùng từ UserContactDAO nếu người dùng tồn tại
                UserContactDAO userContactDAO = new UserContactDAO();
                emails = userContactDAO.getUserEmails(userParam.getId()); // Lấy danh sách email của người dùng
                phones = userContactDAO.getUserPhones(userParam.getId()); // Lấy danh sách số điện thoại của người dùng
            }
        }

        // Lấy thông tin khóa học từ CourseDAO
        CourseDAO courseDAO = new CourseDAO();
        Course course = courseDAO.getCourseById(registration.getCourseId());

        // Lấy tất cả các khóa học để hiển thị danh sách khóa học
        List<Course> courses = courseDAO.getAllCourses();

        // Lấy thông tin gói học từ PackageDAO
        PackageDAO packageDAO = new PackageDAO();
        Package pkg = packageDAO.getPackageById(registration.getPackageId());

        // Lấy danh sách các gói học hiện có của khóa học theo courseId
        List<Package> packages = packageDAO.getPackagesByCourseId(registration.getCourseId());

        // Lấy thông tin media liên quan đến đăng ký từ RegistrationMediaDAO (hình ảnh và video)
        RegistrationMediaDAO registrationMediaDAO = new RegistrationMediaDAO();
        List<RegistrationMedia> images = registrationMediaDAO.getImagesByRegistrationId(registrationId);
        List<RegistrationMedia> videos = registrationMediaDAO.getVideosByRegistrationId(registrationId);

        // Đặt các thông tin vừa lấy được vào request để chuyển tiếp sang JSP
        request.setAttribute("registration", registration); // Đặt thông tin đăng ký vào request
        request.setAttribute("userParam", userParam); // Đặt thông tin người dùng vào request
        if (emails != null) {
            request.setAttribute("emails", emails); // Đặt thông tin email vào request nếu có
        }
        if (phones != null) {
            request.setAttribute("phones", phones); // Đặt thông tin điện thoại vào request nếu có
        }
        request.setAttribute("course", course); // Đặt thông tin khóa học vào request
        request.setAttribute("courses", courses); // Đặt danh sách tất cả khóa học vào request
        request.setAttribute("pkg", pkg); // Đặt thông tin gói học vào request
        request.setAttribute("packages", packages); // Đặt danh sách gói học vào request
        request.setAttribute("validFrom", validFromStr); // Đặt thông tin thời gian bắt đầu (validFrom)
        request.setAttribute("validTo", validToStr); // Đặt thông tin thời gian kết thúc (validTo)
        request.setAttribute("images", images); // Đặt thông tin hình ảnh liên quan đến đăng ký vào request
        request.setAttribute("videos", videos); // Đặt thông tin video liên quan đến đăng ký vào request

        // Chuyển tiếp đến JSP (registration-details.jsp) để hiển thị tất cả các thông tin trên giao diện người dùng
        request.getRequestDispatcher("registration-details.jsp").forward(request, response);
    }
}
