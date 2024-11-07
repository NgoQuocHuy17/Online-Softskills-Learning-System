package model;

import java.time.LocalDateTime;
import java.util.Base64;

public class Slider {
    
    private int id;
    private String title;
    private byte[] imageUrl;
    private String backlink;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public Slider() {}

    public Slider(int id, String title, byte[] imageUrl, String backlink, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.backlink = backlink;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    public Slider(int id, String title, byte[] imageUrl, String backlink, String status) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.backlink = backlink;
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

    // Getters and Setters
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

    public byte[] getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(byte[] imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBacklink() {
        return backlink;
    }

    public void setBacklink(String backlink) {
        this.backlink = backlink;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Slider{" + "id=" + id + ", title=" + title + ", imageUrl=" + imageUrl + ", backlink=" + backlink + ", status=" + status + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
    
    public String getImageUrlAsBase64() {
        if (imageUrl != null) {
            return Base64.getEncoder().encodeToString(imageUrl);
        }
        return null;
    }
}
