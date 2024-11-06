package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

        // Lấy thông tin người dùng từ UserDAO
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserById(registration.getUserId());

        // Lấy email và số điện thoại của người dùng từ UserContactDAO
        UserContactDAO userContactDAO = new UserContactDAO();
        List<UserContact> emails = userContactDAO.getUserEmails(user.getId());
        List<UserContact> phones = userContactDAO.getUserPhones(user.getId());

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
        request.setAttribute("user", user);
        request.setAttribute("emails", emails);
        request.setAttribute("phones", phones);
        request.setAttribute("course", course);
        request.setAttribute("courses", courses); // Thêm attribute "courses"
        request.setAttribute("pkg", pkg);
        request.setAttribute("packages", packages); // Thêm attribute "packages" của khóa học hiện tại
        request.setAttribute("images", images);
        request.setAttribute("videos", videos);

        // Chuyển tiếp tới JSP để hiển thị thông tin
        request.getRequestDispatcher("registration-details.jsp").forward(request, response);
    }

}
