package model;

import java.util.Date;

public class CourseDetail {
    private int courseId;
    private String sectionTitle;
    private String content;
    private Date createdAt;
    private Date updatedAt;
    
    public CourseDetail(){
        
    }

    public CourseDetail(int courseId, String sectionTitle, String content, Date createdAt, Date updatedAt) {
        this.courseId = courseId;
        this.sectionTitle = sectionTitle;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getSectionTitle() {
        return sectionTitle;
    }

    public void setSectionTitle(String sectionTitle) {
        this.sectionTitle = sectionTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
