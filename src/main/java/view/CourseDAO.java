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
        String sql = "SELECT TOP 6 id, title, tag_line, description, category, list_price, sale_price, status "
                + "FROM courses WHERE category = 'Soft Skills'";

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

    public List<Course> getCoursesByCategory(String category, int page, int pageSize) {
        List<Course> courses = new ArrayList<>();
        String sql;

        if ("All".equalsIgnoreCase(category)) {
            sql = "SELECT id, title, tag_line, description, category, list_price, sale_price, status "
                    + "FROM courses ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        } else {
            sql = "SELECT id, title, tag_line, description, category, list_price, sale_price, status "
                    + "FROM courses WHERE category = ? ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        }

        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            if ("All".equalsIgnoreCase(category)) {
                pst.setInt(1, (page - 1) * pageSize);
                pst.setInt(2, pageSize);
            } else {
                pst.setString(1, category);
                pst.setInt(2, (page - 1) * pageSize);
                pst.setInt(3, pageSize);
            }

            try (ResultSet rs = pst.executeQuery()) {
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public int getTotalCoursesByCategory(String category) {
        String sql;

        if ("All".equalsIgnoreCase(category)) {
            sql = "SELECT COUNT(*) FROM courses";
        } else {
            sql = "SELECT COUNT(*) FROM courses WHERE category = ?";
        }

        int totalCourses = 0;

        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            if (!"All".equalsIgnoreCase(category)) {
                pst.setString(1, category);
            }

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    totalCourses = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalCourses;
    }

    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        String sql = "SELECT DISTINCT category FROM courses";

        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                categories.add(rs.getString("category"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public Course getCourseById(int id) {
        Course course = null;
        String sql = "SELECT id, title, tag_line, description, category, list_price, sale_price, status "
                + "FROM courses WHERE id = ?";
        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    course = new Course();
                    course.setId(rs.getInt("id"));
                    course.setTitle(rs.getString("title"));
                    course.setTagLine(rs.getString("tag_line"));
                    course.setDescription(rs.getString("description"));
                    course.setCategory(rs.getString("category"));
                    course.setListPrice(rs.getBigDecimal("list_price"));
                    course.setSalePrice(rs.getBigDecimal("sale_price"));
                    course.setStatus(rs.getString("status"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }

    // Method to search courses by title for autocomplete (returns Course objects)
    public List<Course> searchCoursesByTitle(String query) {
        List<Course> courses = new ArrayList<>();

        String sql = "SELECT TOP 10 id, title FROM [SoftSkillsOnlineLearningSystem].[dbo].[courses] "
                + "WHERE title LIKE ?";

        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, "%" + query + "%");  // Wildcard search

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Course course = new Course();
                    course.setId(rs.getInt("id"));
                    course.setTitle(rs.getString("title"));
                    courses.add(course);
                }
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
