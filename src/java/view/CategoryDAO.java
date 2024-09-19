package view;

import model.Category;
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
public class CategoryDAO extends DBContext<Category> {

    @Override
    public List<Category> select() {
        String sql = "SELECT [id], [name] FROM [dbo].[categories]";
        List<Category> categories = new Vector();
        try (PreparedStatement pre = super.getConn().prepareStatement(sql); ResultSet rs = pre.executeQuery()) {
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt(1));
                category.setName(rs.getString(2));
                categories.add(category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }

    @Override
    public Category select(int... id) {
        String sql = "SELECT [id], [name] FROM [dbo].[categories] WHERE id = ?";
        Category category = null;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]);
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    category = new Category();
                    category.setId(rs.getInt(1));
                    category.setName(rs.getString(2));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return category;
    }

    @Override
    public int insert(Category category) {
        String sql = "INSERT INTO [dbo].[categories] ([name]) VALUES (?)";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setString(1, category.getName());
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(Category category) {
        String sql = "UPDATE [dbo].[categories] SET [name] = ? WHERE id = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setString(1, category.getName());
            pre.setInt(2, category.getId());
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(int... id) {
        String sql = "DELETE FROM [dbo].[categories] WHERE id = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]);
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
