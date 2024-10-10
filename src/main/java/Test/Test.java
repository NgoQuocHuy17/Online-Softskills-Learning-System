package Test;

import java.util.List;
import model.Course;
import model.User;
import view.CourseDAO;
import view.UserDAO;

public class Test {
//    public static void main(String[] args) {
//        UserDAO userDAO = new UserDAO();
//
//        // Tham số cho phương thức getUsersByPage
//        int pageNumber = 1;      // Số trang
//        int pageSize = 5;        // Số lượng người dùng mỗi trang
//        String genderFilter = "Male";  // Lọc theo giới tính (null nếu không lọc)
//        String roleFilter = null;     // Lọc theo vai trò (null nếu không lọc)
//        String statusFilter = null;   // Lọc theo trạng thái (null nếu không lọc)
//        String searchTerm = null;     // Tìm kiếm (null nếu không tìm kiếm)
//        String sortBy = null;         // Sắp xếp theo cột nào
//        String sortOrder = null;     // Thứ tự sắp xếp (ASC hoặc DESC)
//
//        // Gọi phương thức getUsersByPage
//        List<User> userList = userDAO.getUsersByPage(pageNumber, pageSize, genderFilter, roleFilter, statusFilter, searchTerm, sortBy, sortOrder);
//
//        // In danh sách người dùng
//        for (User user : userList) {
//            System.out.println("User ID: " + user.getId());
//            System.out.println("Full Name: " + user.getFullName());
//            System.out.println("Gender: " + user.getGender());
//            System.out.println("Email: " + user.getEmail());
//            System.out.println("Role: " + user.getRole());
//            System.out.println("Status: " + (user.getIsValid() == 1 ? "Valid" : "Invalid"));
//            System.out.println("---------------------------");
//        }
//    }
 
    public static void main(String[] args) {
        CourseDAO courseDAO = new CourseDAO(); // Giả sử lớp của bạn có tên là CourseDAO
        List<String> statuses = courseDAO.getAllStatuses();

        // In ra các trạng thái
        if (statuses.isEmpty()) {
            System.out.println("Không có trạng thái nào.");
        } else {
            System.out.println("Các trạng thái có trong bảng courses:");
            for (String status : statuses) {
                System.out.println(status);
            }
        }
    }
}


