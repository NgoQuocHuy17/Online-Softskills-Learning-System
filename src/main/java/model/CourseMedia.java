/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author daihi
 */
public class CourseMedia {

    private int id;
    private int courseId;
    private String mediaType;
    private String fileName;
    private String title;
    private int displayOrder;
    private boolean isThumbnail;
    private String content; // Add content field

    public CourseMedia() {

    }

    public CourseMedia(int id, int courseId, String mediaType, String fileName, String title, int displayOrder, boolean isThumbnail, String content) {
        this.id = id;
        this.courseId = courseId;
        this.mediaType = mediaType;
        this.fileName = fileName;
        this.title = title;
        this.displayOrder = displayOrder;
        this.isThumbnail = isThumbnail;
        this.content = content;
    }

    // Getter and Setter for content
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getFileName() {
        return fileName;
    }

    public String getTitle() {
        return title;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public boolean isIsThumbnail() {
        return isThumbnail;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public void setIsThumbnail(boolean isThumbnail) {
        this.isThumbnail = isThumbnail;
    }

}
