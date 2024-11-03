package model;

public class RegistrationMedia {
    private int id;
    private int registrationId;
    private String mediaType;
    private byte[] mediaData;

    public RegistrationMedia() {
    }

    public RegistrationMedia(int id, int registrationId, String mediaType, byte[] mediaData) {
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

    public byte[] getMediaData() {
        return mediaData;
    }

    public void setMediaData(byte[] mediaData) {
        this.mediaData = mediaData;
    }
}
