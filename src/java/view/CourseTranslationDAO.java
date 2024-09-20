package view;

import model.CourseTranslation;
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
public class CourseTranslationDAO extends DBContext<CourseTranslation> {

    @Override
    public List<CourseTranslation> select() {
        String sql = "SELECT [id], [course_id], [language_code], [title], [description] FROM [dbo].[course_translations]";
        List<CourseTranslation> courseTranslations = new Vector<>();
        try (PreparedStatement pre = super.getConn().prepareStatement(sql); ResultSet rs = pre.executeQuery()) {
            while (rs.next()) {
                CourseTranslation courseTranslation = new CourseTranslation();
                courseTranslation.setId(rs.getInt(1));
                courseTranslation.setCourseId(rs.getInt(2));
                courseTranslation.setLanguageCode(rs.getString(3));
                courseTranslation.setTitle(rs.getString(4));
                courseTranslation.setDescription(rs.getString(5));
                courseTranslations.add(courseTranslation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseTranslationDAO.class.getName()).log(Level.SEVERE, "Error selecting course translations", ex);
        }
        return courseTranslations;
    }

    @Override
    public CourseTranslation select(int... id) {
        String sql = "SELECT [id], [course_id], [language_code], [title], [description] FROM [dbo].[course_translations] WHERE [id] = ?";
        CourseTranslation courseTranslation = null;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]);
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    courseTranslation = new CourseTranslation();
                    courseTranslation.setId(rs.getInt(1));
                    courseTranslation.setCourseId(rs.getInt(2));
                    courseTranslation.setLanguageCode(rs.getString(3));
                    courseTranslation.setTitle(rs.getString(4));
                    courseTranslation.setDescription(rs.getString(5));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseTranslationDAO.class.getName()).log(Level.SEVERE, "Error selecting course translation with id " + id, ex);
        }
        return courseTranslation;
    }

    @Override
    public int insert(CourseTranslation courseTranslation) {
        String sql = "INSERT INTO [dbo].[course_translations] ([course_id], [language_code], [title], [description]) VALUES (?, ?, ?, ?)";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, courseTranslation.getCourseId());
            pre.setString(2, courseTranslation.getLanguageCode());
            pre.setString(3, courseTranslation.getTitle());
            pre.setString(4, courseTranslation.getDescription());
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseTranslationDAO.class.getName()).log(Level.SEVERE, "Error inserting course translation", ex);
        }
        return result;
    }

    @Override
    public int update(CourseTranslation courseTranslation) {
        String sql = "UPDATE [dbo].[course_translations] SET [course_id] = ?, [language_code] = ?, [title] = ?, [description] = ? WHERE [id] = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, courseTranslation.getCourseId());
            pre.setString(2, courseTranslation.getLanguageCode());
            pre.setString(3, courseTranslation.getTitle());
            pre.setString(4, courseTranslation.getDescription());
            pre.setInt(5, courseTranslation.getId());
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseTranslationDAO.class.getName()).log(Level.SEVERE, "Error updating course translation", ex);
        }
        return result;
    }

    @Override
    public int delete(int... id) {
        String sql = "DELETE FROM [dbo].[course_translations] WHERE [id] = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]);
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseTranslationDAO.class.getName()).log(Level.SEVERE, "Error deleting course translation with id " + id, ex);
        }
        return result;
    }

}
