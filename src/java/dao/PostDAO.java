package dao;

import model.Post;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostDAO extends DBContext {

    // Method to fetch hot posts (existing)
    public List<Post> getHotPosts() {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT id, title, thumbnail_url, created_at FROM blog_posts WHERE status = 'Published' ORDER BY created_at DESC";
        
        try (Connection conn = getConn();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setTitle(rs.getString("title"));
                post.setThumbnailUrl(rs.getString("thumbnail_url"));
                post.setCreatedAt(rs.getDate("created_at"));
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    
}
