package model;

/**
 *
 * @author Minh
 */
public class UserContact {

    private int id;
    private int userId;
    private String contactType;
    private String contactValue;

    public UserContact() {
    }

    public UserContact(int id, int userId, String contactType, String contactValue) {
        this.id = id;
        this.userId = userId;
        this.contactType = contactType;
        this.contactValue = contactValue;
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
}
