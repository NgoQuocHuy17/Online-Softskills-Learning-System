// File: CourseDAO.java
package view;

import model.Course;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO extends DBContext<Course> {

    public CourseDAO() {
        super();
    }

    public List<Course> getFeaturedCourses(int size) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT TOP (?) id, title, tag_line, description, category, basic_package_price, advanced_package_price, owner_id, is_sponsored, status, created_at, updated_at "
                + "FROM courses WHERE is_sponsored = '1'";

        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, size);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Course course = new Course();
                    course.setId(rs.getInt("id"));
                    course.setTitle(rs.getString("title"));
                    course.setTagLine(rs.getString("tag_line"));
                    course.setDescription(rs.getString("description"));
                    course.setCategory(rs.getString("category"));
                    course.setBasicPackagePrice(rs.getBigDecimal("basic_package_price"));
                    course.setAdvancedPackagePrice(rs.getBigDecimal("advanced_package_price"));
                    course.setOwnerId(rs.getInt("owner_id"));
                    course.setSponsored(rs.getBoolean("is_sponsored"));
                    course.setStatus(rs.getString("status"));
                    course.setCreatedAt(rs.getTimestamp("created_at"));
                    course.setUpdatedAt(rs.getTimestamp("updated_at"));
                    courses.add(course);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        String sql = "SELECT DISTINCT category FROM courses";

        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                String category = rs.getString("category");
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    public List<Course> getCoursesByCategory(String category, int page, int pageSize) {
        List<Course> courses = new ArrayList<>();
        String sql;

        if ("All".equalsIgnoreCase(category)) {
            sql = "SELECT id, title, tag_line, description, category, basic_package_price, advanced_package_price, owner_id, is_sponsored, status, created_at, updated_at "
                    + "FROM courses WHERE status = 'Published' "
                    + "ORDER BY is_sponsored DESC, updated_at DESC "
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        } else {
            sql = "SELECT id, title, tag_line, description, category, basic_package_price, advanced_package_price, owner_id, is_sponsored, status, created_at, updated_at "
                    + "FROM courses WHERE status = 'Published' AND category = ? "
                    + "ORDER BY is_sponsored DESC, updated_at DESC "
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
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
                    course.setBasicPackagePrice(rs.getBigDecimal("basic_package_price"));
                    course.setAdvancedPackagePrice(rs.getBigDecimal("advanced_package_price"));
                    course.setOwnerId(rs.getInt("owner_id"));
                    course.setSponsored(rs.getBoolean("is_sponsored"));
                    course.setStatus(rs.getString("status"));
                    course.setCreatedAt(rs.getTimestamp("created_at"));
                    course.setUpdatedAt(rs.getTimestamp("updated_at"));
                    courses.add(course);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public int getTotalCoursesByCategory(String category) {
        int totalCourses = 0;
        String sql;
        if ("All".equalsIgnoreCase(category)) {
            sql = "SELECT COUNT(*) AS total FROM courses WHERE status = 'Published'";
        } else {
            sql = "SELECT COUNT(*) AS total FROM courses WHERE status = 'Published' AND category = ?";
        }

        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            if (!"All".equalsIgnoreCase(category)) {
                pst.setString(1, category);
            }
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    totalCourses = rs.getInt("total");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalCourses;
    }

    public Course getCourseById(int id) {
        Course course = null;
        String sql = "SELECT id, title, tag_line, description, category, basic_package_price, advanced_package_price, owner_id, is_sponsored, status, created_at, updated_at "
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
                    course.setBasicPackagePrice(rs.getBigDecimal("basic_package_price"));
                    course.setAdvancedPackagePrice(rs.getBigDecimal("advanced_package_price"));
                    course.setOwnerId(rs.getInt("owner_id"));
                    course.setSponsored(rs.getBoolean("is_sponsored"));
                    course.setStatus(rs.getString("status"));
                    course.setCreatedAt(rs.getTimestamp("created_at"));
                    course.setUpdatedAt(rs.getTimestamp("updated_at"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }

    @Override
    public List<Course> select() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Course select(int... id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(Course obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(Course obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(int... id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
