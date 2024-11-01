package model;

import java.util.Date;

public class LessonContent {
    private int id;
    private int lessonId;
    private String contentURL;
    private String contentType; // New field
    private String contentDescription;
    private String imageURL; // New field
    private String videoURL; // New field
    private String textContent; // New field
    private int orderInLesson; // New field
    private Date createdDate; // New field

    public LessonContent(int id, int lessonId, String contentURL, String contentType, String contentDescription, 
                         String imageURL, String videoURL, String textContent, int orderInLesson, Date createdDate) {
        this.id = id;
        this.lessonId = lessonId;
        this.contentURL = contentURL;
        this.contentType = contentType;
        this.contentDescription = contentDescription;
        this.imageURL = imageURL;
        this.videoURL = videoURL;
        this.textContent = textContent;
        this.orderInLesson = orderInLesson;
        this.createdDate = createdDate;
    }

    public LessonContent(int lessonId, String contentURL, String contentType, String contentDescription, String imageURL, String videoURL, String textContent, int orderInLesson) {
        this.lessonId = lessonId;
        this.contentURL = contentURL;
        this.contentType = contentType;
        this.contentDescription = contentDescription;
        this.imageURL = imageURL;
        this.videoURL = videoURL;
        this.textContent = textContent;
        this.orderInLesson = orderInLesson;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
