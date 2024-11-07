package view;

import model.ActivityLog;
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
public class ActivityLogDAO extends DBContext<ActivityLog> {

    @Override
    public List<ActivityLog> select() {
        String sql = "SELECT * FROM [dbo].[activity_log]";
        List<ActivityLog> activityLogs = new Vector<>();
        try (PreparedStatement pre = super.getConn().prepareStatement(sql); ResultSet rs = pre.executeQuery()) {
            while (rs.next()) {
                ActivityLog activityLog = new ActivityLog();
                activityLog.setId(rs.getInt(1));
                activityLog.setUserId(rs.getInt(2));
                activityLog.setActivityType(rs.getString(3));
                activityLog.setActivityData(rs.getString(4));
                activityLog.setCreatedAt(rs.getTimestamp(5)); // Changed to Timestamp to match your select(int id) method
                activityLogs.add(activityLog);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActivityLogDAO.class.getName()).log(Level.SEVERE, "Error selecting activity logs", ex);
        }
        return activityLogs;
    }

    @Override
    public ActivityLog select(int... id) {
        String sql = "SELECT [id], [user_id], [activity_type], [activity_data], [created_at] "
                + "FROM [dbo].[activity_log] WHERE id = ?";
        ActivityLog activityLog = null;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]);
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    activityLog = new ActivityLog();
                    activityLog.setId(rs.getInt(1));
                    activityLog.setUserId(rs.getInt(2));
                    activityLog.setActivityType(rs.getString(3));
                    activityLog.setActivityData(rs.getString(4));
                    activityLog.setCreatedAt(rs.getTimestamp(5)); // Changed to Timestamp
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActivityLogDAO.class.getName()).log(Level.SEVERE, "Error selecting activity log with id " + id, ex);
        }
        return activityLog;
    }

    @Override
    public int insert(ActivityLog activityLog) {
        String sql = "INSERT INTO [dbo].[activity_log] "
                + "([user_id], [activity_type], [activity_data], [created_at]) "
                + "VALUES (?, ?, ?, ?)";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, activityLog.getUserId());
            pre.setString(2, activityLog.getActivityType());
            pre.setString(3, activityLog.getActivityData());
            pre.setTimestamp(4, new java.sql.Timestamp(activityLog.getCreatedAt().getTime()));
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ActivityLogDAO.class.getName()).log(Level.SEVERE, "Error inserting activity log", ex);
        }
        return result;
    }

    @Override
    public int update(ActivityLog activityLog) {
        String sql = "UPDATE [dbo].[activity_log] "
                + "SET [user_id] = ?, [activity_type] = ?, [activity_data] = ?, [created_at] = ? "
                + "WHERE id = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, activityLog.getUserId());
            pre.setString(2, activityLog.getActivityType());
            pre.setString(3, activityLog.getActivityData());
            pre.setTimestamp(4, new java.sql.Timestamp(activityLog.getCreatedAt().getTime()));
            pre.setInt(5, activityLog.getId());
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ActivityLogDAO.class.getName()).log(Level.SEVERE, "Error updating activity log", ex);
        }
        return result;
    }

    @Override
    public int delete(int... id) {
        String sql = "DELETE FROM [dbo].[activity_log] WHERE id = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]);
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ActivityLogDAO.class.getName()).log(Level.SEVERE, "Error deleting activity log with id " + id, ex);
        }
        return result;
    }
}
