package Test;

import view.UserDAO;
import model.User;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        // Tạo đối tượng UserDAO để gọi phương thức lấy danh sách user
        UserDAO userDAO = new UserDAO();
        
        // Giả sử muốn thử lấy danh sách users ở trang 1, mỗi trang có 5 users
        int page = 1;
        int pageSize = 5;

        // Lấy danh sách user theo trang
        List<User> userList = userDAO.getUsersByPage(page, pageSize);

        // In ra danh sách user
        System.out.println("User List for page " + page + ":");
        for (User user : userList) {
            System.out.println("ID: " + user.getId() + ", Name: " + user.getFullName());
        }

        // Kiểm tra tổng số users và số trang
        int totalUsers = userDAO.getTotalUsers();
        int totalPages = (int) Math.ceil(totalUsers / (double) pageSize);

        System.out.println("Total Users: " + totalUsers);
        System.out.println("Total Pages: " + totalPages);
    }
}
