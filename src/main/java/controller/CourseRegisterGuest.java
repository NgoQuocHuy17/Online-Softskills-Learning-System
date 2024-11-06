package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.digest.DigestUtils;
import view.PackageDAO;
import view.RegistrationDAO;
import view.UserContactDAO;
import view.UserDAO;

@WebServlet(name = "CourseRegisterGuest", urlPatterns = {"/CourseRegisterGuest"})
public class CourseRegisterGuest extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy các param fullname, gender, login email
        String fullName = request.getParameter("fullName");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int packageId = Integer.parseInt(request.getParameter("choosingPackageId"));

        // Kiểm tra email tồn tại hay chưa
        UserDAO userDAO = new UserDAO();
        try {
            if (userDAO.isEmailExist(email)) {
                request.setAttribute("message", "Email đã tồn tại");
                request.getRequestDispatcher("course-register.jsp").forward(request, response);
                return;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseRegisterGuest.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Tạo mật khẩu ngẫu nhiên
        String password = generateRandomPassword(10);

        // Tạo mã hash từ password
        String myHash;
        Random random = new Random();
        random.nextInt(999999);
        myHash = DigestUtils.md5Hex("" + random);

        // Đăng ký người dùng mới
        boolean isRegistered = userDAO.registerUser(fullName, gender, email, password, myHash);
        if (!isRegistered) {
            request.setAttribute("message", "Đăng ký người dùng thất bại, thử lại sau");
            request.getRequestDispatcher("course-register.jsp").forward(request, response);
            return;
        }

        // Lấy id của người dùng vừa đăng ký
        int userId = userDAO.getUserIdByEmail(email);

        // Thêm các địa chỉ email liên lạc và số điện thoại
        UserContactDAO userContactDAO = new UserContactDAO();
        String[] emails = request.getParameterValues("emails[]");
        String[] phones = request.getParameterValues("phones[]");
        String preferredContact = request.getParameter("preferredContact");

        for (int i = 0; i < emails.length; i++) {
            boolean isPreferred = ("email" + i).equals(preferredContact);
            userContactDAO.insertContact(userId, "Email", emails[i], isPreferred);
        }

        for (int i = 0; i < phones.length; i++) {
            boolean isPreferred = ("phone" + i).equals(preferredContact);
            userContactDAO.insertContact(userId, "Phone", phones[i], isPreferred);
        }

        // Lấy tổng chi phí từ PackageDAO
        PackageDAO packageDAO = new PackageDAO();
        double totalCost = packageDAO.getPriceById(packageId);

        // Thêm đăng ký vào cơ sở dữ liệu
        RegistrationDAO registrationDAO = new RegistrationDAO();
        boolean isAdded = registrationDAO.addRegistration(userId, packageId, courseId, totalCost, userId, "Registered by user");

        if (!isAdded) {
            request.setAttribute("message", "Đăng ký khóa học thất bại, thử lại sau");
            request.getRequestDispatcher("course-register.jsp").forward(request, response);
            return;
        }

        // Gửi email kích hoạt tài khoản cùng mật khẩu đăng nhập
        String subject = "Kích hoạt tài khoản của bạn";
        String body = "Cảm ơn bạn đã đăng ký tài khoản và đăng ký khóa học thành công.\n"
                + "Mật khẩu đăng nhập của bạn là: " + password + "\n"
                + "Để kích hoạt tài khoản, hãy bấm vào link bên dưới:\n"
                + "http://localhost:9999/project/ActivateAccount?key1=" + email + "&key2=" + myHash;
        SendingEmail se = new SendingEmail();
        se.sendEmail(email, subject, body);

        // Chuyển hướng đến trang thành công
        request.setAttribute("message", "Đăng ký thành công. Vui lòng kiểm tra email để kích hoạt tài khoản.");
        request.getRequestDispatcher("course-register-success.jsp").forward(request, response);
    }

    private String generateRandomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
