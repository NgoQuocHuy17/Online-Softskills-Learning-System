package model;

/**
 *
 * @author Minh
 */

public class Setting {
    private int id;
    private String type;
    private String value;
    private int orderNum;
    private String status;

    public Setting() {
    }

    public Setting(int id, String type, String value, int orderNum, String status) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.orderNum = orderNum;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        return "Setting{" + "id=" + id + ", type=" + type + ", value=" + value + ", orderNum=" + orderNum + ", status=" + status + '}';
    }
}
