import view.UserContactDAO;
import model.UserContact;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        UserContactDAO userContactDAO = new UserContactDAO();

        // Giả sử bạn muốn kiểm tra số điện thoại của người dùng với userId = 1
        int userId = 2;

        // Gọi phương thức để lấy danh sách số điện thoại
        List<UserContact> phones = userContactDAO.getUserPhones(userId);

        // In ra danh sách số điện thoại
        System.out.println("Phones for User ID " + userId + ":");
        for (UserContact contact : phones) {
            System.out.println("ID: " + contact.getId() +
                               ", User ID: " + contact.getUserId() +
                               ", Contact Type: " + contact.getContactType() +
                               ", Contact Value: " + contact.getContactValue() +
                               ", Is Preferred: " + contact.isPreferred());
        }
    }
}
