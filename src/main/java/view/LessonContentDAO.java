/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.LessonContent;

/**
 *
 * @author ngoqu
 */
public class LessonContentDAO extends DBContext<LessonContent> {

    public List<LessonContent> getAllContentByLessonId(int lessonId) {
        List<LessonContent> lessonContents = new ArrayList<>();
        String sql = "SELECT ContentID, LessonID, ContentURL, ContentType, ContentDescription, ImageURL, VideoURL, TextContent, OrderInLesson, CreatedDate FROM LessonContent WHERE LessonID = ? ORDER BY OrderInLesson";

        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, lessonId); // Set lessonId parameter in the query
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    LessonContent content = new LessonContent(
                            rs.getInt("ContentID"),
                            rs.getInt("LessonID"),
                            rs.getString("ContentURL"),
                            rs.getString("ContentType"),
                            rs.getString("ContentDescription"),
                            rs.getString("ImageURL"),
                            rs.getString("VideoURL"),
                            rs.getString("TextContent"),
                            rs.getInt("OrderInLesson"),
                            rs.getTimestamp("CreatedDate") // Assuming it's stored as Timestamp
                    );
                    lessonContents.add(content);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessonContents;
    }

    public boolean addContent(LessonContent content) {
        String sql = "INSERT INTO LessonContent (LessonID, ContentURL, ContentType, ContentDescription, ImageURL, VideoURL, TextContent, OrderInLesson) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        boolean isAdded = false; // Flag to track if the insertion was successful

        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            // Set parameters for the insert query
            pst.setInt(1, content.getLessonId());
            pst.setString(2, content.getContentURL());
            pst.setString(3, content.getContentType());
            pst.setString(4, content.getContentDescription());
            pst.setString(5, content.getImageURL());
            pst.setString(6, content.getVideoURL());
            pst.setString(7, content.getTextContent());
            pst.setInt(8, content.getOrderInLesson());

            int rowsAffected = pst.executeUpdate(); // Execute the insert query
            isAdded = (rowsAffected > 0); // If rowsAffected is greater than 0, insertion was successful
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isAdded; // Return true if addition was successful, otherwise false
    }

    public LessonContent getContentById(int contentId) {
        LessonContent content = null; // Initialize the content to null
        String sql = "SELECT ContentID, LessonID, ContentURL, ContentType, ContentDescription, ImageURL, VideoURL, TextContent, OrderInLesson, CreatedDate "
                + "FROM LessonContent WHERE ContentID = ?";

        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, contentId); // Set contentId parameter in the query
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) { // We expect at most one result
                    content = new LessonContent(
                            rs.getInt("ContentID"),
                            rs.getInt("LessonID"),
                            rs.getString("ContentURL"),
                            rs.getString("ContentType"),
                            rs.getString("ContentDescription"),
                            rs.getString("ImageURL"),
                            rs.getString("VideoURL"),
                            rs.getString("TextContent"),
                            rs.getInt("OrderInLesson"),
                            rs.getTimestamp("CreatedDate") // Assuming it's stored as Timestamp
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return content; // Return the LessonContent object or null if not found
    }

    public boolean updateContentById(LessonContent content) {
        String sql = "UPDATE LessonContent SET ContentURL = ?, ContentType = ?, ContentDescription = ?, ImageURL = ?, VideoURL = ?, TextContent = ?, OrderInLesson = ? WHERE ContentID = ?";
        boolean isUpdated = false; // Flag to track if the update was successful

        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            // Set parameters for the update query
            pst.setString(1, content.getContentURL());
            pst.setString(2, content.getContentType());
            pst.setString(3, content.getContentDescription());
            pst.setString(4, content.getImageURL());
            pst.setString(5, content.getVideoURL());
            pst.setString(6, content.getTextContent());
            pst.setInt(7, content.getOrderInLesson());
            pst.setInt(8, content.getId()); // Last parameter is the ID of the content to update

            int rowsAffected = pst.executeUpdate(); // Execute the update query
            isUpdated = (rowsAffected > 0); // If rowsAffected is greater than 0, update was successful
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isUpdated; // Return true if update was successful, otherwise false
    }

    public boolean deleteContentById(int contentId) {
        String sql = "DELETE FROM LessonContent WHERE ContentID = ?";
        boolean isDeleted = false; // Flag to track if the deletion was successful

        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, contentId); // Set contentId parameter in the query
            int rowsAffected = pst.executeUpdate(); // Execute the delete query
            isDeleted = (rowsAffected > 0); // If rowsAffected is greater than 0, deletion was successful
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isDeleted; // Return true if deletion was successful, otherwise false
    }

    public int getMaxOrderForLesson(int lessonId) {
        int maxOrder = 0;
        String sql = "SELECT MAX(OrderInLesson) FROM LessonContent WHERE LessonID = ?";

        try (Connection conn = getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, lessonId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maxOrder = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
        return maxOrder;
    }

    @Override
    public List<LessonContent> select() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LessonContent select(int... id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(LessonContent obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(LessonContent obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(int... id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
