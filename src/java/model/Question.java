package model;

import java.util.Date;

/**
 *
 * @author Minh
 */

public class Question {
    private int id;
    private int courseId;
    private String content;
    private String mediaUrl;
    private String level;
    private Date createdAt;
    private Date updatedAt;

    public Question() {
    }

    public Question(int id, int courseId, String content, String mediaUrl, String level, Date createdAt, Date updatedAt) {
        this.id = id;
        this.courseId = courseId;
        this.content = content;
        this.mediaUrl = mediaUrl;
        this.level = level;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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
