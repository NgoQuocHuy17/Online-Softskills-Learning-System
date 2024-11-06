package view;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;

public class BlogPostDAO extends DBContext<BlogPost> {

    public boolean changeStatus(int id, String status) {
        
        String oppositeStatus = switch (status){
            case "active" -> "Active";
            case "inactive" -> "Inactive";
            default -> "Inactive";
        };
        
        String sql = "UPDATE [dbo].[blog_posts]\n"
                + "   SET\n"
                + "      [status] = ?\n"
                + " WHERE id = ?";
        
        int result = 0;
        
        try (Connection conn = getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, oppositeStatus);
            ps.setInt(2, id);

            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    public String getCategoryNameById(int categoryId) {
        String name = "";
        String sql = "SELECT name FROM categories WHERE id = ?";

        try (Connection conn = getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, categoryId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    name = rs.getString("name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    public List<BlogPost> getBlogPostsByPage(int pageNumber, int pageSize) {
        List<BlogPost> blogPosts = new ArrayList<>();
        int offset = (pageNumber - 1) * pageSize;
        String sql = "SELECT * FROM blog_posts ORDER BY created_at DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setInt(1, offset);
            ps.setInt(2, pageSize);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    BlogPost post = new BlogPost();
                    post.setId(rs.getInt("id"));
                    post.setTitle(rs.getString("title"));
                    post.setThumbnailUrl(rs.getString("thumbnail_url"));
                    post.setCategoryName(getCategoryNameById(rs.getInt("category_id")));
                    post.setContent(rs.getString("content"));
                    post.setAuthorId(rs.getInt("author_id"));
                    post.setStatus(rs.getString("status"));
                    post.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                    post.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());

                    blogPosts.add(post);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogPosts;
    }

    public int getTotalBlogPostCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM blog_posts";

        try (Connection conn = getConn(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT id, name FROM categories";

        try (Connection conn = getConn(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public List<BlogPost> getBlogPostsByCategories(List<Integer> categoryIds, int pageNumber, int pageSize) {
        List<BlogPost> blogPosts = new ArrayList<>();
        int offset = (pageNumber - 1) * pageSize;

        StringBuilder sql = new StringBuilder("SELECT * FROM blog_posts ");
        if (categoryIds != null && !categoryIds.isEmpty()) {
            sql.append("WHERE category_id IN (");
            for (int i = 0; i < categoryIds.size(); i++) {
                sql.append("?");
                if (i < categoryIds.size() - 1) {
                    sql.append(", ");
                }
            }
            sql.append(") ");
        }
        sql.append("ORDER BY created_at DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

        try (Connection conn = getConn(); PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            int paramIndex = 1;
            if (categoryIds != null && !categoryIds.isEmpty()) {
                for (int categoryId : categoryIds) {
                    ps.setInt(paramIndex++, categoryId);
                }
            }
            ps.setInt(paramIndex++, offset);
            ps.setInt(paramIndex, pageSize);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    BlogPost post = new BlogPost();
                    post.setId(rs.getInt("id"));
                    post.setTitle(rs.getString("title"));
                    post.setThumbnailUrl(rs.getString("thumbnail_url"));
                    post.setCategoryName(getCategoryNameById(rs.getInt("category_id")));
                    post.setContent(rs.getString("content"));
                    post.setAuthorId(rs.getInt("author_id"));
                    post.setStatus(rs.getString("status"));
                    post.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                    post.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());

                    blogPosts.add(post);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogPosts;
    }

    @Override
    public List<BlogPost> select() {
        String sql = "SELECT [id], [title], [thumbnail_url], [category_id], [content], [author_id], [status], [created_at], [updated_at] FROM [dbo].[blog_posts]";
        List<BlogPost> blogPosts = new Vector<>();
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
                blogPost.setCreatedAt(rs.getTimestamp(8).toLocalDateTime());
                blogPost.setUpdatedAt(rs.getTimestamp(9).toLocalDateTime());
                blogPosts.add(blogPost);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogPostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blogPosts;
    }

    @Override
    public BlogPost select(int... id) {
        String sql = "SELECT * FROM [dbo].[blog_posts] WHERE id = ?";
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
                    blogPost.setCreatedAt(rs.getTimestamp(8).toLocalDateTime());
                    blogPost.setUpdatedAt(rs.getTimestamp(9).toLocalDateTime());
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
            pre.setTimestamp(7, Timestamp.valueOf(blogPost.getCreatedAt()));
            pre.setTimestamp(8, Timestamp.valueOf(blogPost.getCreatedAt()));
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
            pre.setTimestamp(7, Timestamp.valueOf(blogPost.getCreatedAt()));
            pre.setTimestamp(8, Timestamp.valueOf(blogPost.getCreatedAt()));
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
