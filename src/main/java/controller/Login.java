package controller;

// Import các thư viện cần thiết cho Servlet
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import view.UserDAO;

// Định nghĩa Servlet với tên "Login" và đường dẫn truy cập "/Login"
@WebServlet(name = "Login", urlPatterns = "/Login")
public class Login extends HttpServlet {

    // Phương thức xử lý yêu cầu chính cho cả GET và POST
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Đặt kiểu nội dung phản hồi là HTML với mã hóa UTF-8
        response.setContentType("text/html;charset=UTF-8");

        // Lấy thông tin email và password từ yêu cầu của người dùng
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Khởi tạo đối tượng UserDAO để thực hiện kiểm tra đăng nhập
        UserDAO userDAO = new UserDAO();
        User user = userDAO.login(email, password); // Gọi phương thức login để xác thực người dùng

        // Kiểm tra kết quả đăng nhập
        if (user == null) {
            // Đăng nhập thất bại, gán thông báo lỗi và chuyển hướng về trang login
            request.setAttribute("error", "Invalid email or password or account not activated");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            // Đăng nhập thành công, tạo session và chuyển hướng đến trang chủ
            HttpSession session = request.getSession();
            session.setAttribute("user", user); // Lưu đối tượng User vào session
            session.setMaxInactiveInterval(600); // Đặt thời gian tồn tại của session (600 giây)
            response.sendRedirect("home"); // Chuyển hướng đến trang chủ
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
