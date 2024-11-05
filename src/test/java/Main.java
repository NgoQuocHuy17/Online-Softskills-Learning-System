
import java.util.List;
import model.UserMedia;
import view.UserMediaDAO;

public class Main {

    public static void main(String[] args) {
        UserMediaDAO userMediaDAO = new UserMediaDAO();

        // Giả sử bạn đã có một userId để kiểm tra, thay đổi giá trị này theo ý bạn
        int userId = 1; // Thay đổi thành userId mà bạn muốn kiểm tra

        // Lấy danh sách hình ảnh của người dùng
        List<UserMedia> images = userMediaDAO.getImagesByUserId(userId);

        // Kiểm tra xem có hình ảnh nào không
        if (images.isEmpty()) {
            System.out.println("No images found for user ID: " + userId);
        } else {
            // In thông tin các hình ảnh
            System.out.println("Images for user ID: " + userId);
            for (UserMedia image : images) {
                System.out.println("ID: " + image.getId());
                System.out.println("User ID: " + image.getUserId());
                System.out.println("Media Type: " + image.getMediaType());
                System.out.println("------------------------------");
            }
        }
    }
}
