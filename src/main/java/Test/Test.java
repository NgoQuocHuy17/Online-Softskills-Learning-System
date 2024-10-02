package Test;
import view.*;
import java.util.List;
import model.Course;

public class Test {
    public static void main(String[] args) {
        try {
            // Create an instance of CourseDAO
            CourseDAO courseDAO = new CourseDAO();
            
            // Call the method with size = 6
            List<Course> featuredCourses = courseDAO.getFeaturedCourses(6);
            
            // Check and print the results
            if (featuredCourses != null && !featuredCourses.isEmpty()) {
                System.out.println("Featured Courses (size = 6):");
                for (Course course : featuredCourses) {
                    System.out.println("ID: " + course.getId());
                    System.out.println("Title: " + course.getTitle());
                    System.out.println("Tag Line: " + course.getTagLine());
                    System.out.println("Category: " + course.getCategory());
                    System.out.println("Is Sponsored: " + course.isSponsored());
                    System.out.println("------------------------------------------------");
                }
                System.out.println("Total featured courses found: " + featuredCourses.size());
            } else {
                System.out.println("No featured courses found. This could be due to:");
                System.out.println("1. No courses marked as sponsored in the database.");
                System.out.println("2. Database connection issues.");
                System.out.println("3. Unexpected query results.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while running the test:");
            e.printStackTrace();
        }
    }
}