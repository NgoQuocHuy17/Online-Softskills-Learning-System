package view;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.LessonContent;

public class LessonContentDAO extends DBContext<LessonContent> {

    public List<LessonContent> getAllContentByLessonId(int lessonId) {
        List<LessonContent> lessonContents = new ArrayList<>();
        String sql = "SELECT ContentID, LessonID, ContentURL, ContentType, ContentDescription, TextContent, OrderInLesson, MediaData, CreatedDate FROM LessonContent WHERE LessonID = ? ORDER BY OrderInLesson";

        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, lessonId);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    LessonContent content = new LessonContent(
                            rs.getInt("ContentID"),
                            rs.getInt("LessonID"),
                            rs.getString("ContentURL"),
                            rs.getString("ContentType"),
                            rs.getString("ContentDescription"),
                            rs.getString("TextContent"),
                            rs.getInt("OrderInLesson"),
                            rs.getBytes("MediaData"), // Fetch binary data
                            rs.getTimestamp("CreatedDate")
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
        String sql = "INSERT INTO LessonContent (LessonID, ContentURL, ContentType, ContentDescription, TextContent, OrderInLesson, MediaData) VALUES (?, ?, ?, ?, ?, ?, ?)";
        boolean isAdded = false;

        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, content.getLessonId());
            pst.setString(2, content.getContentURL());
            pst.setString(3, content.getContentType());
            pst.setString(4, content.getContentDescription());
            pst.setString(5, content.getTextContent());
            pst.setInt(6, content.getOrderInLesson());
            pst.setBytes(7, content.getMediaData()); // Set binary data

            int rowsAffected = pst.executeUpdate();
            isAdded = (rowsAffected > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isAdded;
    }

    public LessonContent getContentById(int contentId) {
        LessonContent content = null;
        String sql = "SELECT ContentID, LessonID, ContentURL, ContentType, ContentDescription, TextContent, OrderInLesson, MediaData, CreatedDate FROM LessonContent WHERE ContentID = ?";

        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, contentId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    content = new LessonContent(
                            rs.getInt("ContentID"),
                            rs.getInt("LessonID"),
                            rs.getString("ContentURL"),
                            rs.getString("ContentType"),
                            rs.getString("ContentDescription"),
                            rs.getString("TextContent"),
                            rs.getInt("OrderInLesson"),
                            rs.getBytes("MediaData"), // Fetch binary data
                            rs.getTimestamp("CreatedDate")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return content;
    }

    public boolean updateContentById(LessonContent content) {
        String sql = "UPDATE LessonContent SET ContentURL = ?, ContentType = ?, ContentDescription = ?, TextContent = ?, OrderInLesson = ?, MediaData = ? WHERE ContentID = ?";
        boolean isUpdated = false;

        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, content.getContentURL());
            pst.setString(2, content.getContentType());
            pst.setString(3, content.getContentDescription());
            pst.setString(4, content.getTextContent());
            pst.setInt(5, content.getOrderInLesson());
            pst.setBytes(6, content.getMediaData()); // Set binary data
            pst.setInt(7, content.getId());

            int rowsAffected = pst.executeUpdate();
            isUpdated = (rowsAffected > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isUpdated;
    }
    public String getContentTypeById(int contentId) {
        String sql = "SELECT ContentType FROM LessonContent WHERE ContentID = ?";
        try (Connection conn = getConn(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, contentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("ContentType");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // No content type found
    }
   public byte[] getContentDataById(int contentId) {
        String sql = "SELECT MediaData FROM LessonContent WHERE ContentID = ?";
        try (Connection conn = getConn(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, contentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getBytes("MediaData");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // No data found
    }
    
    public boolean deleteContentById(int contentId) {
        String sql = "DELETE FROM LessonContent WHERE ContentID = ?";
        boolean isDeleted = false;

        try (Connection conn = getConn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, contentId);
            int rowsAffected = pst.executeUpdate();
            isDeleted = (rowsAffected > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isDeleted;
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
            e.printStackTrace();
        }
        return maxOrder;
    }

    @Override
    public List<LessonContent> select() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LessonContent select(int... id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int insert(LessonContent obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int update(LessonContent obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(int... id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
