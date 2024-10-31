import java.util.List;
import view.RegistrationDAO; // Đảm bảo import đúng package chứa RegistrationDAO
import model.Registration; // Đảm bảo import đúng package chứa Registration

public class Main {
    public static void main(String[] args) {
        // Tạo đối tượng RegistrationDAO
        RegistrationDAO registrationDAO = new RegistrationDAO();

        // Thay đổi ID người dùng, category và searchTerm theo nhu cầu
        int userId = 1; // Giả sử ID người dùng là 1
        String category = "Communication"; // Thay thế với category thực tế nếu cần
        String searchTerm = "Communication"; // Thay thế với title thực tế nếu cần

        // Gọi phương thức getTotalRegistrations để lấy tổng số đăng ký
        int totalRegistrations = registrationDAO.getTotalRegistrations(userId, category, searchTerm);

        // In ra kết quả
        System.out.println("Total registrations for user ID " + userId + 
                           " with category '" + category + "' and search term '" + searchTerm + "': " + 
                           totalRegistrations);
    }
}