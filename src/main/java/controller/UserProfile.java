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
        // Kiểm tra xem người dùng đã đăng nhập chưa thông qua session
        HttpSession session = request.getSession(false); // Lấy session hiện tại, nếu không có sẽ trả về null
        if (session == null || session.getAttribute("user") == null) {
            // Nếu session không tồn tại hoặc người dùng chưa đăng nhập, chuyển hướng tới trang login
            request.setAttribute("message", "Please log in to view your profile."); // Thiết lập thông báo
            request.getRequestDispatcher("/Login.jsp").forward(request, response); // Chuyển hướng tới trang đăng nhập
            return; // Dừng lại ở đây nếu chưa đăng nhập
        }

        // Lấy thông tin người dùng từ session
        User user = (User) session.getAttribute("user"); // Lấy đối tượng User từ session
        int userId = user.getId(); // Lấy ID người dùng từ đối tượng User

        // Khởi tạo các DAO để lấy thông tin liên lạc và media của người dùng
        UserContactDAO userContactDAO = new UserContactDAO(); // Tạo đối tượng UserContactDAO để truy vấn liên lạc
        UserMediaDAO userMediaDAO = new UserMediaDAO(); // Tạo đối tượng UserMediaDAO để truy vấn media (avatar)

        // Lấy danh sách số điện thoại và email của người dùng từ cơ sở dữ liệu
        List<UserContact> phones = userContactDAO.getUserPhones(userId); // Lấy số điện thoại người dùng
        List<UserContact> emails = userContactDAO.getUserEmails(userId); // Lấy email người dùng
        UserMedia avatar = userMediaDAO.getAvatarByUserId(userId); // Lấy avatar người dùng

        // Gửi các thông tin người dùng (số điện thoại, email, avatar) đến JSP để hiển thị
        request.setAttribute("phones", phones); // Đặt danh sách số điện thoại vào request attribute
        request.setAttribute("emails", emails); // Đặt danh sách email vào request attribute
        request.setAttribute("avatar", avatar); // Đặt avatar vào request attribute
        request.setAttribute("user", user); // Đặt thông tin người dùng vào request attribute

        // Chuyển tiếp yêu cầu đến trang JSP để hiển thị thông tin
        request.getRequestDispatcher("/user-profile.jsp").forward(request, response); // Chuyển tiếp đến user-profile.jsp
    }
}
