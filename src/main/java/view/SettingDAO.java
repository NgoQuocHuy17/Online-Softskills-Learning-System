package view;

import model.Setting;
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

public class SettingDAO extends DBContext<Setting> {

    public List<Setting> select(String searchValue, String type, String status, String sort, String order) {
        StringBuilder sql = new StringBuilder("SELECT [id], [setting_type], [value], [order_num], [status] FROM [dbo].[settings] WHERE 1=1");
        
        // Add filters based on input parameters
        if (searchValue != null && !searchValue.isEmpty()) {
            sql.append(" AND [value] LIKE ?");
        }
        if (type != null && !type.isEmpty()) {
            sql.append(" AND [setting_type] = ?");
        }
        if (status != null && !status.isEmpty()) {
            sql.append(" AND [status] = ?");
        }

        // Add sorting if specified
        if (sort != null && !sort.isEmpty()) {
            sql.append(" ORDER BY ").append(sort).append(" ").append(order != null && order.equalsIgnoreCase("desc") ? "DESC" : "ASC");
        }

        List<Setting> settings = new Vector<>();
        try (PreparedStatement pre = super.getConn().prepareStatement(sql.toString())) {
            int index = 1;

            // Set the parameters for the prepared statement
            if (searchValue != null && !searchValue.isEmpty()) {
                pre.setString(index++, "%" + searchValue + "%");
            }
            if (type != null && !type.isEmpty()) {
                pre.setString(index++, type);
            }
            if (status != null && !status.isEmpty()) {
                pre.setString(index++, status);
            }

            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    Setting setting = new Setting();
                    setting.setId(rs.getInt(1));
                    setting.setType(rs.getString(2));
                    setting.setValue(rs.getString(3));
                    setting.setOrderNum(rs.getInt(4));
                    setting.setStatus(rs.getString(5));
                    settings.add(setting);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, "Error selecting settings with filters", ex);
        }
        return settings;
    }
    
    @Override
    public List<Setting> select() {
        String sql = "SELECT [id], [setting_type], [value], [order_num], [status] FROM [dbo].[settings]";
        List<Setting> settings = new Vector<>();
        try (PreparedStatement pre = super.getConn().prepareStatement(sql); ResultSet rs = pre.executeQuery()) {
            while (rs.next()) {
                Setting setting = new Setting();
                setting.setId(rs.getInt(1));
                setting.setSettingType(rs.getString(2));
                setting.setValue(rs.getString(3));
                setting.setOrderNum(rs.getInt(4));
                setting.setStatus(rs.getString(5));
                settings.add(setting);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, "Error selecting settings", ex);
        }
        return settings;
    }

    @Override
    public Setting select(int... id) {
        String sql = "SELECT [id], [setting_type], [value], [order_num], [status] FROM [dbo].[settings] WHERE id = ?";
        Setting setting = null;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]);
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    setting = new Setting();
                    setting.setId(rs.getInt(1));
                    setting.setSettingType(rs.getString(2));
                    setting.setValue(rs.getString(3));
                    setting.setOrderNum(rs.getInt(4));
                    setting.setStatus(rs.getString(5));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, "Error selecting setting with id " + id, ex);
        }
        return setting;
    }

    @Override
    public int insert(Setting setting) {
        String sql = "INSERT INTO [dbo].[settings] "
                + "([setting_type], [value], [order_num], [status]) "
                + "VALUES (?, ?, ?, ?)";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setString(1, setting.getSettingType());
            pre.setString(2, setting.getValue());
            pre.setInt(3, setting.getOrderNum());
            pre.setString(4, setting.getStatus());
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, "Error inserting setting", ex);
        }
        return result;
    }

    @Override
    public int update(Setting setting) {
        String sql = "UPDATE [dbo].[settings] "
                + "SET [setting_type] = ?, [value] = ?, [order_num] = ?, [status] = ? "
                + "WHERE id = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setString(1, setting.getSettingType());
            pre.setString(2, setting.getValue());
            pre.setInt(3, setting.getOrderNum());
            pre.setString(4, setting.getStatus());
            pre.setInt(5, setting.getId());
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, "Error updating setting", ex);
        }
        return result;
    }

    @Override
    public int delete(int... id) {
        String sql = "DELETE FROM [dbo].[settings] WHERE id = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]);
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, "Error deleting setting with id " + id, ex);
        }
        return result;
    }
}