package view;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.*;

public class BlogPostDAO extends DBContext {

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

    // BlogPostDAO.java
    public List<BlogPost> getBlogPostsByPage(int pageNumber, int pageSize) {
        List<BlogPost> blogPosts = new ArrayList<>();
        int offset = (pageNumber - 1) * pageSize;
        String sql = "SELECT * FROM blog_posts ORDER BY created_at DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (Connection conn = getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

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
                    post.setCreatedAt(rs.getTimestamp("created_at"));
                    post.setUpdatedAt(rs.getTimestamp("updated_at"));

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
                    post.setCreatedAt(rs.getTimestamp("created_at"));
                    post.setUpdatedAt(rs.getTimestamp("updated_at"));

                    blogPosts.add(post);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogPosts;
    }

}
