
import java.sql.SQLException;
import java.util.List;
import model.User;
import model.UserMedia;
import view.UserMediaDAO;
import view.UserDAO;


public class Main {

    public static void main(String[] args) {
        // Khởi tạo DAO
        UserDAO userDao = new UserDAO();

        // Thiết lập các tham số thử nghiệm
        int pageNumber = 1;
        int pageSize = 10;
        String genderFilter = ""; // Ví dụ: Lọc theo giới tính
        String roleFilter = ""; // Không lọc theo vai trò
        String statusFilter = ""; // Ví dụ: Lọc theo trạng thái
        String searchTerm = ""; // Không có từ khóa tìm kiếm
        String sortBy = ""; // Sắp xếp theo tên đầy đủ
        String sortOrder = ""; // Thứ tự sắp xếp tăng dần
        // Gọi hàm getUsersByPage và in kết quả
        List<User> users = userDao.getUsersByPage(pageNumber, pageSize, genderFilter, roleFilter, statusFilter, searchTerm, sortBy, sortOrder);
        for (User user : users) {
            System.out.println(user);
        }
    }

}
