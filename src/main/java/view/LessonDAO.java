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
public class LessonDAO extends DBContext<Lesson>{
    
 public List<Lesson> getAllLessons() {
    List<Lesson> lessons = new ArrayList<>();
    String sql = "SELECT LessonID, CourseID, Title, Description, CreatedDate, LastUpdatedDate FROM Lesson";

    try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
        try (ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(rs.getInt("LessonID"));
                lesson.setCourseID(rs.getInt("CourseID"));
                lesson.setTitle(rs.getString("Title"));
                lesson.setDescription(rs.getString("Description"));
                lessons.add(lesson);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return lessons;
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
