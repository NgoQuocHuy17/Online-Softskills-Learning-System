package controller;

import model.User;
import view.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.UserContact;
import model.UserVideo;
import view.UserContactDAO;
import view.UserVideoDAO;

@WebServlet(name = "UserDetails", urlPatterns = {"/UserDetails"})
public class UserDetails extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy userId từ tham số request
        String userIdParam = request.getParameter("userId");
        if (userIdParam == null || userIdParam.isEmpty()) {
            request.setAttribute("message", "User ID is missing.");
            request.getRequestDispatcher("/UserList.jsp").forward(request, response);
            return;
        }

        int userId;
        try {
            userId = Integer.parseInt(userIdParam);
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid User ID.");
            request.getRequestDispatcher("/UserList.jsp").forward(request, response);
            return;
        }

        // Tạo đối tượng UserDAO và lấy thông tin người dùng
        UserDAO userDAO = new UserDAO();
        UserContactDAO userContactDAO = new UserContactDAO();
        UserVideoDAO userVideoDAO = new UserVideoDAO();

        User user = userDAO.getUserById(userId);
        List<UserContact> phones = userContactDAO.getUserPhones(userId);
        List<UserContact> emails = userContactDAO.getUserEmails(userId);
        List<UserVideo> videos = userVideoDAO.getUserVideo(userId);

        // Gửi danh sách video, số điện thoại và email đến JSP
        request.setAttribute("videos", videos);
        request.setAttribute("phones", phones);
        request.setAttribute("emails", emails);
        request.setAttribute("user", user);

        request.getRequestDispatcher("/UserDetails.jsp").forward(request, response);
    }
}
