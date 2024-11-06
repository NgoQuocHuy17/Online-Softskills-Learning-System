package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.Course;
import model.Registration;
import model.User;
import model.UserContact;
import model.Package;
import model.RegistrationMedia;
import view.CourseDAO;
import view.PackageDAO;
import view.RegistrationDAO;
import view.RegistrationMediaDAO;
import view.UserContactDAO;
import view.UserDAO;

@WebServlet(name = "DeleteRegistrationMedia", urlPatterns = {"/DeleteRegistrationMedia"})
public class DeleteRegistrationMedia extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int mediaId = Integer.parseInt(request.getParameter("mediaId"));
        int registrationId = Integer.parseInt(request.getParameter("registrationId"));
        int userId = Integer.parseInt(request.getParameter("userId"));

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
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserById(userId);
        UserContactDAO userContactDAO = new UserContactDAO();
        CourseDAO courseDAO = new CourseDAO();
        PackageDAO packageDAO = new PackageDAO();

        List<UserContact> phones = userContactDAO.getUserPhones(userId);
        List<UserContact> emails = userContactDAO.getUserEmails(userId);
        List<Course> courses = courseDAO.getAllCourses();
        Course course = courseDAO.getCourseById(registration.getCourseId());
        Package pkg = packageDAO.getPackageById(registration.getPackageId());
        List<Package> packages = packageDAO.getPackagesByCourseId(registration.getCourseId());
        List<RegistrationMedia> images = registrationMediaDAO.getImagesByRegistrationId(registrationId);
        List<RegistrationMedia> videos = registrationMediaDAO.getVideosByRegistrationId(registrationId);

        // Định dạng ngày tháng
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String validFromStr = registration.getValidFrom().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter);
        String validToStr = registration.getValidTo().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter);

        // Gửi danh sách video, hình ảnh, số điện thoại và email đến JSP
        request.setAttribute("registration", registration);
        request.setAttribute("user", user);
        request.setAttribute("emails", emails);
        request.setAttribute("phones", phones);
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
