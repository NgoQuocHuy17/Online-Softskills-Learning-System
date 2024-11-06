
import model.User;
import view.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.UserMedia;
import view.UserMediaDAO;

@WebServlet(name = "UserDetailsTest", urlPatterns = {"/UserDetailsTest"})
public class UserDetailsTest extends HttpServlet {

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
        User user = userDAO.getUserById(userId);

        // Tạo đối tượng UserMediaDAO và lấy ảnh và video của người dùng
        UserMediaDAO userMediaDAO = new UserMediaDAO();
        List<UserMedia> images = userMediaDAO.getImagesByUserId(userId);
        List<UserMedia> videos = userMediaDAO.getVideosByUserId(userId);

        // Đặt thông tin người dùng và danh sách ảnh và video vào request
        request.setAttribute("user", user);
        request.setAttribute("images", images);
        request.setAttribute("videos", videos);

        // Chuyển hướng đến JSP để hiển thị thông tin người dùng và media
        request.getRequestDispatcher("/UserDetailsTest.jsp").forward(request, response);
    }
}
