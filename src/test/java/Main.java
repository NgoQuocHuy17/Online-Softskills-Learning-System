import java.util.List;
import view.UserCourseDAO;

public class Main {
    public static void main(String[] args) {
        // Initialize DBContext (assuming you have a no-argument constructor or a setup method)
        
        // Initialize UserCourseDAO with dbContext
        UserCourseDAO userCourseDAO = new UserCourseDAO();
        
        // Test userId (replace with a valid userId from your database)
        int testUserId = 1;
        
        // Fetch course IDs for the user
        List<Integer> courseIds = userCourseDAO.getCourseIdsByUserId(testUserId);
        
        // Print the result
        System.out.println("Course IDs for user with ID " + testUserId + ": " + courseIds);
    }
}
