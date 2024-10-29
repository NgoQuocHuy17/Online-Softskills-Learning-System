package view;

import model.CourseContent;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        CourseContentDAO contentDAO = new CourseContentDAO();
        
        // Test lấy content theo courseId
        int courseId = 1; // Thay đổi giá trị courseId này nếu muốn thử với các giá trị khác
        List<CourseContent> contents = contentDAO.selectByCourseId(courseId);
        
        // Kiểm tra kết quả truy vấn
        if (contents.isEmpty()) {
            System.out.println("No content found for courseId: " + courseId);
        } else {
            System.out.println("Contents for courseId " + courseId + ":");
            for (CourseContent content : contents) {
                System.out.println("ID: " + content.getId());
                System.out.println("Content: " + content.getContent());
                System.out.println("Created At: " + content.getCreatedAt());
                System.out.println("Updated At: " + content.getUpdatedAt());
                System.out.println("-------------");
            }
        }
    }
}
