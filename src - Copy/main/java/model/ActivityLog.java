package model;

import java.util.Date;

/**
 *
 * @author Minh
 */
public class ActivityLog {

    private int id;
    private int userId;
    private String activityType;
    private String activityData;
    private Date createdAt;

    public ActivityLog() {
    }

    public ActivityLog(int id, int userId, String activityType, String activityData, Date createdAt) {
        this.id = id;
        this.userId = userId;
        this.activityType = activityType;
        this.activityData = activityData;
        this.createdAt = createdAt;
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

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityData() {
        return activityData;
    }

    public void setActivityData(String activityData) {
        this.activityData = activityData;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
