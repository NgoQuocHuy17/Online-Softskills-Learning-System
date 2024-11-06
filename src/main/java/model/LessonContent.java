package model;

import java.util.Date;

public class LessonContent {
    private int id;
    private int lessonId;
    private String contentURL;
    private String contentType;
    private String contentDescription;
    private String textContent;
    private int orderInLesson;
    private byte[] mediaData; // For storing binary data (e.g., images, PDFs)
    private Date createdDate;

    // Full constructor
    public LessonContent(int id, int lessonId, String contentURL, String contentType, String contentDescription,
                         String textContent, int orderInLesson, byte[] mediaData, Date createdDate) {
        this.id = id;
        this.lessonId = lessonId;
        this.contentURL = contentURL;
        this.contentType = contentType;
        this.contentDescription = contentDescription;
        this.textContent = textContent;
        this.orderInLesson = orderInLesson;
        this.mediaData = mediaData;
        this.createdDate = createdDate;
    }

    // Constructor without ID and createdDate (e.g., for new records)
    public LessonContent(int lessonId, String contentURL, String contentType, String contentDescription,
                         String textContent, int orderInLesson, byte[] mediaData) {
        this.lessonId = lessonId;
        this.contentURL = contentURL;
        this.contentType = contentType;
        this.contentDescription = contentDescription;
        this.textContent = textContent;
        this.orderInLesson = orderInLesson;
        this.mediaData = mediaData;
    }

    public LessonContent() {
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getContentURL() {
        return contentURL;
    }

    public void setContentURL(String contentURL) {
        this.contentURL = contentURL;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentDescription() {
        return contentDescription;
    }

    public void setContentDescription(String contentDescription) {
        this.contentDescription = contentDescription;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public int getOrderInLesson() {
        return orderInLesson;
    }

    public void setOrderInLesson(int orderInLesson) {
        this.orderInLesson = orderInLesson;
    }

    public byte[] getMediaData() {
        return mediaData;
    }

    public void setMediaData(byte[] mediaData) {
        this.mediaData = mediaData;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
