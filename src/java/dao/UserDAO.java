package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class UserDAO extends DBContext {

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (Connection connection = getConn();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User user = new User(
                    rs.getInt("id"),
                    rs.getString("full_name"),
                    rs.getString("gender"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("role"),
                    rs.getString("avatar_url"),
                    rs.getString("created_at"),
                    rs.getString("updated_at")
                );
                list.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insertUser(User user) {
        String query = "INSERT INTO users (full_name, gender, email, password, role, avatar_url, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConn();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getGender());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getRole());
            ps.setString(6, user.getAvatarUrl());
            ps.setString(7, user.getCreatedAt());
            ps.setString(8, user.getUpdatedAt());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(int userId) {
        String query = "DELETE FROM users WHERE id = ?";
        try (Connection connection = getConn();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateUser(User user) {
        String query = "UPDATE users SET full_name = ?, gender = ?, email = ?, password = ?, role = ?, avatar_url = ?, updated_at = ? WHERE id = ?";
        try (Connection connection = getConn();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getGender());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getRole());
            ps.setString(6, user.getAvatarUrl());
            ps.setString(7, user.getUpdatedAt());
            ps.setInt(8, user.getId());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUserById(int userId) {
        User user = null;
        String query = "SELECT * FROM users WHERE id = ?";
        try (Connection connection = getConn();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("gender"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("avatar_url"),
                        rs.getString("created_at"),
                        rs.getString("updated_at")
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
        try (Connection connection = getConn();
             PreparedStatement ps = connection.prepareStatement(query)) {

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
                        rs.getString("role"),
                        rs.getString("avatar_url"),
                        rs.getString("created_at"),
                        rs.getString("updated_at")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}
