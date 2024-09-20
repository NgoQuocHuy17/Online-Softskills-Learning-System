/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import view.UserDAO;

@WebServlet(name = "updateProfile", urlPatterns = "/updateProfile")
public class updateProfile extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin người dùng từ session
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("user");

        if (loggedInUser != null) {
            // Lấy dữ liệu từ form
            String fullName = request.getParameter("fullName");
            String gender = request.getParameter("gender");
            String mobile = request.getParameter("mobile");

            // Cập nhật thông tin người dùng
            UserDAO userDAO = new UserDAO();
            loggedInUser.setFullName(fullName);
            loggedInUser.setGender(gender);
            loggedInUser.setMobile(mobile);
            boolean success = userDAO.updateProfile(loggedInUser);

            // Xử lý kết quả cập nhật
            if (success) {
                // Cập nhật thành công, lưu thông tin mới vào session và đặt thông báo thành công
                session.setAttribute("user", loggedInUser);
                request.setAttribute("message", "Profile updated successfully!");
            } else {
                // Cập nhật thất bại, đặt thông báo lỗi
                request.setAttribute("message", "Failed to update profile. Please try again.");
            }

            // Chuyển hướng đến trang hồ sơ
            request.getRequestDispatcher("profile-settings.jsp").forward(request, response);
        } else {
            // Người dùng chưa đăng nhập, chuyển hướng đến trang đăng nhập
            response.sendRedirect("login.jsp");
        }
    }
}
    