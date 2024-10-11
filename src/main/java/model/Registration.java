package model;

import java.util.Date;

public class Registration {
    private int id;
    private int userId;
    private int packageId;
    private double totalCost;
    private String status;
    private Date validFrom;
    private Date validTo;

    public Registration(int id, int userId, int packageId, double totalCost, String status, Date validFrom, Date validTo) {
        this.id = id;
        this.userId = userId;
        this.packageId = packageId;
        this.totalCost = totalCost;
        this.status = status;
        this.validFrom = validFrom;
        this.validTo = validTo;
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

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
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
}
