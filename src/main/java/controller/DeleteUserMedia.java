package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.User;
import model.UserContact;
import model.UserMedia;
import view.UserContactDAO;
import view.UserDAO;
import view.UserMediaDAO;

@WebServlet(name = "DeleteUserMedia", urlPatterns = {"/DeleteUserMedia"})
public class DeleteUserMedia extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int mediaId = Integer.parseInt(request.getParameter("mediaId"));
        int userId = Integer.parseInt(request.getParameter("userId"));

        UserDAO userDAO = new UserDAO();
        UserMediaDAO userMediaDAO = new UserMediaDAO();

        // Xóa media với id tương ứng
        boolean isDeleted = userMediaDAO.deleteMediaById(mediaId);
        if (isDeleted) {
            request.setAttribute("message", "Xóa media thành công.");
        } else {
            request.setAttribute("message", "Xóa media thất bại.");
        }

        // Lấy thông tin user và các danh sách cần thiết để hiển thị trên JSP
        User user = userDAO.getUserById(userId);
        UserContactDAO userContactDAO = new UserContactDAO();

        List<UserContact> phones = userContactDAO.getUserPhones(userId);
        List<UserContact> emails = userContactDAO.getUserEmails(userId);
        List<UserMedia> images = userMediaDAO.getImagesByUserId(userId);
        List<UserMedia> videos = userMediaDAO.getVideosByUserId(userId);
        UserMedia avatar = userMediaDAO.getAvatarByUserId(userId);

        // Gửi danh sách video, hình ảnh, số điện thoại và email đến JSP
        request.setAttribute("videos", videos);
        request.setAttribute("images", images);
        request.setAttribute("phones", phones);
        request.setAttribute("emails", emails);
        request.setAttribute("user", user);
        request.setAttribute("avatar", avatar);

        request.getRequestDispatcher("/user-details.jsp").forward(request, response);
    }
}
