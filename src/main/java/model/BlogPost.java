package model;

import java.time.LocalDateTime;

public class BlogPost {

    private int id;
    private String title;
    private String thumbnailUrl;
    private String category;
    private String content;
    private int authorId;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int categoryId;

    public BlogPost() {
    }

    public BlogPost(int id, String title, String thumbnailUrl, String content, int authorId, String status, 
            LocalDateTime createdAt, LocalDateTime updatedAt, int categoryId) {
        this.id = id;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.content = content;
        this.authorId = authorId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    
    // Getters and setters
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

    public String getCategoryName() {
        return category;
    }

    public void setCategoryName(String categoryId) {
        this.category = categoryId;
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
        return "BlogPost{" + "id=" + id + ", title=" + title + ", thumbnailUrl=" + thumbnailUrl + ", category=" + category + ", content=" + content + ", authorId=" + authorId + ", status=" + status + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", categoryId=" + categoryId + '}';
    }   
}
