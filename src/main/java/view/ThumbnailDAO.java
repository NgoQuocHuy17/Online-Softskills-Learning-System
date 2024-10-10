package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import model.Thumbnail;

public class ThumbnailDAO extends DBContext<Thumbnail> {

    /**
     * Inserts a new thumbnail record into the database.
     * 
     * @param thumbnail The thumbnail to be inserted.
     * @return The number of rows affected (should be 1 if successful).
     */
    public int addThumbnail(Thumbnail thumbnail) {
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        
        String sql = "INSERT INTO Thumbnails (courseId, fileName, fileData) VALUES (?, ?, ?)";
        
        try {
            conn = getConn();  // Open a connection
            ps = conn.prepareStatement(sql);
            
            // Set the parameters
            ps.setInt(1, thumbnail.getCourseId());
            ps.setString(2, thumbnail.getFileName());
            ps.setBytes(3, thumbnail.getFileData());
            
            // Execute the insert statement
            result = ps.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Always close the resources
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            closeConnection(conn);
        }
        
        return result;
    }

    // Implement abstract methods (for completeness)
    @Override
    public List<Thumbnail> select() {
        // Implementation for retrieving thumbnails (not needed here)
        return null;
    }

    @Override
    public Thumbnail select(int... id) {
        // Implementation for retrieving a single thumbnail by ID
        return null;
    }

    @Override
    public int insert(Thumbnail obj) {
        return addThumbnail(obj);
    }

    @Override
    public int update(Thumbnail obj) {
        // Implementation for updating a thumbnail (not needed here)
        return 0;
    }

    @Override
    public int delete(int... id) {
        // Implementation for deleting a thumbnail (not needed here)
        return 0;
    }
}
