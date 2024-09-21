package model;

import java.util.Date;

/**
 *
 * @author Minh
 */
public class Course {

    private int id;
    private String title;
    private String tagLine;
    private String description;
    private String category;
    private double listPrice;
    private double salePrice;
    private int ownerId;
    private String status;
    private Date createdAt;
    private Date updatedAt;

    public Course() {
    }

    public Course(int id, String title, String tagLine, String description, String category, double listPrice, double salePrice, int ownerId, String status, Date createdAt, Date updatedAt) {
        this.id = id;
        this.title = title;
        this.tagLine = tagLine;
        this.description = description;
        this.category = category;
        this.listPrice = listPrice;
        this.salePrice = salePrice;
        this.ownerId = ownerId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

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

    public double getListPrice() {
        return listPrice;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
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
