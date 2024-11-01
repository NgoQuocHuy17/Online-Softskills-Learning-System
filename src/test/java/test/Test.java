package test;

import java.util.List;
import model.BlogPost;
import model.Course;
import view.BlogPostDAO;
import view.UserCourseDAO;
import view.CourseDAO;
import controller.BlogUpdateController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Test {
    public static void main(String[] args) throws IOException {
        var strs = getThumbnails();
        System.out.println(strs.isEmpty());
        for (String str : strs) {
            System.out.println(str);
        }
    }
    
    public static List<String> getThumbnails(){
        List<String> thumbnails = null;
        try {
            thumbnails = new BlogUpdateController().retrieveThumbnails();
        } catch (IOException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        return thumbnails;
    }
}
