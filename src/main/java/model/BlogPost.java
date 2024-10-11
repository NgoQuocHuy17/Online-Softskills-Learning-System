package model;

import java.time.LocalDateTime;

/**
 *
 * @author Minh
 */

public class BlogPost {
    private int id;
    private String title;
    private String thumbnailUrl;
    private int categoryId;
    private String content;
    private int authorId;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BlogPost() {
    }

    public BlogPost(int id, String title, String thumbnailUrl, int category_id, String content, int authorId, String status, 
            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.categoryId = category_id;
        this.content = content;
        this.authorId = authorId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int category_id) {
        this.categoryId = category_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "BlogPost{" + "id=" + id + ", title=" + title + ", thumbnailUrl=" + thumbnailUrl + ", categoryId=" + categoryId + ", content=" + content + ", authorId=" + authorId + ", status=" + status + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
}