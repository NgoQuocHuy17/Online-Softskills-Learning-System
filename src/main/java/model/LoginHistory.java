package model;

import java.util.Date;

/**
 *
 * @author Minh
 */

public class LoginHistory {
    private int id;
    private int userId;
    private Date loginTime;
    private String ipAddress;

    public LoginHistory() {
    }

    public LoginHistory(int id, int userId, Date loginTime, String ipAddress) {
        this.id = id;
        this.userId = userId;
        this.loginTime = loginTime;
        this.ipAddress = ipAddress;
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

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    
    
}
