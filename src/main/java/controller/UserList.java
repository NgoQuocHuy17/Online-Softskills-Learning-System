package controller;

import view.UserDAO;
import model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.UserContact;
import view.UserContactDAO;

@WebServlet(name = "UserList", urlPatterns = {"/UserList"})
public class UserList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Khởi tạo các đối tượng DAO để truy xuất dữ liệu người dùng và thông tin liên hệ
        UserDAO userDAO = new UserDAO();
        UserContactDAO userContactDAO = new UserContactDAO();

        // Lấy tham số trang hiện tại từ yêu cầu; mặc định là 1 nếu không có tham số
        int page = 1;
        int pageSize = 5; // Số lượng người dùng mặc định trên mỗi trang
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        // Lấy tham số pageSize từ yêu cầu; có thể thay đổi tùy nhu cầu người dùng
        String pageSizeParam = request.getParameter("pageSize");
        if (pageSizeParam != null && !pageSizeParam.isEmpty()) {
            pageSize = Integer.parseInt(pageSizeParam);
        }

        // Lấy các tham số lọc và tìm kiếm từ yêu cầu, đặt giá trị null nếu không có
        String genderFilter = request.getParameter("gender");
        if (genderFilter == null || genderFilter.isEmpty()) {
            genderFilter = null;
        }

        String roleFilter = request.getParameter("role");
        if (roleFilter == null || roleFilter.isEmpty()) {
            roleFilter = null;
        }

        String statusFilter = request.getParameter("status");
        if (statusFilter == null || statusFilter.isEmpty()) {
            statusFilter = null;
        }

        String searchTerm = request.getParameter("searchTerm");
        if (searchTerm == null || searchTerm.isEmpty()) {
            searchTerm = null;
        }

        // Lấy các tham số sắp xếp từ yêu cầu
        String sortBy = request.getParameter("sort");
        String sortOrder = request.getParameter("sortOrder");

        // In ra các giá trị lọc để kiểm tra
        System.out.println("Gender Filter: " + genderFilter);
        System.out.println("Role Filter: " + roleFilter);
        System.out.println("Status Filter: " + statusFilter);

        // Lấy danh sách người dùng từ DAO dựa trên trang hiện tại và các tham số lọc
        List<User> userList = userDAO.getUsersByPage(page, pageSize, genderFilter, roleFilter, statusFilter, searchTerm, sortBy, sortOrder);

        // Tính tổng số người dùng và tổng số trang để phân trang
        int totalUsers = userDAO.getTotalUsers(genderFilter, roleFilter, statusFilter, searchTerm);
        int totalPages = (int) Math.ceil(totalUsers / (double) pageSize);

        // Nếu không có người dùng hợp lệ, gán thông báo vào yêu cầu
        if (userList.isEmpty()) {
            request.setAttribute("message", "Không có người dùng hợp lệ");
        }

        // Lưu trữ danh sách số điện thoại và email cho từng người dùng
        for (User user : userList) {
            List<UserContact> phones = userContactDAO.getUserPhones(user.getId());
            List<UserContact> emails = userContactDAO.getUserEmails(user.getId());
            request.setAttribute("phones_" + user.getId(), phones); // Gán số điện thoại vào yêu cầu với id người dùng
            request.setAttribute("emails_" + user.getId(), emails); // Gán email vào yêu cầu với id người dùng
        }

        // Lấy các tham số 'HideField' từ yêu cầu để quyết định các cột cần ẩn
        String hideFullName = request.getParameter("hideFullName");
        String hideGender = request.getParameter("hideGender");
        String hideEmail = request.getParameter("hideEmail");
        String hideMobile = request.getParameter("hideMobile");
        String hideRole = request.getParameter("hideRole");
        String hideStatus = request.getParameter("hideStatus");

        // Gán các tham số ẩn cột vào yêu cầu để chuyển đến JSP
        request.setAttribute("hideFullName", hideFullName);
        request.setAttribute("hideGender", hideGender);
        request.setAttribute("hideEmail", hideEmail);
        request.setAttribute("hideMobile", hideMobile);
        request.setAttribute("hideRole", hideRole);
        request.setAttribute("hideStatus", hideStatus);

        // Gửi danh sách người dùng, thông tin phân trang, và các tham số lọc đến trang JSP
        request.setAttribute("userList", userList);
        request.setAttribute("currentPage", page);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("genderFilter", genderFilter);
        request.setAttribute("roleFilter", roleFilter);
        request.setAttribute("statusFilter", statusFilter);
        request.setAttribute("searchTerm", searchTerm);
        request.setAttribute("sort", sortBy);
        request.setAttribute("sortOrder", sortOrder);

        // Chuyển hướng đến trang "user-list.jsp" để hiển thị danh sách người dùng
        request.getRequestDispatcher("/user-list.jsp").forward(request, response);
    }
}
