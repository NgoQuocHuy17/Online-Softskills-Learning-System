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

    @Override
    public List<User> select() {
        String sql = "Select * from users";
        List<User> users = new Vector();
        User user = new User();
        try {
            PreparedStatement pre = super.getConn().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt(1));
                user.setFullName(rs.getString(2));
                user.setGender(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setPasswordHash(rs.getString(5));
                user.setRole(rs.getString(6));
                user.setAvatarUrl(rs.getString(7));
                user.setCreatedAt(rs.getDate(8));
                user.setUpdatedAt(rs.getDate(9));
                users.add(user);
            }
        } catch (Exception e) {
        }
        return users;
    }

    @Override
    public User select(int... id) {
        String sql = "SELECT TOP (1000) [id]\n"
                + "      ,[full_name]\n"
                + "      ,[gender]\n"
                + "      ,[email]\n"
                + "      ,[password_hash]\n"
                + "      ,[role]\n"
                + "      ,[avatar_url]\n"
                + "      ,[created_at]\n"
                + "      ,[updated_at]\n"
                + "  FROM [SoftSkillsOnlineLearningSystem].[dbo].[users] where id = ?";
        User user = new User();
        try {
            PreparedStatement pre = super.getConn().prepareStatement(sql);
            pre.setInt(1, id[0]);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                user.setId(id[0]);
                user.setFullName(rs.getString(1));
                user.setGender(rs.getString(2));
                user.setEmail(rs.getString(4));
                user.setPasswordHash(rs.getString(5));
                user.setRole(rs.getString(6));
                user.setAvatarUrl(rs.getString(7));
                user.setCreatedAt(rs.getDate(8));
                user.setUpdatedAt(rs.getDate(9));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @Override
    public int insert(User user) {

        String sql = "INSERT INTO [dbo].[users]\n"
                + "           ([full_name]\n"
                + "           ,[gender]\n"
                + "           ,[email]\n"
                + "           ,[password_hash]\n"
                + "           ,[role]\n"
                + "           ,[avatar_url]\n"
                + "           ,[created_at]\n"
                + "           ,[updated_at])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?, ?, ?, ?)";
        int result = 0;
        try {
            PreparedStatement pre = super.getConn().prepareStatement(sql);
            pre.setString(1, user.getFullName());
            pre.setString(2, user.getGender());
            pre.setString(3, user.getEmail());
            pre.setString(4, user.getPasswordHash());
            pre.setString(5, user.getRole());
            pre.setString(6, user.getAvatarUrl());
            pre.setDate(7, (java.sql.Date) user.getCreatedAt());
            pre.setDate(8, (java.sql.Date) user.getUpdatedAt());
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(User user) {
        String sql = "UPDATE [dbo].[users]\n"
                + "   SET [full_name] = ?\n"
                + "      ,[gender] = ?\n"
                + "      ,[email] = ?\n"
                + "      ,[password_hash] = ?\n"
                + "      ,[role] = ?\n"
                + "      ,[avatar_url] = ?\n"
                + "      ,[created_at] = ?\n"
                + "      ,[updated_at] = ?\n"
                + " WHERE id = ?";
        int result = 0;
        try {
            PreparedStatement pre = super.getConn().prepareStatement(sql);
            pre.setInt(9, user.getId());
            pre.setString(1, user.getFullName());
            pre.setString(2, user.getGender());
            pre.setString(3, user.getEmail());
            pre.setString(4, user.getPasswordHash());
            pre.setString(5, user.getRole());
            pre.setString(6, user.getAvatarUrl());
            pre.setDate(7, (java.sql.Date) user.getCreatedAt());
            pre.setDate(8, (java.sql.Date) user.getUpdatedAt());
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(int... id) {
        String sql = "DELETE FROM [dbo].[users]\n"
                + "      WHERE id = ?";
        int result = 0;
        try {
            PreparedStatement pre = super.getConn().prepareStatement(sql);
            pre.setInt(1, id[0]);
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
