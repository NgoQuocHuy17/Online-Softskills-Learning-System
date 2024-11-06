package view;

import java.util.ArrayList;
import java.util.List;
import model.UserVideo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserVideoDAO extends DBContext<UserVideo> {

    // Phương thức lấy danh sách video của người dùng theo userId
    public List<UserVideo> getUserVideo(int userId) {
        List<UserVideo> userVideos = new ArrayList<>();
        String query = "SELECT * FROM user_videos WHERE user_id = ?";

        try (Connection conn = getConn(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    UserVideo userVideo = new UserVideo(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getString("video_url")
                    );
                    userVideos.add(userVideo);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userVideos;
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
