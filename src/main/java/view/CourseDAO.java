package view;

import model.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    private Connection connection;

    public CourseDAO() {
        this.connection = new DBContext().getConn();
    }

    // Assuming a category named "Featured" represents featured courses
    public List<Course> getFeaturedCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT TOP 6 id, title, tag_line, description, category, list_price, sale_price, status " +
                     "FROM courses WHERE category = 'Soft Skills'";

        try (PreparedStatement pst = connection.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

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
}
