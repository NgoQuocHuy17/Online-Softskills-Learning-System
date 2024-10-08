package model;

public class UserContact {

    private int id;
    private int userId;
    private String contactType;
    private String contactValue;
    private boolean isPreferred; // Cột mới để lưu trạng thái phương thức liên hệ yêu thích

    public UserContact() {
    }

    public UserContact(int id, int userId, String contactType, String contactValue, boolean isPreferred) {
        this.id = id;
        this.userId = userId;
        this.contactType = contactType;
        this.contactValue = contactValue;
        this.isPreferred = isPreferred; // Khởi tạo trạng thái yêu thích
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

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getContactValue() {
        return contactValue;
    }

    public void setContactValue(String contactValue) {
        this.contactValue = contactValue;
    }

    public boolean isPreferred() { // Phương thức để kiểm tra trạng thái yêu thích
        return isPreferred;
    }

    public void setPreferred(boolean isPreferred) { // Phương thức để cập nhật trạng thái yêu thích
        this.isPreferred = isPreferred;
    }
}
