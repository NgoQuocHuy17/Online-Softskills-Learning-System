package model;

import java.util.Date;

public class Package {

    private int id;
    private int courseId;
    private String packageName;
    private double price;
    private double salePrice;
    private Date saleStart;
    private Date saleEnd;
    private int accessDuration;

    public Package() {
    }

    public Package(int id, int courseId, String packageName, double price, double salePrice, Date saleStart, Date saleEnd, int accessDuration) {
        this.id = id;
        this.courseId = courseId;
        this.packageName = packageName;
        this.price = price;
        this.salePrice = salePrice;
        this.saleStart = saleStart;
        this.saleEnd = saleEnd;
        this.accessDuration = accessDuration;
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

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public Date getSaleStart() {
        return saleStart;
    }

    public void setSaleStart(Date saleStart) {
        this.saleStart = saleStart;
    }

    public Date getSaleEnd() {
        return saleEnd;
    }

    public void setSaleEnd(Date saleEnd) {
        this.saleEnd = saleEnd;
    }

    public int getAccessDuration() {
        return accessDuration;
    }

    public void setAccessDuration(int accessDuration) {
        this.accessDuration = accessDuration;
    }

    // toString method
    @Override
    public String toString() {
        return "Package{"
                + "id=" + id
                + ", courseId=" + courseId
                + ", packageName='" + packageName + '\''
                + ", price=" + price
                + ", salePrice=" + salePrice
                + ", saleStart=" + saleStart
                + ", saleEnd=" + saleEnd
                + ", accessDuration=" + accessDuration
                + '}';
    }
}
