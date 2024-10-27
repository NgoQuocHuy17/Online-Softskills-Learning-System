/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

/**
 *
 * @author daihi
 */
public class CourseMediaDAO extends DBContext<CourseMedia> {

    public List<CourseMedia> selectByCourseId(int courseId) {
        List<CourseMedia> medias = new ArrayList<>();
        String sql = "SELECT id, course_id, media_type, file_name, title, content, display_order, is_thumbnail "
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
                media.setContent(rs.getString("content"));  // Đảm bảo content được lấy
                media.setDisplayOrder(rs.getInt("display_order"));
                media.setIsThumbnail(rs.getBoolean("is_thumbnail"));
                medias.add(media);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseMediaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return medias;
    }

    // Implement other CRUD methods
    @Override
    public List<CourseMedia> select() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CourseMedia select(int... id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(CourseMedia obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(CourseMedia obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(int... id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
