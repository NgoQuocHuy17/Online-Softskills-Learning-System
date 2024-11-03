/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.ArrayList;
import java.util.List;
import model.UserVideo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.UserMedia;

public class UserMediaDAO extends DBContext<UserVideo> {

    public void insertMedia(int userId, String mediaType, byte[] mediaData) {
        String query = "INSERT INTO user_media (user_id, media_type, media_data) VALUES (?, ?, ?)";

        try (Connection connection = getConn(); PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setString(2, mediaType);
            ps.setBytes(3, mediaData);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<UserMedia> getImagesByUserId(int userId) {
        List<UserMedia> images = new ArrayList<>();
        String query = "SELECT * FROM user_media WHERE user_id = ? AND media_type LIKE 'image/%'";

        try (Connection connection = getConn(); PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UserMedia media = new UserMedia();
                media.setId(rs.getInt("id"));
                media.setUserId(rs.getInt("user_id"));
                media.setMediaType(rs.getString("media_type"));
                media.setMediaData(Base64.getEncoder().encodeToString(rs.getBytes("media_data"))); // Mã hóa dữ liệu thành Base64
                images.add(media);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return images;
    }

    public List<UserMedia> getVideosByUserId(int userId) {
        List<UserMedia> videos = new ArrayList<>();
        String query = "SELECT * FROM user_media WHERE user_id = ? AND media_type LIKE 'video/%'";

        try (Connection connection = getConn(); PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UserMedia media = new UserMedia();
                media.setId(rs.getInt("id"));
                media.setUserId(rs.getInt("user_id"));
                media.setMediaType(rs.getString("media_type"));
                media.setMediaData(Base64.getEncoder().encodeToString(rs.getBytes("media_data"))); // Mã hóa dữ liệu thành Base64
                videos.add(media);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return videos;
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

    @Override
    public int insert(UserVideo obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(UserVideo obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<UserVideo> select() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public UserVideo select(int... id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
