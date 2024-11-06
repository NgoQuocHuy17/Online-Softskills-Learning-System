package view;

import java.io.ByteArrayInputStream;
import model.Slider;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class SliderDAO extends DBContext<Slider> {

    public List<Slider> getSlidersByPage(int page, int pageSize, String statusFilter, String searchTerm, String sortBy, String sortOrder) {
        List<Slider> sliders = new ArrayList<>();
        String sql = "WITH PaginatedSliders AS ( "
                + "    SELECT *, ROW_NUMBER() OVER (ORDER BY " + (sortBy != null ? sortBy : "id") + " " + (sortOrder != null ? sortOrder : "ASC") + ") AS RowNum "
                + "    FROM sliders "
                + "    WHERE 1=1 ";

        // Apply filters
        if (statusFilter != null && !statusFilter.isEmpty()) {
            sql += " AND status = ?";
        }
        if (searchTerm != null && !searchTerm.isEmpty()) {
            sql += " AND (title LIKE ? OR backlink LIKE ?)";
        }

        sql += ") "
                + "SELECT * FROM PaginatedSliders "
                + "WHERE RowNum BETWEEN ? AND ?";  // Fetch only rows within the calculated range

        try (Connection conn = getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            int index = 1;
            // Set filters
            if (statusFilter != null && !statusFilter.isEmpty()) {
                ps.setString(index++, statusFilter);
            }
            if (searchTerm != null && !searchTerm.isEmpty()) {
                ps.setString(index++, "%" + searchTerm + "%");
                ps.setString(index++, "%" + searchTerm + "%");
            }

            // Set pagination
            int startRow = (page - 1) * pageSize + 1; // Inclusive starting row
            int endRow = page * pageSize; // Inclusive ending row
            ps.setInt(index++, startRow); // Set start row
            ps.setInt(index++, endRow); // Set end row

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    Blob imageBlob = rs.getBlob("image_url");
                    byte[] imageBytes = null;
                    if (imageBlob != null) {
                        imageBytes = imageBlob.getBytes(1, (int) imageBlob.length());  // Convert BLOB to byte[]
                    }

                    Slider slider = new Slider(
                            rs.getInt("id"),
                            rs.getString("title"),
                            imageBytes,
                            rs.getString("backlink"),
                            rs.getString("status")
                    );
                    sliders.add(slider);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sliders;
    }

    public int getTotalSliders(String statusFilter, String searchTerm) {
        int totalSliders = 0;
        String sql = "SELECT COUNT(*) FROM sliders WHERE 1=1";

        if (statusFilter != null && !statusFilter.isEmpty()) {
            sql += " AND status = ?";
        }
        if (searchTerm != null && !searchTerm.isEmpty()) {
            sql += " AND (title LIKE ? OR backlink LIKE ?)";
        }

        try (Connection conn = getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            int index = 1;
            if (statusFilter != null && !statusFilter.isEmpty()) {
                ps.setString(index++, statusFilter);
            }
            if (searchTerm != null && !searchTerm.isEmpty()) {
                ps.setString(index++, "%" + searchTerm + "%");
                ps.setString(index++, "%" + searchTerm + "%");
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalSliders = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalSliders;
    }

    public List<Slider> select(int page, int slidersPerPage, String statusFilter, String searchQuery) {
        List<Slider> sliders = new Vector<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM sliders WHERE 1=1");

        // Append conditions based on filters
        if (statusFilter != null && !statusFilter.isEmpty()) {
            sql.append(" AND status = ?");
        }

        if (searchQuery != null && !searchQuery.isEmpty()) {
            sql.append(" AND (title LIKE ? OR backlink LIKE ?)");
        }

        // SQL Server syntax for pagination using OFFSET and FETCH
        sql.append(" ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

        try (PreparedStatement ps = super.getConn().prepareStatement(sql.toString())) {
            int index = 1;

            // Set the status filter if applicable
            if (statusFilter != null && !statusFilter.isEmpty()) {
                ps.setString(index++, statusFilter);
            }

            // Set the search query if applicable
            if (searchQuery != null && !searchQuery.isEmpty()) {
                String wildcardQuery = "%" + searchQuery + "%";
                ps.setString(index++, wildcardQuery); // For title
                ps.setString(index++, wildcardQuery); // For backlink
            }

            // Set pagination parameters
            ps.setInt(index++, (page - 1) * slidersPerPage); // Offset
            ps.setInt(index++, slidersPerPage); // Limit

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Slider slider = new Slider();
                    slider.setId(rs.getInt("id"));
                    slider.setTitle(rs.getString("title"));
                    slider.setImageUrl(rs.getBlob("image_url").getBytes(1, (int) rs.getBlob("image_url").length()));
                    slider.setBacklink(rs.getString("backlink"));
                    slider.setStatus(rs.getString("status"));
                    slider.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                    slider.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                    sliders.add(slider);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sliders;
    }

    public void toggleSliderStatus(int id) {
        String sql = "UPDATE sliders SET status = CASE WHEN status = 'active' THEN 'inactive' ELSE 'active' END WHERE id = ?";

        try (PreparedStatement ps = super.getConn().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get all visible sliders from the database
    public List<Slider> getVisibleSliders() {
        List<Slider> sliders = new ArrayList<>();
        String sql = "SELECT id, title, image_url, backlink, status FROM sliders WHERE status = 'Visible'";

        try (Connection conn = getConn(); // Get a new connection
                 PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {  // Execute the SQL query

            while (rs.next()) {
                // Convert BLOB to byte[] for image_url
                Blob imageBlob = rs.getBlob("image_url");
                byte[] imageBytes = null;
                if (imageBlob != null) {
                    imageBytes = imageBlob.getBytes(1, (int) imageBlob.length());  // Convert BLOB to byte[]
                }

                // Create Slider object and add it to the list
                Slider slider = new Slider(
                        rs.getInt("id"),
                        rs.getString("title"),
                        imageBytes, // Set the image URL as byte array
                        rs.getString("backlink"),
                        rs.getString("status")
                );
                sliders.add(slider);  // Add each slider to the list
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Print exception stack trace for debugging
        }
        return sliders;  // Return the list of sliders
    }

    @Override
    public List<Slider> select() {
        List<Slider> sliders = new ArrayList<>();
        String sql = "SELECT id, title, image_url, backlink, status FROM sliders";

        try (Connection conn = getConn(); // Get a new connection
                 PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {  // Execute the SQL query

            while (rs.next()) {

                Blob imageBlob = rs.getBlob("image_url");
                byte[] imageBytes = null;
                if (imageBlob != null) {
                    imageBytes = imageBlob.getBytes(1, (int) imageBlob.length());  // Convert BLOB to byte[]
                }

                Slider slider = new Slider(
                        rs.getInt("id"),
                        rs.getString("title"),
                        imageBytes,
                        rs.getString("backlink"),
                        rs.getString("status")
                );
                sliders.add(slider);  // Add each slider to the list
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Print exception stack trace for debugging
        }

        return sliders;
    }

    @Override
    public Slider select(int... id) {
        Slider slider = null;
        String sql = "SELECT id, title, image_url, backlink, status FROM sliders WHERE id = ?";

        try (Connection conn = getConn(); // Get a new connection
                 PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id[0]);  // Assuming there's only one id to search
            try (ResultSet rs = ps.executeQuery()) {  // Execute the SQL query
                if (rs.next()) {

                    Blob imageBlob = rs.getBlob("image_url");
                    byte[] imageBytes = null;
                    if (imageBlob != null) {
                        imageBytes = imageBlob.getBytes(1, (int) imageBlob.length());  // Convert BLOB to byte[]
                    }

                    slider = new Slider(
                            rs.getInt("id"),
                            rs.getString("title"),
                            imageBytes,
                            rs.getString("backlink"),
                            rs.getString("status")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Print exception stack trace for debugging
        }

        return slider;
    }

    @Override
    public int insert(Slider slider) {
        String sql = "INSERT INTO sliders (title, image_url, backlink, status) VALUES (?, ?, ?, ?)";
        int result = 0;

        try (Connection conn = getConn(); // Get a new connection
                 PreparedStatement ps = conn.prepareStatement(sql)) {

            // Set the parameters for the insert statement
            ps.setString(1, slider.getTitle());

            // Convert the byte array (imageUrl) to a BLOB
            byte[] imageBytes = slider.getImageUrl();
            if (imageBytes != null) {
                ps.setBlob(2, new ByteArrayInputStream(imageBytes)); // Use ByteArrayInputStream for BLOB
            } else {
                ps.setNull(2, java.sql.Types.BLOB); // If image is null, set it as NULL in database
            }

            ps.setString(3, slider.getBacklink());
            ps.setString(4, slider.getStatus());

            // Execute the insert and get the affected row count
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();  // Print exception stack trace for debugging
        }

        return result;
    }

    @Override
    public int update(Slider slider) {
        String sql = "UPDATE sliders SET title = ?, image_url = ?, backlink = ?, status = ? WHERE id = ?";
        int result = 0;

        try (Connection conn = getConn(); // Get a new connection
                 PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, slider.getTitle());

            // Convert the byte array (imageUrl) to a BLOB
            byte[] imageBytes = slider.getImageUrl();
            if (imageBytes != null) {
                ps.setBlob(2, new ByteArrayInputStream(imageBytes)); // Use ByteArrayInputStream for BLOB
            } else {
                ps.setNull(2, java.sql.Types.BLOB); // If image is null, set it as NULL in database
            }

            ps.setString(3, slider.getBacklink());
            ps.setString(4, slider.getStatus());
            ps.setInt(5, slider.getId());

            result = ps.executeUpdate();  // Update the record and get affected row count
        } catch (SQLException e) {
            e.printStackTrace();  // Print exception stack trace for debugging
        }

        return result;
    }

    @Override
    public int delete(int... id) {
        String sql = "DELETE FROM sliders WHERE id = ?";
        int result = 0;

        try (Connection conn = getConn(); // Get a new connection
                 PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id[0]);  // Assuming there's only one id to delete
            result = ps.executeUpdate();  // Delete the record and get affected row count
        } catch (SQLException e) {
            e.printStackTrace();  // Print exception stack trace for debugging
        }

        return result;
    }

}
