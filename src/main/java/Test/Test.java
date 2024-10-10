package Test;

import model.User;
import view.UserDAO;

public class Test {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO(); // Giả sử bạn đã có một lớp UserDAO
        int testUserId = 1; // Thay đổi ID người dùng để kiểm tra

        User user = userDAO.getUserById(testUserId);

        if (user != null) {
            System.out.println("User found:");
            System.out.println("ID: " + user.getId());
            System.out.println("Full Name: " + user.getFullName());
            System.out.println("Gender: " + user.getGender());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Role: " + user.getRole());
            System.out.println("Avatar URL: " + user.getAvatarUrl());
            System.out.println("Created At: " + user.getCreatedAt());
            System.out.println("Updated At: " + user.getUpdatedAt());
            System.out.println("Hash: " + user.getHash());
            System.out.println("Is Valid: " + user.getIsValid());
        } else {
            System.out.println("User not found with ID: " + testUserId);
        }
    }
}
