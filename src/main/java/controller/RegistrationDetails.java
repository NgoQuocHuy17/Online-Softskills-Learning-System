package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.Course;
import model.Registration;
import model.RegistrationMedia;
import model.User;
import model.Package;
import model.UserContact;
import view.CourseDAO;
import view.PackageDAO;
import view.RegistrationDAO;
import view.RegistrationMediaDAO;
import view.UserContactDAO;
import view.UserDAO;

@WebServlet(name = "RegistrationDetails", urlPatterns = {"/RegistrationDetails"})
public class RegistrationDetails extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin registrationId từ request
        int registrationId = Integer.parseInt(request.getParameter("registrationId"));

        // Lấy thông tin registration từ RegistrationDAO
        RegistrationDAO registrationDAO = new RegistrationDAO();
        Registration registration = registrationDAO.getRegistrationById(registrationId);

        // Format định dạng thời gian của valid from và valid to
        LocalDate validFrom = registration.getValidFrom().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate validTo = registration.getValidTo().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String validFromStr = validFrom.format(formatter);
        String validToStr = validTo.format(formatter);

        // Thiết lập attribute cho user
        User userParam = null;
        List<UserContact> emails = null;
        List<UserContact> phones = null;

        Integer userId = registration.getUserId();
        if (userId != null && userId != 0) {
            // Lấy thông tin người dùng từ UserDAO
            UserDAO userDAO = new UserDAO();
            userParam = userDAO.getUserById(userId);

            if (userParam != null) {
                // Lấy email và số điện thoại của người dùng từ UserContactDAO
                UserContactDAO userContactDAO = new UserContactDAO();
                emails = userContactDAO.getUserEmails(userParam.getId());
                phones = userContactDAO.getUserPhones(userParam.getId());
            }
        }

        // Lấy thông tin khóa học từ CourseDAO
        CourseDAO courseDAO = new CourseDAO();
        Course course = courseDAO.getCourseById(registration.getCourseId());

        // Lấy tất cả các khóa học từ CourseDAO
        List<Course> courses = courseDAO.getAllCourses();

        // Lấy thông tin gói từ PackageDAO
        PackageDAO packageDAO = new PackageDAO();
        Package pkg = packageDAO.getPackageById(registration.getPackageId());

        // Lấy danh sách các gói hiện tại của khóa học
        List<Package> packages = packageDAO.getPackagesByCourseId(registration.getCourseId());

        // Lấy thông tin media từ RegistrationMediaDAO
        RegistrationMediaDAO registrationMediaDAO = new RegistrationMediaDAO();
        List<RegistrationMedia> images = registrationMediaDAO.getImagesByRegistrationId(registrationId);
        List<RegistrationMedia> videos = registrationMediaDAO.getVideosByRegistrationId(registrationId);

        // Thiết lập các attribute để gửi tới JSP
        request.setAttribute("registration", registration);
        request.setAttribute("userParam", userParam);
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

        // Chuyển tiếp tới JSP để hiển thị thông tin
        request.getRequestDispatcher("registration-details.jsp").forward(request, response);
    }

}
