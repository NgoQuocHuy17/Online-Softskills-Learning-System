package model;

import java.util.Date;

/**
 *
 * @author Minh
 */
public class Quiz {

    private int id;
    private int courseId;
    private String title;
    private int duration;
    private double passRate;
    private Date createdAt;
    private Date updatedAt;

    public Quiz() {
    }

    public Quiz(int id, int courseId, String title, int duration, double passRate, Date createdAt, Date updatedAt) {
        this.id = id;
        this.courseId = courseId;
        this.title = title;
        this.duration = duration;
        this.passRate = passRate;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPassRate() {
        return passRate;
    }

    public void setPassRate(double passRate) {
        this.passRate = passRate;
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
