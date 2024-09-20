package view;

import model.User;
import java.util.List;
import java.util.Vector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author Minh
 */
public class UserDAO extends DBContext<User> {

    public User login(String email, String password) {
        User user = null;
        String query = "SELECT * FROM users WHERE email = ? AND password = ?"; // Ensure column name is consistent
        try (PreparedStatement ps = super.getConn().prepareStatement(query)) {
            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getString(8),
                            rs.getDate(9),
                            rs.getDate(10)
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> select() {
        String sql = "SELECT * FROM users";
        List<User> users = new Vector<>();
        try (PreparedStatement pre = super.getConn().prepareStatement(sql); ResultSet rs = pre.executeQuery()) {
            while (rs.next()) {
                // Create a new User object inside the loop
                User user = new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getDate(9),
                        rs.getDate(10)
                );
                users.add(user);
            }
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return users;
    }

    @Override
    public User select(int... id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        User user = null;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]);
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    user = new User(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getString(8),
                            rs.getDate(9),
                            rs.getDate(10)
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @Override
    public int insert(User user) {
        String sql = "INSERT INTO users (full_name, gender, email, password, mobile, role, avatar_url, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setString(1, user.getFullName());
            pre.setString(2, user.getGender());
            pre.setString(3, user.getEmail());
            pre.setString(4, user.getPassword());
            pre.setString(5, user.getMobile());
            pre.setString(6, user.getRole());
            pre.setString(7, user.getAvatarUrl());
            pre.setDate(8, (java.sql.Date) user.getCreatedAt());
            pre.setDate(9, (java.sql.Date) user.getUpdatedAt());
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(User user) {
        String sql = "UPDATE users SET full_name = ?, gender = ?, email = ?, password = ?, mobile = ?, role = ?, avatar_url = ?, created_at = ?, updated_at = ? WHERE id = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setString(1, user.getFullName());
            pre.setString(2, user.getGender());
            pre.setString(3, user.getEmail());
            pre.setString(4, user.getPassword());
            pre.setString(5, user.getMobile());
            pre.setString(6, user.getRole());
            pre.setString(7, user.getAvatarUrl());
            pre.setDate(8, (java.sql.Date) user.getCreatedAt());
            pre.setDate(9, (java.sql.Date) user.getUpdatedAt());
            pre.setInt(10, user.getId());
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(int... id) {
        String sql = "DELETE FROM users WHERE id = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]);
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
