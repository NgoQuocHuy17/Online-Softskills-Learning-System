package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import view.UserDAO;
import model.User;
import model.UserContact;
import model.UserVideo;
import view.UserContactDAO;
import view.UserVideoDAO;

@WebServlet(name = "UpdateUserDetails", urlPatterns = {"/user-details-update"})
public class UpdateUserDetails extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int userId = Integer.parseInt(request.getParameter("userId"));
        String newRole = request.getParameter("role");
        String newStatus = request.getParameter("status");

        // Chuyển status thành isValid (1 cho "Active", 0 cho "Inactive")
        int isValid = "Active".equals(newStatus) ? 1 : 0;

        UserDAO userDAO = new UserDAO();
        boolean isUpdated = userDAO.updateUserRoleAndStatus(userId, newRole, isValid);
        if (isUpdated) {
            request.setAttribute("message", "Cập nhật thông tin người dùng thành công.");
        } else {
            request.setAttribute("message", "Cập nhật thông tin người dùng thất bại.");
        }

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
