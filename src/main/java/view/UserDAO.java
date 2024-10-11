package view;

import controller.RegisterBean;
import controller.SendingEmail;
import model.User;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
            ps.setString(3, user.getMobile());
            ps.setInt(4, user.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String register(RegisterBean rb) {
        String fullName = rb.getFullName();
        String email = rb.getEmail();
        String mobile = rb.getMobile();
        String gender = rb.getGender();
        String password = rb.getPassword();
        String myHash = rb.getMyHash();

        try {
            String sqlQuery = "INSERT INTO users (full_name, gender, email, password, mobile, hash) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = super.getConn().prepareStatement(sqlQuery);
            ps.setString(1, fullName);
            ps.setString(2, gender);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setString(5, mobile);
            ps.setString(6, myHash);

            int i = ps.executeUpdate();
            if (i != 0) {
                SendingEmail se = new SendingEmail(email, myHash);
                se.sendMail();
                return "SUCCESS";
            }

        } catch (Exception e) {

            System.out.println("RegisterDAO error!");
        }
        return "ERROR";

    }
    public User login(String email, String password) {
        User user = null;
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
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
                            rs.getTimestamp(9).toLocalDateTime(),
                            rs.getTimestamp(10).toLocalDateTime()
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
        String sql = "SELECT [id]\n"
                + "      ,[full_name]\n"
                + "      ,[gender]\n"
                + "      ,[email]\n"
                + "      ,[password]\n"
                + "      ,[role]\n"
                + "      ,[avatar_url]\n"
                + "      ,[created_at]\n"
                + "      ,[updated_at]\n"
                + "      ,[hash]\n"
                + "      ,[isValid]\n"
                + "  FROM [OnlineSoftSkillsLearningSystem].[dbo].[users]";
        List<User> users = new ArrayList();
        try (PreparedStatement pre = super.getConn().prepareStatement(sql); ResultSet rs = pre.executeQuery()) {
            while (rs.next()) {
                // Create a new User object inside the loop
                User user = new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        null,
                        //                        rs.getString(6),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getTimestamp(8).toLocalDateTime(),
                        rs.getTimestamp(9).toLocalDateTime()
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
                            null,
                            //                            rs.getString(6),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getTimestamp(8).toLocalDateTime(),
                            rs.getTimestamp(9).toLocalDateTime()
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
        String sql2 = "INSERT INTO [dbo].[users]\n"
                + "           ([full_name]\n"
                + "           ,[gender]\n"
                + "           ,[email]\n"
                + "           ,[password]\n"
                + "           ,[role]\n"
                + "           ,[avatar_url]\n"
                + "           ,[created_at]\n"
                + "           ,[updated_at]\n"
                + "           ,[hash]\n"
                + "           ,[isValid])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql2)) {
            pre.setString(1, user.getFullName());
            pre.setString(2, user.getGender());
            pre.setString(3, user.getEmail());
            pre.setString(4, user.getPassword());
//            pre.setString(5, user.getMobile());
            pre.setString(5, user.getRole());
            pre.setString(6, user.getAvatarUrl());
            pre.setTimestamp(7, Timestamp.valueOf(user.getCreatedAt()));
            pre.setTimestamp(8, Timestamp.valueOf(user.getUpdatedAt()));
            pre.setString(9, null);
            pre.setInt(10, 0);
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
            pre.setTimestamp(8, Timestamp.valueOf(user.getCreatedAt()));
            pre.setTimestamp(9, Timestamp.valueOf(user.getUpdatedAt()));
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
