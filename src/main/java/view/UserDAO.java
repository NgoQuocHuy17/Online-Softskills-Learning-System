package view;

import controller.SendingEmail;
import controller.RegisterBean;
import java.sql.Connection;
import model.User;
import java.util.List;
import java.util.Vector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author Minh
 */
public class UserDAO extends DBContext<User> {

    public boolean updateProfile(User user) {
        String query = "UPDATE users SET full_name = ?, gender = ?, mobile = ? WHERE id = ?";

        try (PreparedStatement ps = super.getConn().prepareStatement(query)) {
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getGender());
//            ps.setString(3, user.getMobile());
            ps.setInt(4, user.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

//    public String register(RegisterBean rb) {
//        String fullName = rb.getFullName();
//        String email = rb.getEmail();
//        String mobile = rb.getMobile();
//        String gender = rb.getGender();
//        String password = rb.getPassword();
//        String myHash = rb.getMyHash();
//
//        try {
//            String sqlQuery = "INSERT INTO users (full_name, gender, email, password, mobile, hash) values (?, ?, ?, ?, ?, ?)";
//            PreparedStatement ps = super.getConn().prepareStatement(sqlQuery);
//            ps.setString(1, fullName);
//            ps.setString(2, gender);
//            ps.setString(3, email);
//            ps.setString(4, password);
//            ps.setString(5, mobile);
//            ps.setString(6, myHash);
//
//            int i = ps.executeUpdate();
//            if (i != 0) {
//                SendingEmail se = new SendingEmail(email, myHash);
//                se.sendMail();
//                return "SUCCESS";
//            }
//
//        } catch (Exception e) {
//
//            System.out.println("RegisterDAO error!");
//        }
//        return "ERROR";
//
//    }

    public User login(String email, String password) {
        User user = null;
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
//        String isValid = "SELECT isValid FROM users WHERE email = ? AND password = ?";
        boolean isValid = false;

        try (Connection connection = getConn(); PreparedStatement ps = connection.prepareStatement("SELECT isValid FROM users WHERE email = ? AND password = ?")) {
            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    isValid = rs.getBoolean("isValid"); // Lấy giá trị cột isValid
                }
            }
        } catch (Exception e) {

        }

        if (isValid == false) {
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
                            rs.getString("role"),
                            rs.getString("avatar_url"),
                            rs.getDate("created_at"),
                            rs.getDate("updated_at"),
                            rs.getString("hash"), // Thêm thuộc tính hash
                            rs.getInt("isValid") // Thêm thuộc tính isValid
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
                // Tạo một đối tượng User mới trong vòng lặp
                User user = new User(
                        rs.getInt("id"), // Lấy id
                        rs.getString("full_name"), // Lấy full_name
                        rs.getString("gender"), // Lấy gender
                        rs.getString("email"), // Lấy email
                        rs.getString("password"), // Lấy password
                        rs.getString("role"), // Lấy role
                        rs.getString("avatar_url"), // Lấy avatar_url
                        rs.getDate("created_at"), // Lấy created_at
                        rs.getDate("updated_at"), // Lấy updated_at
                        rs.getString("hash"), // Lấy hash
                        rs.getInt("isValid") // Lấy isValid
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
                            rs.getInt("id"), // Lấy id
                            rs.getString("full_name"), // Lấy full_name
                            rs.getString("gender"), // Lấy gender
                            rs.getString("email"), // Lấy email
                            rs.getString("password"), // Lấy password
                            rs.getString("role"), // Lấy role
                            rs.getString("avatar_url"), // Lấy avatar_url
                            rs.getDate("created_at"), // Lấy created_at
                            rs.getDate("updated_at"), // Lấy updated_at
                            rs.getString("hash"), // Lấy hash
                            rs.getInt("isValid") // Lấy isValid
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
        String sql = "INSERT INTO users (full_name, gender, email, password, role, avatar_url, created_at, updated_at, hash, isValid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setString(1, user.getFullName());
            pre.setString(2, user.getGender());
            pre.setString(3, user.getEmail());
            pre.setString(4, user.getPassword());
            pre.setString(5, user.getRole());
            pre.setString(6, user.getAvatarUrl());
            pre.setDate(7, new java.sql.Date(user.getCreatedAt().getTime())); // Chuyển đổi Date sang java.sql.Date
            pre.setDate(8, new java.sql.Date(user.getUpdatedAt().getTime())); // Chuyển đổi Date sang java.sql.Date
            pre.setString(9, user.getHash());  // Thêm hash
            pre.setInt(10, user.getIsValid()); // Thêm isValid
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(User user) {
        String sql = "UPDATE users SET full_name = ?, gender = ?, email = ?, password = ?, role = ?, avatar_url = ?, created_at = ?, updated_at = ?, hash = ?, isValid = ? WHERE id = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setString(1, user.getFullName());
            pre.setString(2, user.getGender());
            pre.setString(3, user.getEmail());
            pre.setString(4, user.getPassword());
            pre.setString(5, user.getRole());
            pre.setString(6, user.getAvatarUrl());
            pre.setDate(7, new java.sql.Date(user.getCreatedAt().getTime())); // Chuyển đổi Date sang java.sql.Date
            pre.setDate(8, new java.sql.Date(user.getUpdatedAt().getTime())); // Chuyển đổi Date sang java.sql.Date
            pre.setString(9, user.getHash());  // Gán giá trị cho hash
            pre.setInt(10, user.getIsValid()); // Gán giá trị cho isValid
            pre.setInt(11, user.getId()); // Thêm id của user để cập nhật
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


     public String getEmailByUser(String user) throws SQLException {
        String sql = "Select email from users where name = ?";
        String email = "";
        PreparedStatement ps = super.getConn().prepareStatement(sql);
        ps.setString(1, user);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            email = rs.getString("email");
            return email;
        }
        return null;
    }
 public void updatePassword(String name, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE email = ?";
        try {
            PreparedStatement ps = super.getConn().prepareStatement(sql);
            ps.setString(1, newPassword);
            ps.setString(2, name);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("updatePassword: " + e.getMessage());
        }
    }


     public boolean isEmailExist(String email) throws SQLException {
        String sql = "Select email from users where email = ?";
        PreparedStatement ps = super.getConn().prepareStatement(sql);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return true;
        }
        return false;
    }


    public List<User> getUsersByPage(int pageNumber, int pageSize) {
        List<User> list = new ArrayList<>();
        String query = "SELECT * FROM users ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        int offset = (pageNumber - 1) * pageSize;

        try (Connection connection = getConn(); PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, offset);   // Bắt đầu từ vị trí offset
            ps.setInt(2, pageSize); // Số lượng users mỗi trang

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User user = new User(
                            rs.getInt("id"),
                            rs.getString("full_name"),
                            rs.getString("gender"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("role"),
                            rs.getString("avatar_url"),
                            rs.getDate("created_at"),
                            rs.getDate("updated_at"),
                            rs.getString("hash"), // Thêm thuộc tính hash
                            rs.getInt("isValid") // Thêm thuộc tính isValid
                    );
                    list.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Phương thức đếm tổng số lượng users
    public int getTotalUsers() {
        int totalUsers = 0;
        String query = "SELECT COUNT(*) AS total FROM users";

        try (Connection connection = getConn(); PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                totalUsers = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalUsers;
    }

}
