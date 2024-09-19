package view;

import model.BlogPost;
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
public class BlogPostDAO extends DBContext<BlogPost> {

    @Override
    public List<BlogPost> select() {
        String sql = "SELECT [id], [title], [thumbnail_url], [category_id], [content], [author_id], [status], [created_at], [updated_at] FROM [dbo].[blog_posts]";
        List<BlogPost> blogPosts = new Vector();
        try (PreparedStatement pre = super.getConn().prepareStatement(sql); ResultSet rs = pre.executeQuery()) {
            while (rs.next()) {
                BlogPost blogPost = new BlogPost();
                blogPost.setId(rs.getInt(1));
                blogPost.setTitle(rs.getString(2));
                blogPost.setThumbnailUrl(rs.getString(3));
                blogPost.setCategoryId(rs.getInt(4));
                blogPost.setContent(rs.getString(5));
                blogPost.setAuthorId(rs.getInt(6));
                blogPost.setStatus(rs.getString(7));
                blogPost.setCreatedAt(rs.getTimestamp(8));
                blogPost.setUpdatedAt(rs.getTimestamp(9));
                blogPosts.add(blogPost);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogPostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blogPosts;
    }

    @Override
    public BlogPost select(int... id) {
        String sql = "SELECT [id], [title], [thumbnail_url], [category_id], [content], [author_id], [status], [created_at], [updated_at] FROM [dbo].[blog_posts] WHERE id = ?";
        BlogPost blogPost = null;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]);
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    blogPost = new BlogPost();
                    blogPost.setId(rs.getInt(1));
                    blogPost.setTitle(rs.getString(2));
                    blogPost.setThumbnailUrl(rs.getString(3));
                    blogPost.setCategoryId(rs.getInt(4));
                    blogPost.setContent(rs.getString(5));
                    blogPost.setAuthorId(rs.getInt(6));
                    blogPost.setStatus(rs.getString(7));
                    blogPost.setCreatedAt(rs.getTimestamp(8));
                    blogPost.setUpdatedAt(rs.getTimestamp(9));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogPostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blogPost;
    }

    @Override
    public int insert(BlogPost blogPost) {
        String sql = "INSERT INTO [dbo].[blog_posts] "
                + "([title], [thumbnail_url], [category_id], [content], [author_id], [status], [created_at], [updated_at]) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setString(1, blogPost.getTitle());
            pre.setString(2, blogPost.getThumbnailUrl());
            pre.setInt(3, blogPost.getCategoryId());
            pre.setString(4, blogPost.getContent());
            pre.setInt(5, blogPost.getAuthorId());
            pre.setString(6, blogPost.getStatus());
            pre.setTimestamp(7, new java.sql.Timestamp(blogPost.getCreatedAt().getTime()));
            pre.setTimestamp(8, new java.sql.Timestamp(blogPost.getUpdatedAt().getTime()));
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BlogPostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(BlogPost blogPost) {
        String sql = "UPDATE [dbo].[blog_posts] "
                + "SET [title] = ?, [thumbnail_url] = ?, [category_id] = ?, [content] = ?, [author_id] = ?, [status] = ?, [created_at] = ?, [updated_at] = ? "
                + "WHERE id = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setString(1, blogPost.getTitle());
            pre.setString(2, blogPost.getThumbnailUrl());
            pre.setInt(3, blogPost.getCategoryId());
            pre.setString(4, blogPost.getContent());
            pre.setInt(5, blogPost.getAuthorId());
            pre.setString(6, blogPost.getStatus());
            pre.setTimestamp(7, new java.sql.Timestamp(blogPost.getCreatedAt().getTime()));
            pre.setTimestamp(8, new java.sql.Timestamp(blogPost.getUpdatedAt().getTime()));
            pre.setInt(9, blogPost.getId());
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BlogPostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(int... id) {
        String sql = "DELETE FROM [dbo].[blog_posts] WHERE id = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]);
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BlogPostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
