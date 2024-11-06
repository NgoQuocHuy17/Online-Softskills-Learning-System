package model;

public class UserMedia {

    private int id;
    private int userId;
    private String mediaType;
    private String mediaData;
    private String note;

    public UserMedia() {
    }

    public UserMedia(int id, int userId, String mediaType, String mediaData, String note) {
        this.id = id;
        this.userId = userId;
        this.mediaType = mediaType;
        this.mediaData = mediaData;
        this.note = note;
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

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaData() {
        return mediaData;
    }

    public void setMediaData(String mediaData) {
        this.mediaData = mediaData;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
