/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author daihi
 */
import java.sql.Timestamp;

public class CourseContent {
    private int id;
    private int courseId;
    private String content;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Constructors
    public CourseContent() {}

    public CourseContent(int courseId, String content) {
        this.courseId = courseId;
        this.content = content;
    }

    // Getters and Setters
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "CourseContent{" + "id=" + id + ", courseId=" + courseId + ", content=" + content + 
                ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
}
