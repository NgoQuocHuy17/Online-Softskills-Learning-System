package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.SecureRandom;
import java.util.Random;
import model.User;
import view.UserDAO;

@WebServlet(name = "AddUser", urlPatterns = {"/AddUser"})
public class AddUser extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();
    private final SendingEmail sendingEmail = new SendingEmail();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String gender = request.getParameter("gender");
        String role = request.getParameter("role");
        String status = request.getParameter("status");
        String email = request.getParameter("email");
        int isValid = status.equals("Active") ? 1 : 0;

        // Kiểm tra xem email đã tồn tại chưa
        try {
            if (userDAO.isEmailExist(email)) {
                request.setAttribute("alertMessage", "Email already exists. Please use a different email.");
                request.getRequestDispatcher("add-user.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("alertMessage", "An error occurred while checking email.");
            request.getRequestDispatcher("add-user.jsp").forward(request, response);
            return;
        }

        // Tạo mật khẩu ngẫu nhiên
        String password = generateRandomPassword(10);

        // Tạo user mới
        User newUser = new User();
        newUser.setFullName(fullName);
        newUser.setGender(gender);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setRole(role);
        newUser.setStatus(isValid);

        // Thêm user vào cơ sở dữ liệu
        boolean isAdded = userDAO.addUser(newUser);

        // Nếu thêm thành công, gửi email cho user với mật khẩu mới
        if (isAdded) {
            String subject = "Your Account Has Been Created";
            String body = "Hello " + fullName + ",\n"
                    + "\nYour account has been created successfully. Your login credentials are:\n"
                    + "Email: " + email + "\nPassword: " + password + "\n"
                    + "\nPlease login and change your password.";
            sendingEmail.sendEmail(email, subject, body);

            request.setAttribute("successMessage", "User added successfully and email sent.");
        } else {
            request.setAttribute("alertMessage", "Failed to add user.");
        }

        request.getRequestDispatcher("add-user.jsp").forward(request, response);
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