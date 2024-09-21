package model;

import java.util.Date;

/**
 *
 * @author Minh
 */

public class Slider {
    private int id;
    private String title;
    private String imageUrl;
    private String backlink;
    private String status;
    private Date createdAt;
    private Date updatedAt;

    public Slider() {
    }

    public Slider(int id, String title, String imageUrl, String backlink, String status, Date createdAt, Date updatedAt) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.backlink = backlink;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    
}
