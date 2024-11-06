package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CourseContent;

public class CourseContentDAO extends DBContext<CourseContent> {

    // Select content by course_id
    public List<CourseContent> selectByCourseId(int courseId) {
        List<CourseContent> contents = new ArrayList<>();
        String sql = "SELECT course_id, content, created_at, updated_at FROM course_content WHERE course_id = ?";
        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, courseId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                CourseContent content = new CourseContent();
                content.setCourseId(rs.getInt("course_id"));
                content.setContent(rs.getString("content"));
                content.setCreatedAt(rs.getTimestamp("created_at"));
                content.setUpdatedAt(rs.getTimestamp("updated_at"));
                contents.add(content);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseContentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contents;
    }

    // Insert new content
    @Override
    public int insert(CourseContent content) {
        String sql = "INSERT INTO course_content (course_id, content, created_at, updated_at) VALUES (?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, content.getCourseId());
            pst.setString(2, content.getContent());
            return pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseContentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    // Update content
    @Override
    public int update(CourseContent content) {
        String sql = "UPDATE course_content SET content = ?, updated_at = CURRENT_TIMESTAMP WHERE course_id = ?";
        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, content.getContent());
            pst.setInt(2, content.getCourseId());
            return pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseContentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    // Delete content by course_id
    @Override
    public int delete(int... ids) {
        String sql = "DELETE FROM course_content WHERE course_id = ?";
        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, ids[0]);
            return pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseContentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public List<CourseContent> select() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CourseContent select(int... ids) {
        CourseContent content = new CourseContent();
        String sql = "SELECT course_id, content, created_at, updated_at FROM course_content WHERE course_id = ?";
        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, ids[0]);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                content.setCourseId(rs.getInt("course_id"));
                content.setContent(rs.getString("content"));
                content.setCreatedAt(rs.getTimestamp("created_at"));
                content.setUpdatedAt(rs.getTimestamp("updated_at"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseContentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return content;
    }

    // Select 1 content by course_id
    public CourseContent getCourseContentById(int courseId) {
        CourseContent content = null;
        String sql = "SELECT course_id, content, created_at, updated_at FROM course_content WHERE course_id = ?";
        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, courseId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                content = new CourseContent();
                content.setCourseId(rs.getInt("course_id"));
                content.setContent(rs.getString("content"));
                content.setCreatedAt(rs.getTimestamp("created_at"));
                content.setUpdatedAt(rs.getTimestamp("updated_at"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseContentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return content;
    }

}
