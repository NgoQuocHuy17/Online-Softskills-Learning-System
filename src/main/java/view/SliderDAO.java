package view;

import model.Slider;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SliderDAO extends DBContext<Slider> {

    // Method to get all visible sliders from the database
    public List<Slider> getVisibleSliders() {
        List<Slider> sliders = new ArrayList<>();
        String sql = "SELECT id, title, image_url, backlink, status FROM sliders WHERE status = 'Visible'";

        try (Connection conn = getConn();  // Get a new connection
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {  // Execute the SQL query

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
        } catch (SQLException e) {
            e.printStackTrace();  // Print exception stack trace for debugging
        }

        return sliders;  // Return the list of sliders
    }

    @Override
    public List<Slider> select() {
        List<Slider> sliders = new ArrayList<>();
        String sql = "SELECT id, title, image_url, backlink, status FROM sliders";

        try (Connection conn = getConn();  // Get a new connection
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {  // Execute the SQL query

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
        } catch (SQLException e) {
            e.printStackTrace();  // Print exception stack trace for debugging
        }

        return sliders;
    }

    @Override
    public Slider select(int... id) {
        Slider slider = null;
        String sql = "SELECT id, title, image_url, backlink, status FROM sliders WHERE id = ?";

        try (Connection conn = getConn();  // Get a new connection
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id[0]);  // Assuming there's only one id to search
            try (ResultSet rs = ps.executeQuery()) {  // Execute the SQL query
                if (rs.next()) {
                    slider = new Slider(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("image_url"),
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

        try (Connection conn = getConn();  // Get a new connection
             PreparedStatement ps = conn.prepareStatement(sql)) {

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

        try (Connection conn = getConn();  // Get a new connection
             PreparedStatement ps = conn.prepareStatement(sql)) {

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

        try (Connection conn = getConn();  // Get a new connection
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id[0]);  // Assuming there's only one id to delete
            result = ps.executeUpdate();  // Delete the record and get affected row count
        } catch (SQLException e) {
            e.printStackTrace();  // Print exception stack trace for debugging
        }

        return result;
    }
}
