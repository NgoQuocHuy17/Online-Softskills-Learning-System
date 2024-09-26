package view;

import model.Course;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO extends DBContext<Course> {

    // Kết nối đến cơ sở dữ liệu
    public CourseDAO() {
        super();
    }

    // Lấy các khóa học nổi bật (ví dụ: lấy 6 khóa học thuộc category 'Soft Skills')
    public List<Course> getFeaturedCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT TOP 6 id, title, tag_line, description, category, list_price, sale_price, status " +
                     "FROM courses WHERE category = 'Soft Skills'";

        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setTitle(rs.getString("title"));
                course.setTagLine(rs.getString("tag_line"));
                course.setDescription(rs.getString("description"));
                course.setCategory(rs.getString("category"));
                course.setListPrice(rs.getBigDecimal("list_price"));
                course.setSalePrice(rs.getBigDecimal("sale_price"));
                course.setStatus(rs.getString("status"));
                courses.add(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    // Ghi đè phương thức select từ DBContext nhưng chưa hỗ trợ
    @Override
    public List<Course> select() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Ghi đè phương thức select theo id nhưng chưa hỗ trợ
    @Override
    public Course select(int... id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Ghi đè phương thức insert nhưng chưa hỗ trợ
    @Override
    public int insert(Course course) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Ghi đè phương thức update nhưng chưa hỗ trợ
    @Override
    public int update(Course course) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Ghi đè phương thức delete nhưng chưa hỗ trợ
    @Override
    public int delete(int... id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
