package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CourseMedia;

public class CourseMediaDAO extends DBContext<CourseMedia> {

    // Lấy danh sách media theo courseId
    public List<CourseMedia> selectByCourseId(int courseId) {
        List<CourseMedia> medias = new ArrayList<>();
        String sql = "SELECT id, course_id, media_type, file_name, title, display_order "
                + "FROM course_media WHERE course_id = ? ORDER BY display_order";
        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, courseId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                CourseMedia media = new CourseMedia();
                media.setId(rs.getInt("id"));
                media.setCourseId(rs.getInt("course_id"));
                media.setMediaType(rs.getString("media_type"));
                media.setFileName(rs.getString("file_name"));
                media.setTitle(rs.getString("title"));
                media.setDisplayOrder(rs.getInt("display_order"));
                medias.add(media);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseMediaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return medias;
    }

    // Thêm mới media
    @Override
    public int insert(CourseMedia media) {
        String sql = "INSERT INTO course_media (course_id, media_type, file_name, title, display_order) "
                + "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, media.getCourseId());
            pst.setString(2, media.getMediaType());
            pst.setString(3, media.getFileName());
            pst.setString(4, media.getTitle());
            pst.setInt(5, media.getDisplayOrder());
            return pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseMediaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    // Cập nhật media
    @Override
    public int update(CourseMedia media) {
        String sql = "UPDATE course_media SET media_type = ?, file_name = ?, title = ?, display_order = ? "
                + "WHERE id = ?";
        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, media.getMediaType());
            pst.setString(2, media.getFileName());
            pst.setString(3, media.getTitle());
            pst.setInt(4, media.getDisplayOrder());
            pst.setInt(5, media.getId());
            return pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseMediaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void updateDisplayOrder(int mediaId, boolean moveUp) {
        String sql = moveUp
                ? "UPDATE course_media SET display_order = display_order - 1 WHERE id = ?"
                : "UPDATE course_media SET display_order = display_order + 1 WHERE id = ?";

        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, mediaId);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseMediaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int delete(int... ids) {
        String sql = "DELETE FROM course_media WHERE id = ?";
        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, ids[0]);
            return pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseMediaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getMaxDisplayOrder(int courseId) {
        int maxOrder = 0;
        String sql = "SELECT MAX(display_order) FROM course_media WHERE course_id = ?";
        try (Connection conn = getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maxOrder = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxOrder;
    }

    @Override
    public List<CourseMedia> select() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CourseMedia select(int... ids) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
