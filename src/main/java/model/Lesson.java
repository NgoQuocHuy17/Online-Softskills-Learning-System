package model;

import java.util.Date;

public class Lesson {

    private int id;
    private int courseID;
    private String title;
    private String description;
    private Date createdDate;         // New attribute
    private Date lastUpdatedDate;     // New attribute
    private boolean status;            // Changed to boolean

    public Lesson() {
    }

    public Lesson(int id, int courseID, String title, String description, Date createdDate, Date lastUpdatedDate, boolean status) {
        this.id = id;
        this.courseID = courseID;
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
        this.lastUpdatedDate = lastUpdatedDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Lesson{" + "id=" + id + ", courseID=" + courseID + ", title=" + title + ", description=" + description + ", createdDate=" + createdDate + ", lastUpdatedDate=" + lastUpdatedDate + ", status=" + status + '}';
    }
}
