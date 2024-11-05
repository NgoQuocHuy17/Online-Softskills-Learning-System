package model;

public class User {

    private int id;
    private String fullName;
    private String gender;
    private String email;
    private String password;
    private String role;
    private String address;
    private String hash;
    private int status;

    public User() {
    }

    public User(int id, String fullName, String gender, String email, String password, String role, String address, String hash, int status) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.role = role;
        this.address = address;
        this.hash = hash;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}

