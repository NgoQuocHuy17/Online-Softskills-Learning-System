/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Base64;

/**
 *
 * @author daihi
 */
public class CourseMedia {

    private int id;
    private int courseId;
    private String mediaType;
    private byte[] fileName;
    private String title;
    private int displayOrder;

    public CourseMedia() {

    }

    public CourseMedia(int id, int courseId, String mediaType, byte[] fileName, String title, int displayOrder) {
        this.id = id;
        this.courseId = courseId;
        this.mediaType = mediaType;
        this.fileName = fileName;
        this.title = title;
        this.displayOrder = displayOrder;
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

    public byte[] getFileName() {
        return fileName;
    }

    public String getTitle() {
        return title;
    }

    public int getDisplayOrder() {
        return displayOrder;
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

    public void setFileName(byte[] fileName) {
        this.fileName = fileName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getFileNameBase64() {
        if (fileName != null) {
            return Base64.getEncoder().encodeToString(fileName);
        }
        return null;
    }
}
