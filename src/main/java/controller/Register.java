package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Random;
import org.apache.commons.codec.digest.DigestUtils;
import view.UserContactDAO;
import view.UserDAO;

@WebServlet(name = "Register", urlPatterns = "/Register")
public class Register extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserDAO userDAO = new UserDAO();
        UserContactDAO userContactDAO = new UserContactDAO();
        SendingEmail se = new SendingEmail();

        // Lấy các tham số từ form đăng ký
        String fullName = request.getParameter("full_name");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("password_confirmation");
        String phoneNumber = request.getParameter("phone_number");

        // Kiểm tra xem password và password_confirmation có khớp không
        if (!password.equals(passwordConfirmation)) {
            // Nếu mật khẩu không khớp, gửi thông báo lỗi và quay lại trang đăng ký
            request.setAttribute("message", "Confirm Password do not match!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return; // Kết thúc phương thức nếu mật khẩu không khớp
        }

        // Kiểm tra xem email đã tồn tại trong cơ sở dữ liệu chưa
        if (userDAO.checkEmailExist(email)) {
            // Nếu email đã tồn tại, gửi thông báo lỗi và quay lại trang đăng ký
            request.setAttribute("message", "Email existed, try another email!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;  // Kết thúc phương thức nếu email đã tồn tại
        }

        // Tạo mã hash cho người dùng
        String myHash;
        Random random = new Random();
        random.nextInt(999999);
        myHash = DigestUtils.md5Hex("" + random);

        // Tạo user mới và thêm vào database
        boolean isRegistered = userDAO.registerUser(fullName, gender, email, password, myHash);
        if (isRegistered) {
            // Lấy ID của người dùng vừa được thêm vào
            int userId = userDAO.getUserIdByEmail(email);

            // Thêm số điện thoại vào bảng user_contacts với is_preferred là true
            boolean isPhoneContactInserted = userContactDAO.insertContact(userId, "Phone", phoneNumber, true);

            if (!isPhoneContactInserted) {
                // Nếu thêm số điện thoại không thành công, gửi thông báo lỗi và quay lại trang đăng ký
                request.setAttribute("message", "Failed to save contact information. Please try again.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }

            // Chuẩn bị nội dung email kích hoạt tài khoản
            String subject = "Activate Your Account";
            String body = "Welcome, " + fullName + "!\nPlease activate your account by clicking the link below:\n"
                    + "http://localhost:9999/project/ActivateAccount?key1=" + email + "&key2=" + myHash;

            // Gửi email xác thực
            se.sendEmail(email, subject, body);

            // Điều hướng người dùng đến trang xác minh
            response.sendRedirect("register-success.jsp");
        } else {
            // Xử lý nếu có lỗi khi đăng ký
            request.setAttribute("message", "Registration failed. Please try again later!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

}
