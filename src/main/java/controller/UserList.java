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

@WebServlet(name = "UserList", urlPatterns = {"/user-list"})
public class UserList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        UserContactDAO userContactDAO = new UserContactDAO();

        // Lấy số trang từ request (mặc định là 1 nếu không có tham số)
        int page = 1;
        int pageSize = 5; // Số lượng users mỗi trang
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        // Lấy các tham số lọc và tìm kiếm từ request
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

        String sortBy = request.getParameter("sort");
        String sortOrder = request.getParameter("sortOrder");

        // Lấy danh sách users theo trang với các tham số lọc và tìm kiếm
        List<User> userList = userDAO.getUsersByPage(page, pageSize, genderFilter, roleFilter, statusFilter, searchTerm, sortBy, sortOrder);

        // Tính tổng số users và số trang
        int totalUsers = userDAO.getTotalUsers(genderFilter, roleFilter, statusFilter, searchTerm);
        int totalPages = (int) Math.ceil(totalUsers / (double) pageSize);

        if (userList.isEmpty()) {
            request.setAttribute("message", "Không có người dùng hợp lệ");
        }

        // Lưu trữ danh sách số điện thoại và email
        for (User user : userList) {
            List<UserContact> phones = userContactDAO.getUserPhones(user.getId());
            List<UserContact> emails = userContactDAO.getUserEmails(user.getId());
            request.setAttribute("phones_" + user.getId(), phones);
            request.setAttribute("emails_" + user.getId(), emails);
        }

        // Gửi danh sách users và các thông tin phân trang đến trang JSP
        request.setAttribute("userList", userList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("genderFilter", genderFilter);
        request.setAttribute("roleFilter", roleFilter);
        request.setAttribute("statusFilter", statusFilter);
        request.setAttribute("searchTerm", searchTerm);
        request.setAttribute("sort", sortBy);
        request.setAttribute("sortOrder", sortOrder);

        request.getRequestDispatcher("/user-list.jsp").forward(request, response);
    }
}
