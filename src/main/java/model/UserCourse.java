package model;

import java.util.Date;

/**
 *
 * @author Minh
 */

public class UserCourse {
    private int userId;
    private int courseId;
    private String role;
    private String status;
    private Date createdAt;
    private Date updatedAt;

    public UserCourse() {
    }

    public UserCourse(int userId, int courseId, String role, String status, Date createdAt, Date updatedAt) {
        this.userId = userId;
        this.courseId = courseId;
        this.role = role;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
