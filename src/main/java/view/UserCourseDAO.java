package view;

import model.UserCourse;
import java.util.List;
import java.util.Vector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;

public class UserCourseDAO extends DBContext<UserCourse> {

    @Override
    public List<UserCourse> select() {
        String sql = "SELECT [user_id], [course_id], [role], [status], [created_at], [updated_at] FROM [dbo].[user_courses]";
        List<UserCourse> userCourses = new Vector();
        try (PreparedStatement pre = super.getConn().prepareStatement(sql); ResultSet rs = pre.executeQuery()) {
            while (rs.next()) {
                UserCourse userCourse = new UserCourse();
                userCourse.setUserId(rs.getInt(1));
                userCourse.setCourseId(rs.getInt(2));
                userCourse.setRole(rs.getString(3));
                userCourse.setStatus(rs.getString(4));
                userCourse.setCreatedAt(rs.getTimestamp(5));
                userCourse.setUpdatedAt(rs.getTimestamp(6));
                userCourses.add(userCourse);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCourseDAO.class.getName()).log(Level.SEVERE, "Error selecting user courses", ex);
        }
        return userCourses;
    }

    @Override
    public UserCourse select(int... id) {
        String sql = "SELECT [user_id], [course_id], [role], [status], [created_at], [updated_at] "
                + "FROM [dbo].[user_courses] WHERE user_id = ? AND course_id = ?";
        UserCourse userCourse = null;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]);
            pre.setInt(2, id[1]);
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    userCourse = new UserCourse();
                    userCourse.setUserId(rs.getInt(1));
                    userCourse.setCourseId(rs.getInt(2));
                    userCourse.setRole(rs.getString(3));
                    userCourse.setStatus(rs.getString(4));
                    userCourse.setCreatedAt(rs.getTimestamp(5));
                    userCourse.setUpdatedAt(rs.getTimestamp(6));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCourseDAO.class.getName()).log(Level.SEVERE, "Error selecting user course with user_id " + id[0] + " and course_id " + id[1], ex);
        }
        return userCourse;
    }

    @Override
    public int insert(UserCourse userCourse) {
        String sql = "INSERT INTO [dbo].[user_courses] ([user_id], [course_id], [role], [status], [created_at], [updated_at]) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, userCourse.getUserId());
            pre.setInt(2, userCourse.getCourseId());
            pre.setString(3, userCourse.getRole());
            pre.setString(4, userCourse.getStatus());
            pre.setTimestamp(5, new java.sql.Timestamp(userCourse.getCreatedAt().getTime()));
            pre.setTimestamp(6, new java.sql.Timestamp(userCourse.getUpdatedAt().getTime()));
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserCourseDAO.class.getName()).log(Level.SEVERE, "Error inserting user course", ex);
        }
        return result;
    }

    @Override
    public int update(UserCourse userCourse) {
        String sql = "UPDATE [dbo].[user_courses] "
                + "SET [role] = ?, [status] = ?, [created_at] = ?, [updated_at] = ? "
                + "WHERE [user_id] = ? AND [course_id] = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setString(1, userCourse.getRole());
            pre.setString(2, userCourse.getStatus());
            pre.setTimestamp(3, new java.sql.Timestamp(userCourse.getCreatedAt().getTime()));
            pre.setTimestamp(4, new java.sql.Timestamp(userCourse.getUpdatedAt().getTime()));
            pre.setInt(5, userCourse.getUserId());
            pre.setInt(6, userCourse.getCourseId());
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserCourseDAO.class.getName()).log(Level.SEVERE, "Error updating user course", ex);
        }
        return result;
    }

    @Override
    public int delete(int... id) {
        String sql = "DELETE FROM [dbo].[user_courses] WHERE user_id = ? AND course_id = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]);
            pre.setInt(2, id[1]);
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserCourseDAO.class.getName()).log(Level.SEVERE, "Error deleting user course with user_id " + id[0] + " and course_id " + id[1], ex);
        }
        return result;
    }
}
