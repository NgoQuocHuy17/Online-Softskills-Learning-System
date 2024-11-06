package view;

import java.sql.Connection;
import model.Registration;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import model.RegistrationMedia;

public class RegistrationMediaDAO extends DBContext<Registration> {

    // Thêm media cho đăng ký
    public void insertMedia(int registrationId, String mediaType, byte[] mediaData, String note) {
        String query = "INSERT INTO registration_media (registration_id, media_type, media_data, note) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConn(); PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, registrationId);
            ps.setString(2, mediaType);
            ps.setBytes(3, mediaData);
            ps.setString(4, note);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Xóa media dựa trên ID
    public boolean deleteMediaById(int mediaId) {
        String query = "DELETE FROM registration_media WHERE id = ?";

        try (Connection connection = getConn(); PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, mediaId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy danh sách ảnh dựa trên ID đăng ký
    public List<RegistrationMedia> getImagesByRegistrationId(int registrationId) {
        List<RegistrationMedia> images = new ArrayList<>();
        String query = "SELECT * FROM registration_media WHERE registration_id = ? AND media_type LIKE 'image/%'";

        try (Connection connection = getConn(); PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, registrationId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                RegistrationMedia media = new RegistrationMedia();
                media.setId(rs.getInt("id"));
                media.setRegistrationId(rs.getInt("registration_id"));
                media.setMediaType(rs.getString("media_type"));
                media.setMediaData(Base64.getEncoder().encodeToString(rs.getBytes("media_data"))); // Mã hóa dữ liệu thành Base64
                media.setNote(rs.getString("note"));
                images.add(media);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return images;
    }

    // Lấy danh sách video dựa trên ID đăng ký
    public List<RegistrationMedia> getVideosByRegistrationId(int registrationId) {
        List<RegistrationMedia> videos = new ArrayList<>();
        String query = "SELECT * FROM registration_media WHERE registration_id = ? AND media_type LIKE 'video/%'";

        try (Connection connection = getConn(); PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, registrationId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                RegistrationMedia media = new RegistrationMedia();
                media.setId(rs.getInt("id"));
                media.setRegistrationId(rs.getInt("registration_id"));
                media.setMediaType(rs.getString("media_type"));
                media.setMediaData(Base64.getEncoder().encodeToString(rs.getBytes("media_data"))); // Mã hóa dữ liệu thành Base64
                media.setNote(rs.getString("note"));
                videos.add(media);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return videos;
    }

    @Override
    public List<Registration> select() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Registration select(int... id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(Registration obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(Registration obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(int... id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
