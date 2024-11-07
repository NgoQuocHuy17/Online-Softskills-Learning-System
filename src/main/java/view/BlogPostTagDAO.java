package view;

import model.BlogPostTag;
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
public class BlogPostTagDAO extends DBContext<BlogPostTag> {

    @Override
    public List<BlogPostTag> select() {
        String sql = "SELECT [blog_post_id], [tag_id] FROM [dbo].[blog_post_tags]";
        List<BlogPostTag> blogPostTags = new Vector<>();
        try (PreparedStatement pre = super.getConn().prepareStatement(sql); ResultSet rs = pre.executeQuery()) {
            while (rs.next()) {
                BlogPostTag blogPostTag = new BlogPostTag();
                blogPostTag.setBlogPostId(rs.getInt(1));
                blogPostTag.setTagId(rs.getInt(2));
                blogPostTags.add(blogPostTag);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogPostTagDAO.class.getName()).log(Level.SEVERE, "Error selecting blog post tags", ex);
        }
        return blogPostTags;
    }

    @Override
    public BlogPostTag select(int... id) {
        String sql = "SELECT [blog_post_id], [tag_id] FROM [dbo].[blog_post_tags] WHERE [blog_post_id] = ? AND [tag_id] = ?";
        BlogPostTag blogPostTag = null;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]);
            pre.setInt(2, id[1]);
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    blogPostTag = new BlogPostTag();
                    blogPostTag.setBlogPostId(rs.getInt(1));
                    blogPostTag.setTagId(rs.getInt(2));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogPostTagDAO.class.getName()).log(Level.SEVERE, "Error selecting blog post tag with blog_post_id " + id[0] + " and tag_id " + id[1], ex);
        }
        return blogPostTag;
    }

    @Override
    public int insert(BlogPostTag blogPostTag) {
        String sql = "INSERT INTO [dbo].[blog_post_tags] ([blog_post_id], [tag_id]) VALUES (?, ?)";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, blogPostTag.getBlogPostId());
            pre.setInt(2, blogPostTag.getTagId());
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BlogPostTagDAO.class.getName()).log(Level.SEVERE, "Error inserting blog post tag", ex);
        }
        return result;
    }

    @Override
    public int update(BlogPostTag blogPostTag) {
        String sql = "UPDATE [dbo].[blog_post_tags] SET [tag_id] = ? WHERE [blog_post_id] = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, blogPostTag.getTagId());
            pre.setInt(2, blogPostTag.getBlogPostId());
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BlogPostTagDAO.class.getName()).log(Level.SEVERE, "Error updating blog post tag", ex);
        }
        return result;
    }

    @Override
    public int delete(int... id) {
        String sql = "DELETE FROM [dbo].[blog_post_tags] WHERE [blog_post_id] = ? AND [tag_id] = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]);
            pre.setInt(2, id[1]);
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BlogPostTagDAO.class.getName()).log(Level.SEVERE, "Error deleting blog post tag with blog_post_id " + id[0] + " and tag_id " + id[1], ex);
        }
        return result;
    }
}
