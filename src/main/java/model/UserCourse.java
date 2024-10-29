package model;

import java.util.Date;

public class UserCourse {
    private int id;
    private int userId;
    private int courseId;
    private Date accessStart;
    private Date accessEnd;

    public UserCourse() {}

    public UserCourse(int id, int userId, int courseId, Date accessStart, Date accessEnd) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.accessStart = accessStart;
        this.accessEnd = accessEnd;
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

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Date getAccessStart() {
        return accessStart;
    }

    public void setAccessStart(Date accessStart) {
        this.accessStart = accessStart;
    }

    public Date getAccessEnd() {
        return accessEnd;
    }

    public void setAccessEnd(Date accessEnd) {
        this.accessEnd = accessEnd;
    }
}
