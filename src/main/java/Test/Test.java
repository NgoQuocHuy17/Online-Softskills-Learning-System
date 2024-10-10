package Test;

import model.User;
import view.UserDAO;

public class Test {
    public static void main(String[] args) {
        // Khởi tạo UserDAO
        UserDAO userDAO = new UserDAO();

        // Tạo một đối tượng User mới
        User newUser = new User();
        newUser.setFullName("John Doe");
        newUser.setGender("Male");
        newUser.setEmail("johndoe@example.com");
        newUser.setPassword("securePassword123");
        newUser.setRole("Student");
        newUser.setIsValid(1); // 1 cho Active, 0 cho Inactive

        // Thêm user vào cơ sở dữ liệu
        boolean isAdded = userDAO.addUser(newUser);

        // Kiểm tra kết quả
        if (isAdded) {
            System.out.println("User added successfully!");
        } else {
            System.out.println("Failed to add user.");
        }
    }
}
