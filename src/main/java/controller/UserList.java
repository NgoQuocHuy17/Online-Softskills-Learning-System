/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import view.UserDAO;
import model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserList", urlPatterns = {"/UserList"})
public class UserList extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        
        // Lấy số trang từ request (mặc định là 1 nếu không có tham số)
        int page = 1;
        int pageSize = 5; // Số lượng users mỗi trang
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        // Lấy danh sách users theo trang
        List<User> userList = userDAO.getUsersByPage(page, pageSize);

        // Tính tổng số users và số trang
        int totalUsers = userDAO.getTotalUsers();
        int totalPages = (int) Math.ceil(totalUsers / (double) pageSize);

        // Gửi danh sách users và các thông tin phân trang đến trang JSP
        request.setAttribute("userList", userList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        // Forward đến trang JSP
        request.getRequestDispatcher("/UserList.jsp").forward(request, response);
    }
}
