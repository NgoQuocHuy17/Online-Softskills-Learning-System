
import java.util.List;
import view.RegistrationDAO; // Đảm bảo import đúng package chứa RegistrationDAO
import model.Registration; // Đảm bảo import đúng package chứa Registration
import view.UserCourseDAO;

public class Main {

    public static void main(String[] args) {
        UserCourseDAO userCourseDAO = new UserCourseDAO();

        // Sample user ID, page number, and page size for testing
        int userId = 1; // Replace with a valid user ID from your database
        int page = 1;   // First page
        int pageSize = 10; // Number of courses per page

        // Call the method to get course IDs
        List<Integer> courseIds = userCourseDAO.getCourseIdsByUserId(userId, page, pageSize);

        // Print the retrieved course IDs
        if (courseIds.isEmpty()) {
            System.out.println("No courses found for user ID: " + userId);
        } else {
            System.out.println("Course IDs for user ID " + userId + " on page " + page + ":");
            for (int id : courseIds) {
                System.out.println(id);
            }
        }
    }
}
