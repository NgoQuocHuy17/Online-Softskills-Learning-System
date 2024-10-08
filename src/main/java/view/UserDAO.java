package view;

import controller.SendMailActivateAcc;
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

public class UserDAO extends DBContext<User> {

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
                            rs.getString("role"),
                            rs.getString("avatar_url"),
                            rs.getDate("created_at"),
                            rs.getDate("updated_at"),
                            rs.getString("hash"),
                            rs.getInt("isValid")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public boolean addUser(User user) {
        String sql = "INSERT INTO users (full_name, gender, email, password, role, isValid) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConn(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getFullName());
            stmt.setString(2, user.getGender());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getRole());
            stmt.setInt(6, user.getIsValid());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

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
                            rs.getString("hash"),
                            rs.getInt("isValid")
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
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("gender"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("avatar_url"),
                        rs.getDate("created_at"),
                        rs.getDate("updated_at"),
                        rs.getString("hash"),
                        rs.getInt("isValid")
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
                            rs.getInt("id"),
                            rs.getString("full_name"),
                            rs.getString("gender"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("role"),
                            rs.getString("avatar_url"),
                            rs.getDate("created_at"),
                            rs.getDate("updated_at"),
                            rs.getString("hash"),
                            rs.getInt("isValid")
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
            pre.setDate(7, new java.sql.Date(user.getCreatedAt().getTime()));
            pre.setDate(8, new java.sql.Date(user.getUpdatedAt().getTime()));
            pre.setString(9, user.getHash());
            pre.setInt(10, user.getIsValid());
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int getIDByUser(String name) throws SQLException {
        String sql = "Select id from users where full_name = ?";
        int id;
        PreparedStatement ps = super.getConn().prepareStatement(sql);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            id = rs.getInt("id");
            return id;
        }
        return 0;
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
            pre.setDate(7, new java.sql.Date(user.getCreatedAt().getTime()));
            pre.setDate(8, new java.sql.Date(user.getUpdatedAt().getTime()));
            pre.setString(9, user.getHash());
            pre.setInt(10, user.getIsValid());
            pre.setInt(11, user.getId());
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

    public List<User> getUsersByPage(int pageNumber, int pageSize, String genderFilter, String roleFilter, String statusFilter, String searchTerm, String sortBy, String sortOrder) {
        List<User> list = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM users WHERE 1=1");

        // Thêm điều kiện lọc
        if (genderFilter != null && !genderFilter.isEmpty()) {
            query.append(" AND gender = ?");
        }
        if (roleFilter != null && !roleFilter.isEmpty()) {
            query.append(" AND role = ?");
        }
        if (statusFilter != null && !statusFilter.isEmpty()) {
            query.append(" AND isValid = ?");
        }
        if (searchTerm != null && !searchTerm.isEmpty()) {
            query.append(" AND (full_name LIKE ? OR email LIKE ? OR id IN (SELECT user_id FROM user_contacts WHERE contact_value LIKE ?))");
        }

        // Thêm điều kiện sắp xếp
        if (sortBy != null && !sortBy.isEmpty()) {
            query.append(" ORDER BY ").append(sortBy).append(" ").append(sortOrder);
        } else {
            query.append(" ORDER BY id"); // Sắp xếp mặc định theo id nếu không có tham số
        }

        // Thêm phân trang
        query.append(" OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

        int offset = (pageNumber - 1) * pageSize;

        try (Connection connection = getConn(); PreparedStatement ps = connection.prepareStatement(query.toString())) {
            int index = 1;

            // Thiết lập các tham số lọc
            if (genderFilter != null && !genderFilter.isEmpty()) {
                ps.setString(index++, genderFilter);
            }
            if (roleFilter != null && !roleFilter.isEmpty()) {
                ps.setString(index++, roleFilter);
            }
            if (statusFilter != null && !statusFilter.isEmpty()) {
                ps.setBoolean(index++, statusFilter.equals("Valid"));
            }
            if (searchTerm != null && !searchTerm.isEmpty()) {
                String searchPattern = "%" + searchTerm + "%";
                ps.setString(index++, searchPattern);
                ps.setString(index++, searchPattern);
                ps.setString(index++, searchPattern);
            }

            // Thiết lập tham số offset và pageSize
            ps.setInt(index++, offset);
            ps.setInt(index++, pageSize);

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
                            rs.getString("hash"),
                            rs.getInt("isValid")
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
    public int getTotalUsers(String genderFilter, String roleFilter, String statusFilter, String searchTerm) {
        int totalUsers = 0;
        StringBuilder query = new StringBuilder("SELECT COUNT(*) AS total FROM users WHERE 1=1");

        if (genderFilter != null && !genderFilter.isEmpty()) {
            query.append(" AND gender = ?");
        }
        if (roleFilter != null && !roleFilter.isEmpty()) {
            query.append(" AND role = ?");
        }
        if (statusFilter != null) {
            query.append(" AND isValid = ?");
        }
        if (searchTerm != null && !searchTerm.isEmpty()) {
            query.append(" AND (full_name LIKE ? OR email LIKE ? OR id IN (SELECT user_id FROM user_contacts WHERE contact_value LIKE ?))");
        }

        try (Connection connection = getConn(); PreparedStatement ps = connection.prepareStatement(query.toString())) {
            int paramIndex = 1;

            if (genderFilter != null && !genderFilter.isEmpty()) {
                ps.setString(paramIndex++, genderFilter);
            }
            if (roleFilter != null && !roleFilter.isEmpty()) {
                ps.setString(paramIndex++, roleFilter);
            }
            if (statusFilter != null) {
                ps.setBoolean(paramIndex++, statusFilter.equals("Valid"));
            }
            if (searchTerm != null && !searchTerm.isEmpty()) {
                String likeTerm = "%" + searchTerm + "%";
                ps.setString(paramIndex++, likeTerm);
                ps.setString(paramIndex++, likeTerm);
                ps.setString(paramIndex++, likeTerm);
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalUsers = rs.getInt("total");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalUsers;
    }

    public boolean updateUserRoleAndStatus(int userId, String role, int isValid) {
        String query = "UPDATE users SET role = ?, isValid = ? WHERE id = ?";
        try (Connection connection = getConn(); PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, role);
            ps.setInt(2, isValid);
            ps.setInt(3, userId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
