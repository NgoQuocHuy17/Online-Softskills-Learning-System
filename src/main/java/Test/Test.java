package Test;

import model.CourseDetail;
import java.util.List;
import view.*;

public class Test {
    public static void main(String[] args) {
        CourseDetailDAO dao = new CourseDetailDAO();

        // Test select all course details
        System.out.println("=== All Course Details ===");
        List<CourseDetail> courseDetails = dao.select();
        for (CourseDetail detail : courseDetails) {
            printCourseDetail(detail);
        }

        // Test select course detail by courseId
        int courseIdToTest = 14; // Change this to the courseId you want to test
        System.out.println("\n=== Course Detail with courseId: " + courseIdToTest + " ===");
        CourseDetail courseDetail = dao.select(courseIdToTest);
        if (courseDetail != null) {
            printCourseDetail(courseDetail);
        } else {
            System.out.println("No course detail found with courseId: " + courseIdToTest);
        }
    }

    // Utility method to print course detail information
    private static void printCourseDetail(CourseDetail detail) {
        System.out.println("Course ID: " + detail.getCourseId());
        System.out.println("Section Title: " + detail.getSectionTitle());
        System.out.println("Content: " + detail.getContent());
        System.out.println("Image Path: " + detail.getImagePath());
        System.out.println("Video URL: " + (detail.getVideoUrl() != null ? detail.getVideoUrl() : "N/A"));
        System.out.println("Created At: " + detail.getCreatedAt());
        System.out.println("Updated At: " + detail.getUpdatedAt());
        System.out.println("-----------------------------");
    }
}
