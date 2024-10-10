
import java.util.List;
import model.Course;
import view.CourseDAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ngoqu
 */
public class NewClass {

    public static void main(String[] args) {
        CourseDAO a = new CourseDAO();
        List<Course> b = a.getAllCourses();
        for (Course course : b) {
            System.out.println(course);
        }
    }

}
