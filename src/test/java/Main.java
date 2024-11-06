
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

        // Các tham số để kiểm tra
        int registrationId = 1; // Giá trị thử nghiệm
        int userId = 0; // Kiểm tra trường hợp userId là 0
        int packageId = 1; // Giá trị thử nghiệm
        int courseId = 1; // Giá trị thử nghiệm
        String status = "Active"; // Giá trị thử nghiệm
        Date validFrom = Date.valueOf("2023-01-01"); // Giá trị thử nghiệm
        Date validTo = Date.valueOf("2023-12-31"); // Giá trị thử nghiệm
        String notes = "Updated1"; // Giá trị thử nghiệm

        boolean isUpdated = registrationDAO.updateRegistrationDetails(registrationId, userId, packageId, courseId, status, validFrom, validTo, notes);
        if (isUpdated) {
            System.out.println("Cập nhật thông tin đăng ký thành công.");
        } else {
            System.out.println("Cập nhật thông tin đăng ký thất bại.");
        }
    }
}


