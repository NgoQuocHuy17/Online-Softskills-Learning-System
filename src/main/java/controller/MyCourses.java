package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Course;
import model.User;
import view.CourseDAO;
import view.UserCourseDAO;

@WebServlet(name = "MyCourses", urlPatterns = {"/MyCourses"})
public class MyCourses extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy session hiện tại. Nếu không có session hoặc chưa đăng nhập, chuyển hướng về trang đăng nhập
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Lấy thông tin người dùng đã đăng nhập từ session
        User user = (User) session.getAttribute("user");
        int userId = user.getId();

        // Khởi tạo các đối tượng DAO để thao tác với database
        UserCourseDAO userCourseDAO = new UserCourseDAO();
        CourseDAO courseDAO = new CourseDAO();

        // Lấy các tham số phân trang từ yêu cầu người dùng
        int page = 1; // Trang mặc định
        int pageSize = 5; // Kích thước trang mặc định (số bản ghi hiển thị mỗi trang)
        String pageParam = request.getParameter("page");
        String pageSizeParam = request.getParameter("pageSize");

        // Kiểm tra và chuyển đổi tham số "page" từ yêu cầu người dùng
        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                page = 1; // Nếu có lỗi, quay về mặc định là 1
            }
        }

        // Kiểm tra và chuyển đổi tham số "pageSize" từ yêu cầu người dùng
        if (pageSizeParam != null && !pageSizeParam.isEmpty()) {
            try {
                pageSize = Integer.parseInt(pageSizeParam);
            } catch (NumberFormatException e) {
                pageSize = 5; // Nếu có lỗi, quay về mặc định là 5
            }
        }

        // Lấy danh sách ID của các khóa học mà người dùng có quyền truy cập, có tính năng phân trang
        List<Integer> courseIds = userCourseDAO.getCourseIdsByUserId(userId, page, pageSize);

        // Lấy thông tin chi tiết của từng khóa học dựa trên ID của khóa học
        List<Course> courseList = new ArrayList<>();
        for (int courseId : courseIds) {
            Course course = courseDAO.getCourseById(courseId);
            if (course != null) {
                courseList.add(course); // Chỉ thêm khóa học vào danh sách nếu khóa học tồn tại
            }
        }

        // Đặt danh sách khóa học vào yêu cầu để chuyển tiếp sang trang JSP
        request.setAttribute("courseList", courseList);

        // Thiết lập các thuộc tính phân trang
        int totalCourses = userCourseDAO.getTotalCoursesByUserId(userId); // Cần cài đặt phương thức này trong UserCourseDAO
        int totalPages = (int) Math.ceil((double) totalCourses / pageSize); // Tính tổng số trang dựa trên tổng số khóa học và pageSize
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("pageSize", pageSize);

        // Lấy các tham số hiển thị từ yêu cầu người dùng với giá trị mặc định là hiển thị tất cả
        boolean showTitle = true;
        boolean showTagline = true;
        boolean showDescription = true;
        boolean showCategory = true;

        showTitle = request.getParameter("showTitle") != null;
        showTagline = request.getParameter("showTagline") != null;
        showDescription = request.getParameter("showDescription") != null;
        showCategory = request.getParameter("showCategory") != null;

        // Đặt các tham số hiển thị vào yêu cầu để chuyển tiếp sang trang JSP
        request.setAttribute("showTitle", showTitle);
        request.setAttribute("showTagline", showTagline);
        request.setAttribute("showDescription", showDescription);
        request.setAttribute("showCategory", showCategory);

        // Chuyển tiếp yêu cầu đến trang "my-courses.jsp" để hiển thị danh sách khóa học của người dùng
        request.getRequestDispatcher("my-courses.jsp").forward(request, response);
    }
}
