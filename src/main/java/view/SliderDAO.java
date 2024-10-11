package view;

import model.Slider;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author Minh
 */
public class SliderDAO extends DBContext<Slider> {

    public List<Slider> select(int page, int slidersPerPage, String statusFilter, String searchQuery) {
        List<Slider> sliders = new ArrayList<>();
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
                    slider.setImageUrl(rs.getString("image_url"));
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

    public int getTotalSliders(String statusFilter, String searchQuery) {
        int total = 0;
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM sliders WHERE 1=1");

        if (statusFilter != null && !statusFilter.isEmpty()) {
            sql.append(" AND status = ?");
        }

        if (searchQuery != null && !searchQuery.isEmpty()) {
            sql.append(" AND (title LIKE ? OR backlink LIKE ?)");
        }

        try (PreparedStatement ps = super.getConn().prepareStatement(sql.toString())) {
            int index = 1;

            if (statusFilter != null && !statusFilter.isEmpty()) {
                ps.setString(index++, statusFilter);
            }

            if (searchQuery != null && !searchQuery.isEmpty()) {
                String wildcardQuery = "%" + searchQuery + "%";
                ps.setString(index++, wildcardQuery); // For title
                ps.setString(index++, wildcardQuery); // For backlink
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    total = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
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

    @Override
    public List<Slider> select() {
        String sql = "SELECT [id], [title], [image_url], [backlink], [status], [created_at], [updated_at] FROM [dbo].[sliders]";
        List<Slider> sliders = new ArrayList();
        try (PreparedStatement pre = super.getConn().prepareStatement(sql); ResultSet rs = pre.executeQuery()) {
            while (rs.next()) {
                Slider slider = new Slider();
                slider.setId(rs.getInt(1));
                slider.setTitle(rs.getString(2));
                slider.setImageUrl(rs.getString(3));
                slider.setBacklink(rs.getString(4));
                slider.setStatus(rs.getString(5));
                slider.setCreatedAt(rs.getTimestamp(6).toLocalDateTime());
                slider.setUpdatedAt(rs.getTimestamp(7).toLocalDateTime());
                sliders.add(slider);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, "Error selecting sliders", ex);
        }
        return sliders;
    }

    @Override
    public Slider select(int... id) {
        String sql = "SELECT [id], [title], [image_url], [backlink], [status], [created_at], [updated_at] "
                + "FROM [dbo].[sliders] WHERE id = ?";
        Slider slider = null;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]);
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    slider = new Slider();
                    slider.setId(rs.getInt(1));
                    slider.setTitle(rs.getString(2));
                    slider.setImageUrl(rs.getString(3));
                    slider.setBacklink(rs.getString(4));
                    slider.setStatus(rs.getString(5));
                    slider.setCreatedAt(rs.getTimestamp(6).toLocalDateTime());
                    slider.setUpdatedAt(rs.getTimestamp(7).toLocalDateTime());
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, "Error selecting slider with id " + id[0], ex);
        }
        return slider;
    }

    @Override
    public int insert(Slider slider) {
        String sql = "INSERT INTO [dbo].[sliders] "
                + "([title], [image_url], [backlink], [status], [created_at], [updated_at]) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setString(1, slider.getTitle());
            pre.setString(2, slider.getImageUrl());
            pre.setString(3, slider.getBacklink());
            pre.setString(4, slider.getStatus());
            pre.setTimestamp(5, java.sql.Timestamp.valueOf(slider.getCreatedAt()));
            pre.setTimestamp(6, java.sql.Timestamp.valueOf(slider.getUpdatedAt()));
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, "Error inserting slider", ex);
        }
        return result;
    }

    @Override
    public int update(Slider slider) {
        String sql = "UPDATE [dbo].[sliders] "
                + "SET [title] = ?, [image_url] = ?, [backlink] = ?, [status] = ?, [created_at] = ?, [updated_at] = ? "
                + "WHERE id = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setString(1, slider.getTitle());
            pre.setString(2, slider.getImageUrl());
            pre.setString(3, slider.getBacklink());
            pre.setString(4, slider.getStatus());
            pre.setTimestamp(5, java.sql.Timestamp.valueOf(slider.getCreatedAt()));
            pre.setTimestamp(6, java.sql.Timestamp.valueOf(slider.getUpdatedAt()));
            pre.setInt(7, slider.getId());
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, "Error updating slider", ex);
        }
        return result;
    }

    @Override
    public int delete(int... id) {
        String sql = "DELETE FROM [dbo].[sliders] WHERE id = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]);
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, "Error deleting slider with id " + id[0], ex);
        }
        return result;
    }
}
