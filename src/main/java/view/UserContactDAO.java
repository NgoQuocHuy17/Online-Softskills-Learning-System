package view;

import model.UserContact;
import java.util.List;
import java.util.Vector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;

public class UserContactDAO extends DBContext<UserContact> {

    public List<UserContact> getUserPhones(int userId) {
        List<UserContact> phones = new ArrayList<>();
        String query = "SELECT * FROM user_contacts WHERE user_id = ? AND contact_type = 'Phone'";

        try (PreparedStatement pre = super.getConn().prepareStatement(query)) {
            pre.setInt(1, userId);
            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    UserContact contact = new UserContact(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getString("contact_type"),
                            rs.getString("contact_value"),
                            rs.getBoolean("is_preferred")
                    );
                    phones.add(contact);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return phones;
    }

    public List<UserContact> getUserEmails(int userId) {
        List<UserContact> emails = new ArrayList<>();
        String query = "SELECT * FROM user_contacts WHERE user_id = ? AND contact_type = 'Email'";

        try (PreparedStatement pre = super.getConn().prepareStatement(query)) {
            pre.setInt(1, userId);
            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    UserContact contact = new UserContact(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getString("contact_type"),
                            rs.getString("contact_value"),
                            rs.getBoolean("is_preferred")
                    );
                    emails.add(contact);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emails;
    }

    @Override
    public List<UserContact> select() {
        String sql = "SELECT [id], [user_id], [contact_type], [contact_value] FROM [dbo].[user_contacts]";
        List<UserContact> userContacts = new Vector();
        try (PreparedStatement pre = super.getConn().prepareStatement(sql); ResultSet rs = pre.executeQuery()) {
            while (rs.next()) {
                UserContact userContact = new UserContact();
                userContact.setId(rs.getInt(1));
                userContact.setUserId(rs.getInt(2));
                userContact.setContactType(rs.getString(3));
                userContact.setContactValue(rs.getString(4));
                userContacts.add(userContact);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserContactDAO.class.getName()).log(Level.SEVERE, "Error selecting user contacts", ex);
        }
        return userContacts;
    }

    @Override
    public UserContact select(int... id) {
        String sql = "SELECT [id], [user_id], [contact_type], [contact_value] FROM [dbo].[user_contacts] WHERE id = ?";
        UserContact userContact = null;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]);
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    userContact = new UserContact();
                    userContact.setId(rs.getInt(1));
                    userContact.setUserId(rs.getInt(2));
                    userContact.setContactType(rs.getString(3));
                    userContact.setContactValue(rs.getString(4));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserContactDAO.class.getName()).log(Level.SEVERE, "Error selecting user contact with id " + id[0], ex);
        }
        return userContact;
    }

    @Override
    public int insert(UserContact userContact) {
        String sql = "INSERT INTO [dbo].[user_contacts] ([user_id], [contact_type], [contact_value]) VALUES (?, ?, ?)";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, userContact.getUserId());
            pre.setString(2, userContact.getContactType());
            pre.setString(3, userContact.getContactValue());
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserContactDAO.class.getName()).log(Level.SEVERE, "Error inserting user contact", ex);
        }
        return result;
    }

    @Override
    public int update(UserContact userContact) {
        String sql = "UPDATE [dbo].[user_contacts] SET [user_id] = ?, [contact_type] = ?, [contact_value] = ? WHERE id = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, userContact.getUserId());
            pre.setString(2, userContact.getContactType());
            pre.setString(3, userContact.getContactValue());
            pre.setInt(4, userContact.getId());
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserContactDAO.class.getName()).log(Level.SEVERE, "Error updating user contact", ex);
        }
        return result;
    }

    @Override
    public int delete(int... id) {
        String sql = "DELETE FROM [dbo].[user_contacts] WHERE id = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]);
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserContactDAO.class.getName()).log(Level.SEVERE, "Error deleting user contact with id " + id[0], ex);
        }
        return result;
    }
}
