package model;

import java.util.Date;

/**
 *
 * @author Minh
 */

public class Registration {
    private int id;
    private int userId;
    private int courseId;
    private String packageName;
    private double totalCost;
    private String status;
    private Date validFrom;
    private Date validTo;
    private Date createdAt;
    private Date updatedAt;

    public Registration() {
    }

    public Registration(int id, int userId, int courseId, String packageName, double totalCost, String status, Date validFrom, Date validTo, Date createdAt, Date updatedAt) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.packageName = packageName;
        this.totalCost = totalCost;
        this.status = status;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
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
