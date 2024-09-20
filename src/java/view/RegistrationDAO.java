package view;

import model.Registration;
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

public class RegistrationDAO extends DBContext<Registration> {

    @Override
    public List<Registration> select() {
        String sql = "SELECT [id], [user_id], [course_id], [package_name], [total_cost], [status], [valid_from], [valid_to], [created_at], [updated_at] FROM [dbo].[registrations]";
        List<Registration> registrations = new Vector();
        try (PreparedStatement pre = super.getConn().prepareStatement(sql); ResultSet rs = pre.executeQuery()) {
            while (rs.next()) {
                Registration registration = new Registration();
                registration.setId(rs.getInt(1));
                registration.setUserId(rs.getInt(2));
                registration.setCourseId(rs.getInt(3));
                registration.setPackageName(rs.getString(4));
                registration.setTotalCost(rs.getDouble(5));
                registration.setStatus(rs.getString(6));
                registration.setValidFrom(rs.getTimestamp(7));
                registration.setValidTo(rs.getTimestamp(8));
                registration.setCreatedAt(rs.getTimestamp(9));
                registration.setUpdatedAt(rs.getTimestamp(10));
                registrations.add(registration);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDAO.class.getName()).log(Level.SEVERE, "Error selecting registrations", ex);
        }
        return registrations;
    }

    @Override
    public Registration select(int... id) {
        String sql = "SELECT [id], [user_id], [course_id], [package_name], [total_cost], [status], [valid_from], [valid_to], [created_at], [updated_at] FROM [dbo].[registrations] WHERE id = ?";
        Registration registration = null;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]);
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    registration = new Registration();
                    registration.setId(rs.getInt(1));
                    registration.setUserId(rs.getInt(2));
                    registration.setCourseId(rs.getInt(3));
                    registration.setPackageName(rs.getString(4));
                    registration.setTotalCost(rs.getDouble(5));
                    registration.setStatus(rs.getString(6));
                    registration.setValidFrom(rs.getTimestamp(7));
                    registration.setValidTo(rs.getTimestamp(8));
                    registration.setCreatedAt(rs.getTimestamp(9));
                    registration.setUpdatedAt(rs.getTimestamp(10));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDAO.class.getName()).log(Level.SEVERE, "Error selecting registration with id " + id, ex);
        }
        return registration;
    }

    @Override
    public int insert(Registration registration) {
        String sql = "INSERT INTO [dbo].[registrations] "
                + "([user_id], [course_id], [package_name], [total_cost], [status], [valid_from], [valid_to], [created_at], [updated_at]) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, registration.getUserId());
            pre.setInt(2, registration.getCourseId());
            pre.setString(3, registration.getPackageName());
            pre.setDouble(4, registration.getTotalCost());
            pre.setString(5, registration.getStatus());
            pre.setTimestamp(6, new java.sql.Timestamp(registration.getValidFrom().getTime()));
            pre.setTimestamp(7, new java.sql.Timestamp(registration.getValidTo().getTime()));
            pre.setTimestamp(8, new java.sql.Timestamp(registration.getCreatedAt().getTime()));
            pre.setTimestamp(9, new java.sql.Timestamp(registration.getUpdatedAt().getTime()));
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDAO.class.getName()).log(Level.SEVERE, "Error inserting registration", ex);
        }
        return result;
    }

    @Override
    public int update(Registration registration) {
        String sql = "UPDATE [dbo].[registrations] "
                + "SET [user_id] = ?, [course_id] = ?, [package_name] = ?, [total_cost] = ?, [status] = ?, [valid_from] = ?, [valid_to] = ?, [created_at] = ?, [updated_at] = ? "
                + "WHERE id = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, registration.getUserId());
            pre.setInt(2, registration.getCourseId());
            pre.setString(3, registration.getPackageName());
            pre.setDouble(4, registration.getTotalCost());
            pre.setString(5, registration.getStatus());
            pre.setTimestamp(6, new java.sql.Timestamp(registration.getValidFrom().getTime()));
            pre.setTimestamp(7, new java.sql.Timestamp(registration.getValidTo().getTime()));
            pre.setTimestamp(8, new java.sql.Timestamp(registration.getCreatedAt().getTime()));
            pre.setTimestamp(9, new java.sql.Timestamp(registration.getUpdatedAt().getTime()));
            pre.setInt(10, registration.getId());
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDAO.class.getName()).log(Level.SEVERE, "Error updating registration", ex);
        }
        return result;
    }

    @Override
    public int delete(int... id) {
        String sql = "DELETE FROM [dbo].[registrations] WHERE id = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]);
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDAO.class.getName()).log(Level.SEVERE, "Error deleting registration with id " + id, ex);
        }
        return result;
    }
}
