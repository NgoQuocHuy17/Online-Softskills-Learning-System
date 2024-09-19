package model;

/**
 *
 * @author Minh
 */

public class CourseTranslation {
    private int id;
    private int courseId;
    private String languageCode;
    private String title;
    private String description;

    public CourseTranslation() {
    }

    public CourseTranslation(int id, int courseId, String languageCode, String tilte, String description) {
        this.id = id;
        this.courseId = courseId;
        this.languageCode = languageCode;
        this.title = tilte;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String tilte) {
        this.title = tilte;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
