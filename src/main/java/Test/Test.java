package Test;

import model.Course;
import view.CourseDAO;

import java.util.List;

public class Test {

    public static void main(String[] args) {
        CourseDAO courseDAO = new CourseDAO();

        // Test fetching all categories
        testGetAllCategories(courseDAO);

        // Test fetching courses by category with pagination
        testGetCoursesByCategory(courseDAO, "Soft Skills", 1, 5);
        
        // Test fetching total number of courses in a specific category
        testGetTotalCoursesByCategory(courseDAO, "Soft Skills");

        // Test fetching courses with no category filter (all courses)
        testGetCoursesByCategory(courseDAO, "All", 1, 5);
    }

    // Test fetching all categories
    private static void testGetAllCategories(CourseDAO courseDAO) {
        System.out.println("Testing getAllCategories...");
        List<String> categories = courseDAO.getAllCategories();
        if (categories.isEmpty()) {
            System.out.println("No categories found.");
        } else {
            categories.forEach(category -> System.out.println("Category: " + category));
        }
    }

    // Test fetching courses by category and pagination
    private static void testGetCoursesByCategory(CourseDAO courseDAO, String category, int page, int pageSize) {
        System.out.println("\nTesting getCoursesByCategory for category: " + category);
        List<Course> courses = courseDAO.getCoursesByCategory(category, page, pageSize);
        if (courses.isEmpty()) {
            System.out.println("No courses found for category: " + category);
        } else {
            courses.forEach(course -> System.out.println("Course: " + course.getTitle() + ", Category: " + course.getCategory() + ", Price: " + course.getSalePrice()));
        }
    }

    // Test fetching total number of courses by category
    private static void testGetTotalCoursesByCategory(CourseDAO courseDAO, String category) {
        System.out.println("\nTesting getTotalCoursesByCategory for category: " + category);
        int totalCourses = courseDAO.getTotalCoursesByCategory(category);
        System.out.println("Total courses in category '" + category + "': " + totalCourses);
    }
}
