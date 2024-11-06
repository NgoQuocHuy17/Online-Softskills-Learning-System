/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.UserDAO;

@WebServlet(name = "ActivateAccount", urlPatterns = "/ActivateAccount")
public class ActivateAccount extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();

        // Lấy các tham số từ URL
        String email = request.getParameter("key1");
        String hash = request.getParameter("key2");

        // Kích hoạt tài khoản cho người dùng
        boolean isActivated = userDAO.activateUser(email, hash);

        if (isActivated) {
            // Nếu kích hoạt thành công, chuyển đến trang thành công
            response.sendRedirect("activate-success.jsp");
        } else {
            // Nếu kích hoạt không thành công, có thể xử lý thêm
            request.setAttribute("message", "Account activation failed. Please try again.");
            request.getRequestDispatcher("activate-fail.jsp").forward(request, response);
        }
    }
}
