package view;

import model.Tag;
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

public class TagDAO extends DBContext<Tag> {

    @Override
    public List<Tag> select() {
        String sql = "SELECT [id], [name] FROM [dbo].[tags]";
        List<Tag> tags = new Vector<>();
        try (PreparedStatement pre = super.getConn().prepareStatement(sql); ResultSet rs = pre.executeQuery()) {
            while (rs.next()) {
                Tag tag = new Tag();
                tag.setId(rs.getInt(1));
                tag.setName(rs.getString(2));
                tags.add(tag);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TagDAO.class.getName()).log(Level.SEVERE, "Error selecting tags", ex);
        }
        return tags;
    }

    @Override
    public Tag select(int... id) {
        String sql = "SELECT [id], [name] FROM [dbo].[tags] WHERE id = ?";
        Tag tag = null;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]);
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    tag = new Tag();
                    tag.setId(rs.getInt(1));
                    tag.setName(rs.getString(2));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TagDAO.class.getName()).log(Level.SEVERE, "Error selecting tag with id " + id[0], ex);
        }
        return tag;
    }

    @Override
    public int insert(Tag tag) {
        String sql = "INSERT INTO [dbo].[tags] ([name]) VALUES (?)";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setString(1, tag.getName());
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TagDAO.class.getName()).log(Level.SEVERE, "Error inserting tag", ex);
        }
        return result;
    }

    @Override
    public int update(Tag tag) {
        String sql = "UPDATE [dbo].[tags] SET [name] = ? WHERE id = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setString(1, tag.getName());
            pre.setInt(2, tag.getId());
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TagDAO.class.getName()).log(Level.SEVERE, "Error updating tag", ex);
        }
        return result;
    }

    @Override
    public int delete(int... id) {
        String sql = "DELETE FROM [dbo].[tags] WHERE id = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]);
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TagDAO.class.getName()).log(Level.SEVERE, "Error deleting tag with id " + id[0], ex);
        }
        return result;
    }
}
