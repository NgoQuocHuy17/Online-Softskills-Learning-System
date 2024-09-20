package dao;

import controller.SendingEmail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;
import controller.registerBean;

public class UserDAO extends DBContext {

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (Connection connection = getConn(); PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("gender"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("mobile"),
                        rs.getString("role"),
                        rs.getString("avatar_url"),
                        rs.getString("created_at"), // Giữ nguyên là String
                        rs.getString("updated_at") // Giữ nguyên là String
                );
                list.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insertUser(User user) {
        String query = "INSERT INTO users (full_name, gender, email, password, mobile, role, avatar_url, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConn(); PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getGender());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getMobile());
            ps.setString(6, user.getRole());
            ps.setString(7, user.getAvatarUrl());
            ps.setString(8, user.getCreatedAt()); // Giữ nguyên là String
            ps.setString(9, user.getUpdatedAt()); // Giữ nguyên là String
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(int userId) {
        String query = "DELETE FROM users WHERE id = ?";
        try (Connection connection = getConn(); PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateUser(User user) {
        String query = "UPDATE users SET full_name = ?, gender = ?, email = ?, password = ?, mobile = ?, role = ?, avatar_url = ?, updated_at = ? WHERE id = ?";
        try (Connection connection = getConn(); PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getGender());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getMobile());
            ps.setString(6, user.getRole());
            ps.setString(7, user.getAvatarUrl());
            ps.setString(8, user.getUpdatedAt()); // Giữ nguyên là String
            ps.setInt(9, user.getId());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUserById(int userId) {
        User user = null;
        String query = "SELECT * FROM users WHERE id = ?";
        try (Connection connection = getConn(); PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User(
                            rs.getInt("id"),
                            rs.getString("full_name"),
                            rs.getString("gender"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("mobile"),
                            rs.getString("role"),
                            rs.getString("avatar_url"),
                            rs.getString("created_at"), // Giữ nguyên là String
                            rs.getString("updated_at") // Giữ nguyên là String
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User login(String email, String password) {
        User user = null;
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        String isValid = "SELECT isValid FROM users WHERE email = ? AND password = ?";
        if(isValid.equals('0')){
            return null;
        }
        
        try (Connection connection = getConn(); PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User(
                            rs.getInt("id"),
                            rs.getString("full_name"),
                            rs.getString("gender"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("mobile"),
                            rs.getString("role"),
                            rs.getString("avatar_url"),
                            rs.getString("created_at"), // Giữ nguyên là String
                            rs.getString("updated_at") // Giữ nguyên là String
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public String register(registerBean rb) {
        String fullName = rb.getFullName();
        String email = rb.getEmail();
        String mobile = rb.getMobile();
        String gender = rb.getGender();
        String password = rb.getPassword();
        String myHash = rb.getMyHash();

        Connection connection = getConn();

        try {
            String sqlQuery = "INSERT INTO users (full_name, gender, email, password, mobile, hash) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, fullName);
            ps.setString(2, gender);
            ps.setString(1, email);
            ps.setString(1, password);
            ps.setString(1, mobile);
            ps.setString(1, myHash);
            
            int i = ps.executeUpdate();
            if(i != 0){
                SendingEmail se = new SendingEmail(email, myHash);
                se.sendMail();
                return "SUCCESS";
            }

        } catch (Exception e) {
            
            System.out.println("RegisterDAO error!");
        }
        return "ERROR";

    }

    public boolean updateProfile(User user) {
        // Query để cập nhật thông tin người dùng
        String query = "UPDATE users SET full_name = ?, gender = ?, mobile = ? WHERE id = ?";

        try (Connection connection = getConn(); PreparedStatement ps = connection.prepareStatement(query)) {
            // Gán giá trị cho các tham số trong câu lệnh SQL
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getGender());
            ps.setString(3, user.getMobile());
            ps.setInt(4, user.getId());

            // Thực hiện cập nhật
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
