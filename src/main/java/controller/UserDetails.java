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
import model.UserMedia;
import view.UserContactDAO;
import view.UserMediaDAO;

@WebServlet(name = "UserDetails", urlPatterns = {"/UserDetails"})
public class UserDetails extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy tham số userId từ request, xác định người dùng mà chúng ta muốn xem chi tiết
        String userIdParam = request.getParameter("userId");

        // Kiểm tra xem tham số userId có hợp lệ không
        if (userIdParam == null || userIdParam.isEmpty()) {
            // Nếu userId bị thiếu, đặt thông báo lỗi và chuyển hướng về trang danh sách người dùng
            request.setAttribute("message", "User ID is missing.");
            request.getRequestDispatcher("/user-list.jsp").forward(request, response);
            return;
        }

        int userId;
        try {
            // Chuyển đổi userId từ String sang int
            userId = Integer.parseInt(userIdParam);
        } catch (NumberFormatException e) {
            // Nếu userId không hợp lệ (không phải số), đặt thông báo lỗi và chuyển hướng về trang danh sách người dùng
            request.setAttribute("message", "Invalid User ID.");
            request.getRequestDispatcher("/user-list.jsp").forward(request, response);
            return;
        }

        // Khởi tạo các đối tượng DAO để lấy dữ liệu từ cơ sở dữ liệu
        UserDAO userDAO = new UserDAO();
        UserContactDAO userContactDAO = new UserContactDAO();
        UserMediaDAO userMediaDAO = new UserMediaDAO();

        // Lấy thông tin người dùng từ cơ sở dữ liệu theo userId
        User user = userDAO.getUserById(userId);

        // Lấy danh sách số điện thoại của người dùng từ bảng liên hệ
        List<UserContact> phones = userContactDAO.getUserPhones(userId);

        // Lấy danh sách email của người dùng từ bảng liên hệ
        List<UserContact> emails = userContactDAO.getUserEmails(userId);

        // Lấy danh sách video của người dùng từ bảng phương tiện đa phương tiện
        List<UserMedia> videos = userMediaDAO.getVideosByUserId(userId);

        // Lấy danh sách hình ảnh của người dùng từ bảng phương tiện đa phương tiện
        List<UserMedia> images = userMediaDAO.getImagesByUserId(userId);

        // Lấy avatar (ảnh đại diện) của người dùng từ bảng phương tiện đa phương tiện
        UserMedia avatar = userMediaDAO.getAvatarByUserId(userId);

        // Đặt các thuộc tính vào request để chuyển tiếp đến trang JSP hiển thị chi tiết người dùng
        request.setAttribute("videos", videos);     // Danh sách video
        request.setAttribute("images", images);     // Danh sách hình ảnh
        request.setAttribute("phones", phones);     // Danh sách số điện thoại
        request.setAttribute("emails", emails);     // Danh sách email
        request.setAttribute("user", user);         // Thông tin người dùng chính
        request.setAttribute("avatar", avatar);     // Avatar của người dùng

        // Chuyển tiếp request và response đến trang user-details.jsp để hiển thị thông tin người dùng
        request.getRequestDispatcher("/user-details.jsp").forward(request, response);
    }
}
