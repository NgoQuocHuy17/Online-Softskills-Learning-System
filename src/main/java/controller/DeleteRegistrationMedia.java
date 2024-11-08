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

@WebServlet(name = "DeleteRegistrationMedia", urlPatterns = {"/DeleteRegistrationMedia"})
public class DeleteRegistrationMedia extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int mediaId = Integer.parseInt(request.getParameter("mediaId"));
        int registrationId = Integer.parseInt(request.getParameter("registrationId"));
        String userIdStr = request.getParameter("userId");

        RegistrationDAO registrationDAO = new RegistrationDAO();
        RegistrationMediaDAO registrationMediaDAO = new RegistrationMediaDAO();

        // Xóa media với id tương ứng
        boolean isDeleted = registrationMediaDAO.deleteMediaById(mediaId);
        if (isDeleted) {
            request.setAttribute("message", "Xóa media thành công.");
        } else {
            request.setAttribute("message", "Xóa media thất bại.");
        }

        // Lấy thông tin đăng ký và các danh sách cần thiết để hiển thị trên JSP
        Registration registration = registrationDAO.getRegistrationById(registrationId);
        User user = null;
        List<UserContact> emails = null;
        List<UserContact> phones = null;

        // Kiểm tra userId và lấy thông tin người dùng nếu userId không null
        if (userIdStr != null && !userIdStr.isEmpty()) {
            int userId = Integer.parseInt(userIdStr);
            UserDAO userDAO = new UserDAO();
            user = userDAO.getUserById(userId);

            if (user != null) {
                UserContactDAO userContactDAO = new UserContactDAO();
                emails = userContactDAO.getUserEmails(userId);
                phones = userContactDAO.getUserPhones(userId);
            }
        }

        CourseDAO courseDAO = new CourseDAO();
        PackageDAO packageDAO = new PackageDAO();

        List<Course> courses = courseDAO.getAllCourses();
        Course course = courseDAO.getCourseById(registration.getCourseId());
        Package pkg = packageDAO.getPackageById(registration.getPackageId());
        List<Package> packages = packageDAO.getPackagesByCourseId(registration.getCourseId());
        List<RegistrationMedia> images = registrationMediaDAO.getImagesByRegistrationId(registrationId);
        List<RegistrationMedia> videos = registrationMediaDAO.getVideosByRegistrationId(registrationId);

        // Định dạng ngày tháng
        LocalDate validFrom = registration.getValidFrom().toLocalDate();
        LocalDate validTo = registration.getValidTo().toLocalDate();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String validFromStr = validFrom.format(formatter); // Định dạng thời gian validFrom
        String validToStr = validTo.format(formatter); // Định dạng thời gian validTo

        // Gửi danh sách video, hình ảnh, số điện thoại và email đến JSP
        request.setAttribute("registration", registration);
        request.setAttribute("user", user);
        if (emails != null) {
            request.setAttribute("emails", emails);
        }
        if (phones != null) {
            request.setAttribute("phones", phones);
        }
        request.setAttribute("course", course);
        request.setAttribute("courses", courses);
        request.setAttribute("pkg", pkg);
        request.setAttribute("packages", packages);
        request.setAttribute("validFrom", validFromStr);
        request.setAttribute("validTo", validToStr);
        request.setAttribute("images", images);
        request.setAttribute("videos", videos);

        request.getRequestDispatcher("/registration-details.jsp").forward(request, response);
    }
}
