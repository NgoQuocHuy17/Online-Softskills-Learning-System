package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Lesson;
import view.LessonDAO;

@WebServlet(name = "LessonListController", urlPatterns = {"/LessonListController"})
public class LessonListController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
            // Set visibility preferences as attributes, defaulting to true
           
                          request.setAttribute("showLessonNumber", showLessonNumber);
                          request.setAttribute("showLessonName", showLessonName);
                         request.setAttribute("showDescription", showDescription);
                       request.setAttribute("showStatus", showStatus);
                              request.setAttribute("showActions", showActions);

            // Filtering lessons based on status
            if (status.equalsIgnoreCase("") || status == null || status.equalsIgnoreCase("All")) {
                filteredLessons = allLessons; // Show all lessons
            } else if ("Active".equalsIgnoreCase(status)) {
                for (Lesson lesson : allLessons) {
                    if (lesson.isStatus()) { // If lesson is active
                        filteredLessons.add(lesson);
                    }
                }
            } else if ("Inactive".equalsIgnoreCase(status)) {
                for (Lesson lesson : allLessons) {
                    if (!lesson.isStatus()) { // If lesson is inactive
                        filteredLessons.add(lesson);
                    }
                }
            }

            boolean showEmpty = filteredLessons.isEmpty();
            request.setAttribute("showEmpty", showEmpty);
            List<Lesson> paginatedLessons = null;
            int totalPages = 1;
            if (!showEmpty) {
                int totalLessons = filteredLessons.size();
                totalPages = (int) Math.ceil((double) totalLessons / pageSize);
                int fromIndex = Math.max(0, Math.min((page - 1) * pageSize, totalLessons - 1));
                int toIndex = Math.min(fromIndex + pageSize, totalLessons);

                paginatedLessons = filteredLessons.subList(fromIndex, toIndex);
            }
            request.setAttribute("lessons", paginatedLessons);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("courseId", courseId);
            request.setAttribute("itemsPerPage", pageSize);
            request.setAttribute("status", status);

        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("LessonList.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        String toggleStatus = request.getParameter("toggleStatus");
        String lessonIdParam = request.getParameter("lessonId");
        String status = request.getParameter("status") != null ? request.getParameter("status") : "All";

        if (toggleStatus != null && lessonIdParam != null) {
            int lessonId = Integer.parseInt(lessonIdParam);
            LessonDAO lessonDAO = new LessonDAO();
            Lesson lesson = lessonDAO.getLessonById(lessonId);

            if (lesson != null) {
                lesson.setStatus("activate".equalsIgnoreCase(toggleStatus));
                lessonDAO.updateLesson(lesson);
            }

        }
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Lesson List Controller Servlet";
    }
}
