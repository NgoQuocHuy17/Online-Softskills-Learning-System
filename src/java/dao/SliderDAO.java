package view;

import model.Slider;
import java.util.List;
import java.util.Vector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author Minh
 */

public class SliderDAO extends DBContext<Slider> {

    @Override
    public List<Slider> select() {
        String sql = "SELECT [id], [title], [image_url], [backlink], [status], [created_at], [updated_at] FROM [dbo].[sliders]";
        List<Slider> sliders = new Vector();
        try (PreparedStatement pre = super.getConn().prepareStatement(sql); ResultSet rs = pre.executeQuery()) {
            while (rs.next()) {
                Slider slider = new Slider();
                slider.setId(rs.getInt(1));
                slider.setTitle(rs.getString(2));
                slider.setImageUrl(rs.getString(3));
                slider.setBacklink(rs.getString(4));
                slider.setStatus(rs.getString(5));
                slider.setCreatedAt(rs.getTimestamp(6));
                slider.setUpdatedAt(rs.getTimestamp(7));
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
                    slider.setCreatedAt(rs.getTimestamp(6));
                    slider.setUpdatedAt(rs.getTimestamp(7));
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
            pre.setTimestamp(5, new java.sql.Timestamp(slider.getCreatedAt().getTime()));
            pre.setTimestamp(6, new java.sql.Timestamp(slider.getUpdatedAt().getTime()));
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
            pre.setTimestamp(5, new java.sql.Timestamp(slider.getCreatedAt().getTime()));
            pre.setTimestamp(6, new java.sql.Timestamp(slider.getUpdatedAt().getTime()));
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
