package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
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
        // Khởi tạo đối tượng UserDAO để cập nhật chi tiết người dùng
        UserDAO userDAO = new UserDAO();

        // Lấy các tham số từ request
        int userId = Integer.parseInt(request.getParameter("userId"));
        String newRole = request.getParameter("role");
        String newStatus = request.getParameter("status");

        // Kiểm tra xem các giá trị của role và status có hợp lệ không
        if (newRole != null && !newRole.isEmpty() && newStatus != null && !newStatus.isEmpty()) {
            // Chuyển đổi giá trị status thành 1 nếu là "Active", hoặc 0 nếu là "Inactive"
            int status = "Active".equals(newStatus) ? 1 : 0;

            // Thực hiện cập nhật role và status trong cơ sở dữ liệu
            boolean isUpdated = userDAO.updateUserDetails(userId, newRole, status);
            if (isUpdated) {
                request.setAttribute("message", "Cập nhật thông tin người dùng thành công.");
            } else {
                request.setAttribute("message", "Cập nhật thông tin người dùng thất bại.");
            }
        }

        // Khởi tạo đối tượng UserMediaDAO để xử lý media của người dùng
        UserMediaDAO userMediaDAO = new UserMediaDAO();

        // Xử lý cập nhật hình ảnh
        Part imagePart = request.getPart("newImage");
        if (imagePart != null && imagePart.getSize() > 0) {
            String imageType = imagePart.getContentType();
            byte[] imageData = new byte[(int) imagePart.getSize()];
            String imageNote = request.getParameter("imageNote");

            // Đọc dữ liệu hình ảnh từ file tải lên
            try (InputStream inputStream = imagePart.getInputStream()) {
                inputStream.read(imageData);
            }

            // Chèn dữ liệu hình ảnh vào cơ sở dữ liệu
            userMediaDAO.insertMedia(userId, imageType, imageData, imageNote);
            request.setAttribute("message", "Cập nhật media của người dùng thành công.");
        }

        // Xử lý cập nhật video
        Part videoPart = request.getPart("newVideo");
        if (videoPart != null && videoPart.getSize() > 0) {
            String videoType = videoPart.getContentType();
            byte[] videoData = new byte[(int) videoPart.getSize()];
            String videoNote = request.getParameter("videoNote");

            // Đọc dữ liệu video từ file tải lên
            try (InputStream inputStream = videoPart.getInputStream()) {
                inputStream.read(videoData);
            }

            // Chèn dữ liệu video vào cơ sở dữ liệu
            userMediaDAO.insertMedia(userId, videoType, videoData, videoNote);
            request.setAttribute("message", "Cập nhật media của người dùng thành công.");
        }

        // Lấy thông tin chi tiết người dùng và các phương tiện khác để hiển thị trên JSP
        User user = userDAO.getUserById(userId);
        UserContactDAO userContactDAO = new UserContactDAO();

        List<UserContact> phones = userContactDAO.getUserPhones(userId);   // Danh sách số điện thoại
        List<UserContact> emails = userContactDAO.getUserEmails(userId);   // Danh sách email
        List<UserMedia> images = userMediaDAO.getImagesByUserId(userId);   // Danh sách hình ảnh
        List<UserMedia> videos = userMediaDAO.getVideosByUserId(userId);   // Danh sách video
        UserMedia avatar = userMediaDAO.getAvatarByUserId(userId);         // Avatar người dùng

        // Đặt các thuộc tính vào request để chuyển tiếp đến trang JSP
        request.setAttribute("videos", videos);
        request.setAttribute("images", images);
        request.setAttribute("phones", phones);
        request.setAttribute("emails", emails);
        request.setAttribute("user", user);
        request.setAttribute("avatar", avatar);

        // Chuyển tiếp request và response đến trang user-details.jsp để hiển thị thông tin
        request.getRequestDispatcher("/user-details.jsp").forward(request, response);
    }
}
