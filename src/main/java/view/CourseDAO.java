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

   public boolean addNewCourse(Course course) {
    String sql = "INSERT INTO courses (title, description, category, owner_id, is_sponsored, status) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

    try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
        pst.setString(1, course.getTitle());
        pst.setString(2, course.getDescription());
        pst.setString(3, course.getCategory());
        pst.setInt(4, course.getOwnerId());
        pst.setBoolean(5, course.isSponsored());
        pst.setString(6, course.getStatus());
        
        int i = pst.executeUpdate();
        return i > 0; // Return true if insertion was successful
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT id, title, tag_line, description, category, basic_package_price, advanced_package_price, owner_id, is_sponsored, status, created_at, updated_at "
                + "FROM courses";  // Removed WHERE clause for sponsored courses

        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
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

    public List<String> getAllStatuses() {
        List<String> statuses = new ArrayList<>();
        String sql = "SELECT DISTINCT status FROM courses";

        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                String status = rs.getString("status");
                statuses.add(status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statuses;
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

    // Phương thức tìm kiếm khóa học theo tiêu đề
    public List<Course> getCoursesByTitle(String title) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT id, title, tag_line, description, category, basic_package_price, advanced_package_price, owner_id, is_sponsored, status, created_at, updated_at "
                + "FROM courses WHERE title LIKE ? AND status = 'Published'"
                + "ORDER BY is_sponsored DESC, updated_at DESC";

        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, "%" + title + "%");
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

    public List<Course> getSubjectByTitle(String title) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT id, title, tag_line, description, category, basic_package_price, advanced_package_price, owner_id, is_sponsored, status, created_at, updated_at "
                + "FROM courses WHERE title LIKE ? "
                + "ORDER BY is_sponsored DESC, updated_at DESC";

        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, "%" + title + "%");
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

    // Phương thức trả về danh sách tiêu đề khóa học cho autocomplete
    public List<String> getCourseTitlesByTerm(String term) {
        List<String> titles = new ArrayList<>();
        String sql = "SELECT title FROM courses WHERE title LIKE ? AND status = 'Published'"
                + "ORDER BY is_sponsored DESC, updated_at DESC";

        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, "%" + term + "%");
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    titles.add(rs.getString("title"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return titles;
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

    public List<Course> getCoursesByTitleAndCategory(String title, String category, int page, int pageSize) {
        List<Course> courses = new ArrayList<>();
        String sql;

        if ("All".equalsIgnoreCase(category)) {
            sql = "SELECT id, title, tag_line, description, category, basic_package_price, advanced_package_price, owner_id, is_sponsored, status, created_at, updated_at "
                    + "FROM courses WHERE title LIKE ? AND status = 'Published' "
                    + "ORDER BY is_sponsored DESC, updated_at DESC "
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        } else {
            sql = "SELECT id, title, tag_line, description, category, basic_package_price, advanced_package_price, owner_id, is_sponsored, status, created_at, updated_at "
                    + "FROM courses WHERE title LIKE ? AND category = ? AND status = 'Published' "
                    + "ORDER BY is_sponsored DESC, updated_at DESC "
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        }

        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, "%" + title + "%");

            if ("All".equalsIgnoreCase(category)) {
                pst.setInt(2, (page - 1) * pageSize);
                pst.setInt(3, pageSize);
            } else {
                pst.setString(2, category);
                pst.setInt(3, (page - 1) * pageSize);
                pst.setInt(4, pageSize);
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
public Integer getIdByTitle(String title) {
    Integer courseId = null; // Initialize to null in case no course is found
    String sql = "SELECT id FROM courses WHERE title = ?";

    try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
        pst.setString(1, title); // Set the title in the prepared statement
        try (ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                courseId = rs.getInt("id"); // Get the course ID
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return courseId; // Return the course ID or null if not found
}

    public int getTotalCoursesByTitleAndCategory(String title, String category) {
        int totalCourses = 0;
        String sql;

        if ("All".equalsIgnoreCase(category)) {
            sql = "SELECT COUNT(*) AS total FROM courses WHERE title LIKE ? AND status = 'Published'";
        } else {
            sql = "SELECT COUNT(*) AS total FROM courses WHERE title LIKE ? AND category = ? AND status = 'Published'";
        }

        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, "%" + title + "%");

            if (!"All".equalsIgnoreCase(category)) {
                pst.setString(2, category);
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
