package view;

import model.LoginHistory;
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

public class LoginHistoryDAO extends DBContext<LoginHistory> {

    @Override
    public List<LoginHistory> select() {
        String sql = "SELECT [id], [user_id], [login_time], [ip_address] FROM [dbo].[login_history]";
        List<LoginHistory> loginHistories = new Vector<>();
        try (PreparedStatement pre = super.getConn().prepareStatement(sql); ResultSet rs = pre.executeQuery()) {
            while (rs.next()) {
                LoginHistory loginHistory = new LoginHistory();
                loginHistory.setId(rs.getInt(1));
                loginHistory.setUserId(rs.getInt(2));
                loginHistory.setLoginTime(rs.getTimestamp(3));
                loginHistory.setIpAddress(rs.getString(4));
                loginHistories.add(loginHistory);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginHistoryDAO.class.getName()).log(Level.SEVERE, "Error selecting login history", ex);
        }
        return loginHistories;
    }

    @Override
    public LoginHistory select(int... id) {
        String sql = "SELECT [id], [user_id], [login_time], [ip_address] FROM [dbo].[login_history] WHERE id = ?";
        LoginHistory loginHistory = null;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]);
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    loginHistory = new LoginHistory();
                    loginHistory.setId(rs.getInt(1));
                    loginHistory.setUserId(rs.getInt(2));
                    loginHistory.setLoginTime(rs.getTimestamp(3));
                    loginHistory.setIpAddress(rs.getString(4));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginHistoryDAO.class.getName()).log(Level.SEVERE, "Error selecting login history with id " + id, ex);
        }
        return loginHistory;
    }

    @Override
    public int insert(LoginHistory loginHistory) {
        String sql = "INSERT INTO [dbo].[login_history] ([user_id], [login_time], [ip_address]) VALUES (?, ?, ?)";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, loginHistory.getUserId());
            pre.setTimestamp(2, new java.sql.Timestamp(loginHistory.getLoginTime().getTime()));
            pre.setString(3, loginHistory.getIpAddress());
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LoginHistoryDAO.class.getName()).log(Level.SEVERE, "Error inserting login history", ex);
        }
        return result;
    }

    @Override
    public int update(LoginHistory loginHistory) {
        String sql = "UPDATE [dbo].[login_history] SET [user_id] = ?, [login_time] = ?, [ip_address] = ? WHERE id = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, loginHistory.getUserId());
            pre.setTimestamp(2, new java.sql.Timestamp(loginHistory.getLoginTime().getTime()));
            pre.setString(3, loginHistory.getIpAddress());
            pre.setInt(4, loginHistory.getId());
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LoginHistoryDAO.class.getName()).log(Level.SEVERE, "Error updating login history", ex);
        }
        return result;
    }

    @Override
    public int delete(int... id) {
        String sql = "DELETE FROM [dbo].[login_history] WHERE id = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]);
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LoginHistoryDAO.class.getName()).log(Level.SEVERE, "Error deleting login history with id " + id, ex);
        }
        return result;
    }
}
