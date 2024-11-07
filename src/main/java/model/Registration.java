package model;

import java.sql.Date;

public class Registration {

    private int id;
    private int userId;
    private int packageId;
    private int courseId;
    private int createdBy;
    private double totalCost;
    private String status;
    private Date validFrom;
    private Date validTo;
    private String notes;

    public Registration() {
    }

    public Registration(int id, int userId, int packageId, int courseId, int createdBy, double totalCost,
            String status, Date validFrom, Date validTo, String notes) {
        this.id = id;
        this.userId = userId;
        this.packageId = packageId;
        this.courseId = courseId;
        this.createdBy = createdBy;
        this.totalCost = totalCost;
        this.status = status;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.notes = notes;
    }

    // Getters and Setters
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

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Registration{"
                + "id=" + id
                + ", userId=" + userId
                + ", packageId=" + packageId
                + ", courseId=" + courseId
                + ", createdBy=" + createdBy
                + ", totalCost=" + totalCost
                + ", status='" + status + '\''
                + ", validFrom=" + validFrom
                + ", validTo=" + validTo
                + ", notes='" + notes + '\''
                + '}';
    }
}
