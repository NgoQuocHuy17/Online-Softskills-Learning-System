package model;

public class RegistrationMedia {

    private int id;
    private int registrationId;
    private String mediaType;
    private String mediaData;
    private String note;  // Thêm thuộc tính note

    public RegistrationMedia() {
    }

    public RegistrationMedia(int id, int registrationId, String mediaType, String mediaData) {
        this.id = id;
        this.registrationId = registrationId;
        this.mediaType = mediaType;
        this.mediaData = mediaData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(int registrationId) {
        this.registrationId = registrationId;
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
