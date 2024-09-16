package view;

import model.User;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Minh
 */

public class UserDAO extends DBContext {

    public User getUser(String email) {
        String sql = "SELECT [id]\n"
                + "      ,[full_name]\n"
                + "      ,[gender]\n"
                + "      ,[password_hash]\n"
                + "      ,[role]\n"
                + "      ,[avatar_url]\n"
                + "      ,[created_at]\n"
                + "      ,[updated_at]\n"
                + "  FROM [SoftSkillsOnlineLearningSystem].[dbo].[users] where email = ?";
        User user = new User();
        try {
            PreparedStatement pre = super.getConn().prepareStatement(sql);
            pre.setString(1, email);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt(1));
                user.setFullName(rs.getString(2));
                user.setGender(rs.getString(3));
                user.setEmail(email);
                user.setPasswordHash(rs.getString(4));
                user.setRole(rs.getString(5));
                user.setAvatarUrl(rs.getString(6));
                user.setCreatedAt(rs.getDate(7));
                user.setUpdatedAt(rs.getDate(8));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
}
