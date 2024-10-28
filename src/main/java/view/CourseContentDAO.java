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

    // Lấy content theo courseId
    public List<CourseContent> selectByCourseId(int courseId) {
        List<CourseContent> contents = new ArrayList<>();
        String sql = "SELECT id, course_id, content, created_at, updated_at FROM course_content WHERE course_id = ?";
        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, courseId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                CourseContent content = new CourseContent();
                content.setId(rs.getInt("id"));
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

    // Thêm content mới
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

    // Cập nhật content
    @Override
    public int update(CourseContent content) {
        String sql = "UPDATE course_content SET content = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, content.getContent());
            pst.setInt(2, content.getId());
            return pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseContentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public int delete(int... ids) {
        String sql = "DELETE FROM course_content WHERE id = ?";
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
        String sql = "SELECT id, course_id, content, created_at, updated_at FROM course_content WHERE id = ?";
        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, ids[0]);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                content.setId(rs.getInt("id"));
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
