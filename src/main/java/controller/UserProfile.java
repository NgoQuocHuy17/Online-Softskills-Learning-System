package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.User;
import model.UserContact;
import model.UserMedia;
import view.UserContactDAO;
import view.UserMediaDAO;

@WebServlet(name = "UserProfile", urlPatterns = {"/UserProfile"})
public class UserProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            request.setAttribute("message", "Please log in to view your profile.");
            request.getRequestDispatcher("/Login.jsp").forward(request, response);
            return;
        }

        // Lấy thông tin người dùng từ session
        User user = (User) session.getAttribute("user");
        int userId = user.getId();

        // Lấy thông tin liên lạc của người dùng và avatar
        UserContactDAO userContactDAO = new UserContactDAO();
        UserMediaDAO userMediaDAO = new UserMediaDAO();

        List<UserContact> phones = userContactDAO.getUserPhones(userId);
        List<UserContact> emails = userContactDAO.getUserEmails(userId);
        UserMedia avatar = userMediaDAO.getAvatarByUserId(userId);

        // Gửi danh sách số điện thoại, email và avatar đến JSP
        request.setAttribute("phones", phones);
        request.setAttribute("emails", emails);
        request.setAttribute("avatar", avatar);
        request.setAttribute("user", user);

        request.getRequestDispatcher("/user-profile.jsp").forward(request, response);
    }
}
