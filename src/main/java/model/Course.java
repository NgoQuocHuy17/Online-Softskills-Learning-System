// File: Course.java
package model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Course {
    private int id;
    private String title;
    private String tagLine;
    private String description;
    private String category;
    private BigDecimal basicPackagePrice;
    private BigDecimal advancedPackagePrice;
    private int ownerId;
    private boolean isSponsored;
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Course(String title, String description, String category, int ownerId, boolean isSponsored, String status) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.ownerId = ownerId;
        this.isSponsored = isSponsored;
        this.status = status;
    }

    public Course() {
    }

    
    // Getters and Setters

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

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getBasicPackagePrice() {
        return basicPackagePrice;
    }

    public void setBasicPackagePrice(BigDecimal basicPackagePrice) {
        this.basicPackagePrice = basicPackagePrice;
    }

    public BigDecimal getAdvancedPackagePrice() {
        return advancedPackagePrice;
    }

    public void setAdvancedPackagePrice(BigDecimal advancedPackagePrice) {
        this.advancedPackagePrice = advancedPackagePrice;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public boolean isSponsored() {
        return isSponsored;
    }

    public void setSponsored(boolean isSponsored) {
        this.isSponsored = isSponsored;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return "Course{" + "id=" + id + ", title=" + title + ", tagLine=" + tagLine + ", description=" + description + ", category=" + category + ", basicPackagePrice=" + basicPackagePrice + ", advancedPackagePrice=" + advancedPackagePrice + ", ownerId=" + ownerId + ", isSponsored=" + isSponsored + ", status=" + status + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
    
    
}
