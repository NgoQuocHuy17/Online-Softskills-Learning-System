
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.util.List;
import model.User;
import model.UserMedia;
import view.UserMediaDAO;
import view.UserDAO;

@WebServlet(name = "UpdateUserDetailsTest", urlPatterns = {"/UpdateUserDetailsTest"})
@MultipartConfig
public class UpdateUserDetailsTest extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        Part mediaPart = request.getPart("media");

        UserDAO userDAO = new UserDAO();
        UserMediaDAO userMediaDAO = new UserMediaDAO();

        // Lấy kiểu media và dữ liệu
        String mediaType = mediaPart.getContentType();
        byte[] mediaData = new byte[(int) mediaPart.getSize()];

        // Đọc dữ liệu từ file upload
        try (InputStream inputStream = mediaPart.getInputStream()) {
            inputStream.read(mediaData);
        }

        // Ghi dữ liệu vào database
        userMediaDAO.insertMedia(userId, mediaType, mediaData);

        // Lấy thông tin user để hiển thị trên JSP
        User user = userDAO.getUserById(userId);

        // Set message attribute để hiển thị trên JSP
        request.setAttribute("message", "Upload successful!");
        
        List<UserMedia> images = userMediaDAO.getImagesByUserId(userId);
        List<UserMedia> videos = userMediaDAO.getVideosByUserId(userId);

        // Đặt thông tin người dùng và danh sách ảnh và video vào request
        request.setAttribute("user", user);
        request.setAttribute("images", images);
        request.setAttribute("videos", videos);

        // Chuyển hướng trở lại trang cập nhật
        request.getRequestDispatcher("UserDetailsTest.jsp").forward(request, response);
    }
}