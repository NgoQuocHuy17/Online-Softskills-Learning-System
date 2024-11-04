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
import model.Lesson;

/**
 *
 * @author ngoqu
 */
public class LessonDAO extends DBContext<Lesson> {

    public List<Lesson> getLessonsByCourseId(int courseId) {
        List<Lesson> lessons = new ArrayList<>();
        String sql = "SELECT LessonID, CourseID, Title, Description, CreatedDate, LastUpdatedDate, Status FROM lesson WHERE CourseID = ?";

        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, courseId); // Set courseId parameter in the query
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Lesson lesson = new Lesson();
                    lesson.setId(rs.getInt("LessonID"));
                    lesson.setCourseID(rs.getInt("CourseID"));
                    lesson.setTitle(rs.getString("Title"));
                    lesson.setDescription(rs.getString("Description"));

                    // Set the new fields
                    lesson.setCreatedDate(rs.getTimestamp("CreatedDate")); // Assuming you are storing it as a Timestamp
                    lesson.setLastUpdatedDate(rs.getTimestamp("LastUpdatedDate")); // Assuming you are storing it as a Timestamp
                    lesson.setStatus(rs.getBoolean("Status")); // Status is a BIT in SQL, maps to boolean in Java

                    lessons.add(lesson);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessons;
    }

    public Lesson getLessonById(int lessonId) {
        String query = "SELECT * FROM lesson WHERE LessonID = ?";
        Lesson lesson = null;

        try (PreparedStatement ps = super.getConn().prepareStatement(query)) {
            ps.setInt(1, lessonId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    lesson = new Lesson();
                    lesson.setId(rs.getInt("LessonID"));
                    lesson.setCourseID(rs.getInt("CourseID"));
                    lesson.setTitle(rs.getString("Title"));
                    lesson.setDescription(rs.getString("Description"));
                    lesson.setCreatedDate(rs.getDate("CreatedDate"));  // Assuming SQL DATE type
                    lesson.setLastUpdatedDate(rs.getDate("LastUpdatedDate"));  // Assuming SQL DATE type
                    lesson.setStatus(rs.getBoolean("Status"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lesson;
    }

    public boolean updateLesson(Lesson lesson) {
        String query = "UPDATE lesson SET "
                + "CourseID = ?, "
                + "Title = ?, "
                + "Description = ?, "
                + "CreatedDate = ?, "
                + "LastUpdatedDate = ?, "
                + "Status = ? "
                + "WHERE LessonID = ?";

        try (PreparedStatement ps = super.getConn().prepareStatement(query)) {
            ps.setInt(1, lesson.getCourseID());
            ps.setString(2, lesson.getTitle());
            ps.setString(3, lesson.getDescription());
            ps.setDate(4, new java.sql.Date(lesson.getCreatedDate().getTime())); // convert java.util.Date to java.sql.Date
            ps.setDate(5, new java.sql.Date(lesson.getLastUpdatedDate().getTime())); // convert java.util.Date to java.sql.Date
            ps.setBoolean(6, lesson.isStatus());
            ps.setInt(7, lesson.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Lesson> select() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Lesson select(int... id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(Lesson obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(Lesson obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(int... id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
