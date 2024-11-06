
import java.sql.SQLException;
import java.util.List;
import model.User;
import model.UserMedia;
import view.UserMediaDAO;
import view.UserDAO;
import java.sql.Date;
import view.RegistrationDAO;

public class Main {

    public static void main(String[] args) {
        RegistrationDAO registrationDAO = new RegistrationDAO();

        // Thiết lập các tham số để kiểm tra
        int registrationId = 1; // Giá trị thử nghiệm cho registrationId
        Integer userId = null; // Kiểm tra trường hợp userId là null
        int packageId = 1; // Giá trị thử nghiệm cho packageId
        int courseId = 1; // Giá trị thử nghiệm cho courseId
        String status = "Active"; // Giá trị thử nghiệm cho status
        Date validFrom = Date.valueOf("2024-01-01"); // Giá trị thử nghiệm cho validFrom
        Date validTo = Date.valueOf("2024-12-31"); // Giá trị thử nghiệm cho validTo
        String notes = "Test note"; // Giá trị thử nghiệm cho notes

        boolean isUpdated = registrationDAO.updateRegistrationDetails(registrationId, userId, packageId, courseId, status, validFrom, validTo, notes);
        if (isUpdated) {
            System.out.println("Cập nhật thông tin đăng ký thành công.");
        } else {
            System.out.println("Cập nhật thông tin đăng ký thất bại.");
        }
    }
}
