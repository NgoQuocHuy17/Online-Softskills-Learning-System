package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.util.List;
import view.UserDAO;
import model.User;
import model.UserContact;
import model.UserMedia;
import view.UserContactDAO;
import view.UserMediaDAO;

@WebServlet(name = "UpdateUserDetails", urlPatterns = {"/UpdateUserDetails"})
@MultipartConfig
public class UpdateUserDetails extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        int userId = Integer.parseInt(request.getParameter("userId"));
        String newRole = request.getParameter("role");
        String newStatus = request.getParameter("status");

        // Kiểm tra newRole và newStatus có null hoặc rỗng không
        if (newRole != null && !newRole.isEmpty() && newStatus != null && !newStatus.isEmpty()) {
            // Chuyển status thành 1 cho "Active", 0 cho "Inactive"
            int status = "Active".equals(newStatus) ? 1 : 0;

            boolean isUpdated = userDAO.updateUserDetails(userId, newRole, status);
            if (isUpdated) {
                request.setAttribute("message", "Cập nhật thông tin người dùng thành công.");
            } else {
                request.setAttribute("message", "Cập nhật thông tin người dùng thất bại.");
            }
        }

        // Xử lý cập nhật hình ảnh
        UserMediaDAO userMediaDAO = new UserMediaDAO();
        Part imagePart = request.getPart("newImage");
        if (imagePart != null && imagePart.getSize() > 0) {
            String imageType = imagePart.getContentType();
            byte[] imageData = new byte[(int) imagePart.getSize()];
            String imageNote = request.getParameter("imageNote");

            // Đọc dữ liệu từ file upload
            try (InputStream inputStream = imagePart.getInputStream()) {
                inputStream.read(imageData);
            }

            // Ghi dữ liệu vào database
            userMediaDAO.insertMedia(userId, imageType, imageData, imageNote);
            request.setAttribute("message", "Cập nhật media của người dùng thành công.");
        }

        // Xử lý cập nhật video
        Part videoPart = request.getPart("newVideo");
        if (videoPart != null && videoPart.getSize() > 0) {
            String videoType = videoPart.getContentType();
            byte[] videoData = new byte[(int) videoPart.getSize()];
            String videoNote = request.getParameter("videoNote");

            // Đọc dữ liệu từ file upload
            try (InputStream inputStream = videoPart.getInputStream()) {
                inputStream.read(videoData);
            }

            // Ghi dữ liệu vào database
            userMediaDAO.insertMedia(userId, videoType, videoData, videoNote);
            request.setAttribute("message", "Cập nhật media của người dùng thành công.");
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
