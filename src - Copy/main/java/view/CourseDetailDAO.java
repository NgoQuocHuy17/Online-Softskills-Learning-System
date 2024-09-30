package view;

import model.CourseDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDetailDAO extends DBContext<CourseDetail> {

    @Override
    public List<CourseDetail> select() {
        List<CourseDetail> courseDetails = new ArrayList<>();
        String sql = "SELECT * FROM course_details"; // Câu lệnh truy vấn

        try (Connection conn = getConn(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int courseId = rs.getInt("courseId");
                String sectionTitle = rs.getString("sectionTitle");
                String content = rs.getString("content");
                String imagePath = rs.getString("imagePath");
                String videoUrl = rs.getString("videoUrl");
                Date createdAt = rs.getDate("createdAt");
                Date updatedAt = rs.getDate("updatedAt");

                CourseDetail detail = new CourseDetail(courseId, sectionTitle, content, imagePath, videoUrl, createdAt, updatedAt);
                courseDetails.add(detail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseDetails;
    }

    @Override
    public CourseDetail select(int... id) {
        String sql = "SELECT * FROM course_details WHERE courseId = ?";
        CourseDetail courseDetail = null;

        try (Connection conn = getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id[0]);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int courseId = rs.getInt("courseId");
                    String sectionTitle = rs.getString("sectionTitle");
                    String content = rs.getString("content");
                    String imagePath = rs.getString("imagePath");
                    String videoUrl = rs.getString("videoUrl");
                    Date createdAt = rs.getDate("createdAt");
                    Date updatedAt = rs.getDate("updatedAt");

                    courseDetail = new CourseDetail(courseId, sectionTitle, content, imagePath, videoUrl, createdAt, updatedAt);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseDetail;
    }

    // Các phương thức insert, update, delete có thể được thêm vào nếu cần
    @Override
    public int insert(CourseDetail obj) { return 0; }

    @Override
    public int update(CourseDetail obj) { return 0; }

    @Override
    public int delete(int... id) { return 0; }
}
