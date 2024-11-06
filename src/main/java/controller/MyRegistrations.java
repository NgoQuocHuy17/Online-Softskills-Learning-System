package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Course;
import model.Registration;
import model.User;
import model.Package;
import view.RegistrationDAO;
import view.CourseDAO;
import view.PackageDAO;

@WebServlet(name = "MyRegistrations", urlPatterns = {"/MyRegistrations"})
public class MyRegistrations extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Khởi tạo các DAO để xử lý dữ liệu từ database
        RegistrationDAO registrationDAO = new RegistrationDAO();
        CourseDAO courseDAO = new CourseDAO();
        PackageDAO packageDAO = new PackageDAO();

        // Lấy session hiện tại và kiểm tra xem người dùng đã đăng nhập chưa
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("user") == null) {
            // Nếu chưa đăng nhập, chuyển hướng về trang đăng nhập
            response.sendRedirect("login.jsp");
            return;
        }

        // Lấy đối tượng User từ session và xác định ID của người dùng
        User user = (User) session.getAttribute("user");
        int userId = user.getId();

        // Lấy các tham số lọc từ yêu cầu người dùng, bao gồm category và searchTerm
        String category = request.getParameter("category");
        String searchTerm = request.getParameter("searchTerm");

        // Xác định pageSize (số bản ghi hiển thị mỗi trang), mặc định là 5
        int pageSize = 5;
        String pageSizeParam = request.getParameter("pageSize");
        if (pageSizeParam != null && !pageSizeParam.isEmpty()) {
            try {
                pageSize = Integer.parseInt(pageSizeParam);
            } catch (NumberFormatException e) {
                pageSize = 5; // Nếu có lỗi, quay về mặc định là 5
            }
        }

        // Xác định trang hiện tại (page), mặc định là 1
        int page = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                page = 1; // Nếu có lỗi, quay về mặc định là 1
            }
        }

        // Lấy danh sách các bản ghi đăng ký của người dùng với tính năng phân trang
        List<Registration> registrations = registrationDAO.getRegistrationsByUserIdWithPagination(userId, page, pageSize, category, searchTerm);

        // Duyệt qua từng đăng ký và lấy chi tiết của khóa học và gói đăng ký tương ứng
        for (Registration registration : registrations) {
            Course course = courseDAO.getCourseById(registration.getCourseId());
            request.setAttribute("course_" + registration.getId(), course);

            Package pkg = packageDAO.getPackageById(registration.getPackageId());
            request.setAttribute("package_" + registration.getId(), pkg);
        }

        // Tính toán tổng số trang dựa trên tổng số bản ghi và pageSize
        int totalRegistrations = registrationDAO.getTotalRegistrations(userId, category, searchTerm);
        int totalPages = (int) Math.ceil((double) totalRegistrations / pageSize);

        // Đặt các thuộc tính để truyền dữ liệu sang JSP
        request.setAttribute("registrations", registrations);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("categories", courseDAO.getAllCategories());

        // Lấy các tham số hiển thị từng trường dữ liệu từ yêu cầu người dùng
        // Mặc định tất cả các trường đều hiển thị
        boolean showTitle = request.getParameter("showTitle") != null;
        boolean showCategory = request.getParameter("showCategory") != null;
        boolean showPackage = request.getParameter("showPackage") != null;
        boolean showCost = request.getParameter("showCost") != null;
        boolean showStatus = request.getParameter("showStatus") != null;
        boolean showValid = request.getParameter("showValid") != null;

        // Đặt các thuộc tính để truyền cấu hình hiển thị các trường sang JSP
        request.setAttribute("showTitle", showTitle);
        request.setAttribute("showCategory", showCategory);
        request.setAttribute("showPackage", showPackage);
        request.setAttribute("showCost", showCost);
        request.setAttribute("showStatus", showStatus);
        request.setAttribute("showValid", showValid);

        // Chuyển tiếp yêu cầu đến trang JSP hiển thị danh sách đăng ký của người dùng
        request.getRequestDispatcher("/my-registrations.jsp").forward(request, response);
    }
}
