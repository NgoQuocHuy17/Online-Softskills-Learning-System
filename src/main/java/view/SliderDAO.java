package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Slider;

public class SliderDAO extends DBContext<Slider> {

    private Connection conn;

    // Constructor to initialize the connection using DBContext
    public SliderDAO() {
        this.conn = getConn();  // Get connection from DBContext
    }

    // Method to get all visible sliders from the database
    public List<Slider> getVisibleSliders() {
        List<Slider> sliders = new ArrayList<>();
        String sql = "SELECT id, title, image_url, backlink, status FROM sliders WHERE status = 'Visible'";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();  // Execute the SQL query
            while (rs.next()) {
                Slider slider = new Slider(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("image_url"),
                        rs.getString("backlink"),
                        rs.getString("status")
                );
                sliders.add(slider);  // Add each slider to the list
            }
            rs.close();  // Close the ResultSet after use
        } catch (SQLException e) {
            e.printStackTrace();  // Print exception stack trace for debugging
        }

        return sliders;  // Return the list of sliders
    }

    @Override
    public List<Slider> select() {
        List<Slider> sliders = new ArrayList<>();
        String sql = "SELECT id, title, image_url, backlink, status FROM sliders";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();  // Execute the SQL query
            while (rs.next()) {
                Slider slider = new Slider(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("image_url"),
                        rs.getString("backlink"),
                        rs.getString("status")
                );
                sliders.add(slider);  // Add each slider to the list
            }
            rs.close();  // Close the ResultSet after use
        } catch (SQLException e) {
            e.printStackTrace();  // Print exception stack trace for debugging
        }

        return sliders;
    }

    @Override
    public Slider select(int... id) {
        Slider slider = null;
        String sql = "SELECT id, title, image_url, backlink, status FROM sliders WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id[0]);  // Assuming there's only one id to search
            ResultSet rs = ps.executeQuery();  // Execute the SQL query

            if (rs.next()) {
                slider = new Slider(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("image_url"),
                        rs.getString("backlink"),
                        rs.getString("status")
                );
            }
            rs.close();  // Close the ResultSet after use
        } catch (SQLException e) {
            e.printStackTrace();  // Print exception stack trace for debugging
        }

        return slider;
    }

    @Override
    public int insert(Slider slider) {
        String sql = "INSERT INTO sliders (title, image_url, backlink, status) VALUES (?, ?, ?, ?)";
        int result = 0;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, slider.getTitle());
            ps.setString(2, slider.getImageUrl());
            ps.setString(3, slider.getBacklink());
            ps.setString(4, slider.getStatus());

            result = ps.executeUpdate();  // Insert the record and get affected row count
        } catch (SQLException e) {
            e.printStackTrace();  // Print exception stack trace for debugging
        }

        return result;
    }

    @Override
    public int update(Slider slider) {
        String sql = "UPDATE sliders SET title = ?, image_url = ?, backlink = ?, status = ? WHERE id = ?";
        int result = 0;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, slider.getTitle());
            ps.setString(2, slider.getImageUrl());
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

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id[0]);  // Assuming there's only one id to delete
            result = ps.executeUpdate();  // Delete the record and get affected row count
        } catch (SQLException e) {
            e.printStackTrace();  // Print exception stack trace for debugging
        }

        return result;
    }
}
