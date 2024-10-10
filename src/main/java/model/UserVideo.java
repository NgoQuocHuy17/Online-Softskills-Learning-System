package model;

public class UserVideo {
    private int id;            // ID của video
    private int userId;       // ID của người dùng
    private String videoUrl;   // Đường dẫn đến video

    // Constructor
    public UserVideo(int id, int userId, String videoUrl) {
        this.id = id;
        this.userId = userId;
        this.videoUrl = videoUrl;
    }

    // Getters và Setters
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

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
