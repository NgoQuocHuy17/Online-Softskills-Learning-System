package test;

import java.util.ArrayList;
import java.util.List;
import model.Lesson;
import model.LessonContent;
import view.LessonContentDAO;
import view.LessonDAO;

public class Test {

    public static void main(String[] args) {
        var lessons = new LessonDAO().getLessonsByCourseId(1);
        var allLessonContent = new ArrayList<List<LessonContent>>();
        for (var lesson : lessons) {
            var lessonContents = new LessonContentDAO().getAllContentByLessonId(lesson.getId());
            allLessonContent.add(lessonContents);
        }
        for (Lesson lesson : lessons) {
            System.out.println(lesson);
            for (List<LessonContent> list : allLessonContent) {
                for (LessonContent lessonContent : list) {
                    if (lessonContent.getLessonId() == lesson.getId()) {
                        System.out.println(lessonContent);
                    }
                }
            }
        }
    }
}
