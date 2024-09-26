package view;

import model.Course;
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
public class CourseDAO extends DBContext<Course> {

    public List<Course> getFeaturedCourses() {
        List<Course> courses = new Vector();
        String sql = "SELECT TOP 6 id, title, tag_line, description, category, list_price, sale_price, status " +
                     "FROM courses WHERE category = 'Soft Skills'";

        try (PreparedStatement pst = super.getConn().prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setTitle(rs.getString("title"));
                course.setTagLine(rs.getString("tag_line"));
                course.setDescription(rs.getString("description"));
                course.setCategory(rs.getString("category"));
                course.setListPrice(rs.getDouble("list_price"));
                course.setSalePrice(rs.getDouble("sale_price"));
                course.setStatus(rs.getString("status"));
                courses.add(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
    
    @Override
    public List<Course> select() {
        String sql = "SELECT [id], [title], [tag_line], [description], [category], [list_price], [sale_price], [owner_id], [status], [created_at], [updated_at] "
                + "FROM [dbo].[courses]";
        List<Course> courses = new Vector();
        try (PreparedStatement pre = super.getConn().prepareStatement(sql); ResultSet rs = pre.executeQuery()) {
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt(1));
                course.setTitle(rs.getString(2));
                course.setTagLine(rs.getString(3));
                course.setDescription(rs.getString(4));
                course.setCategory(rs.getString(5));
                course.setListPrice(rs.getDouble(6));
                course.setSalePrice(rs.getDouble(7));
                course.setOwnerId(rs.getInt(8));
                course.setStatus(rs.getString(9));
                course.setCreatedAt(rs.getTimestamp(10));
                course.setUpdatedAt(rs.getTimestamp(11));
                courses.add(course);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, "Error selecting courses", ex);
        }
        return courses;
    }

    @Override
    public Course select(int... id) {
        String sql = "SELECT [id], [title], [tag_line], [description], [category], [list_price], [sale_price], [owner_id], [status], [created_at], [updated_at] "
                + "FROM [dbo].[courses] WHERE [id] = ?";
        Course course = null;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]);
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    course = new Course();
                    course.setId(rs.getInt(1));
                    course.setTitle(rs.getString(2));
                    course.setTagLine(rs.getString(3));
                    course.setDescription(rs.getString(4));
                    course.setCategory(rs.getString(5));
                    course.setListPrice(rs.getDouble(6));
                    course.setSalePrice(rs.getDouble(7));
                    course.setOwnerId(rs.getInt(8));
                    course.setStatus(rs.getString(9));
                    course.setCreatedAt(rs.getTimestamp(10));
                    course.setUpdatedAt(rs.getTimestamp(11));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, "Error selecting course with id " + id, ex);
        }
        return course;
    }

    @Override
    public int insert(Course course) {
        String sql = "INSERT INTO [dbo].[courses] ([title], [tag_line], [description], [category], [list_price], [sale_price], [owner_id], [status], [created_at], [updated_at]) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setString(1, course.getTitle());
            pre.setString(2, course.getTagLine());
            pre.setString(3, course.getDescription());
            pre.setString(4, course.getCategory());
            pre.setDouble(5, course.getListPrice());
            pre.setDouble(6, course.getSalePrice());
            pre.setInt(7, course.getOwnerId());
            pre.setString(8, course.getStatus());
            pre.setTimestamp(9, new java.sql.Timestamp(course.getCreatedAt().getTime()));
            pre.setTimestamp(10, new java.sql.Timestamp(course.getUpdatedAt().getTime()));
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, "Error inserting course", ex);
        }
        return result;
    }

    @Override
    public int update(Course course) {
        String sql = "UPDATE [dbo].[courses] SET [title] = ?, [tag_line] = ?, [description] = ?, [category] = ?, [list_price] = ?, [sale_price] = ?, [owner_id] = ?, [status] = ?, [created_at] = ?, [updated_at] = ? "
                + "WHERE [id] = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setString(1, course.getTitle());
            pre.setString(2, course.getTagLine());
            pre.setString(3, course.getDescription());
            pre.setString(4, course.getCategory());
            pre.setDouble(5, course.getListPrice());
            pre.setDouble(6, course.getSalePrice());
            pre.setInt(7, course.getOwnerId());
            pre.setString(8, course.getStatus());
            pre.setTimestamp(9, new java.sql.Timestamp(course.getCreatedAt().getTime()));
            pre.setTimestamp(10, new java.sql.Timestamp(course.getUpdatedAt().getTime()));
            pre.setInt(11, course.getId());
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, "Error updating course", ex);
        }
        return result;
    }

    @Override
    public int delete(int... id) {
        String sql = "DELETE FROM [dbo].[courses] WHERE [id] = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]);
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, "Error deleting course with id " + id, ex);
        }
        return result;
    }
}
