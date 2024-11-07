
import view.*;
import model.*;

public class Main {
    public static void main(String[] args) {
        RegistrationMediaDAO registrationMediaDAO = new RegistrationMediaDAO();

        int registrationId = 1; // Đặt giá trị ID của đăng ký để kiểm tra
        String mediaType = "image/jpeg"; // Đặt kiểu MIME của media
        byte[] mediaData = "sample image data".getBytes(); // Đặt dữ liệu media (ở đây là chuỗi mẫu chuyển đổi thành byte[])
        String mediaNote = "Sample image note"; // Đặt ghi chú của media

        // Thử thêm media vào cơ sở dữ liệu
        registrationMediaDAO.insertMedia(registrationId, mediaType, mediaData, mediaNote);

        // Xác nhận thêm thành công
        System.out.println("Media inserted successfully.");
    }
}
