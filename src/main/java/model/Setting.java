package model;

/**
 *
 * @author Minh
 */

public class Setting {
    private int id;
    private String settingType;
    private String value;
    private int orderNum;
    private String status;
    private String type;

    public Setting() {
    }

    public Setting(int id, String value, int orderNum, String status, String type) {
        this.id = id;
        this.value = value;
        this.orderNum = orderNum;
        this.status = status;
        this.type = type;
    }

    public Setting(int id, String settingType, String value, int orderNum, String status) {
        this.id = id;
        this.settingType = settingType;
        this.value = value;
        this.orderNum = orderNum;
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSettingType() {
        return settingType;
    }

    public void setSettingType(String settingType) {
        this.settingType = settingType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
