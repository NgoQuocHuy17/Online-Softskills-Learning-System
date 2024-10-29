import java.util.List;
import view.RegistrationDAO; // Đảm bảo import đúng package chứa RegistrationDAO
import model.Registration; // Đảm bảo import đúng package chứa Registration

public class Main {
    public static void main(String[] args) {
        RegistrationDAO registrationDAO = new RegistrationDAO();

        // Test userId (thay thế bằng userId hợp lệ từ cơ sở dữ liệu của bạn)
        int testUserId = 1;

        // Lấy danh sách các đăng ký cho user
        List<Registration> registrations = registrationDAO.getRegistrationsByUserId(testUserId);

        // In kết quả
        if (registrations.isEmpty()) {
            System.out.println("Không có đăng ký nào cho user với ID: " + testUserId);
        } else {
            System.out.println("Danh sách đăng ký cho user ID " + testUserId + ":");
            for (Registration registration : registrations) {
                System.out.println(registration);
            }
        }
    }
}
