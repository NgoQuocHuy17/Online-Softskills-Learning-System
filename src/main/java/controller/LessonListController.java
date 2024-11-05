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

/**
 *
 * @author ngoqu
 */
@WebServlet(name = "LessonListController", urlPatterns = {"/lesson-list"})
public class LessonListController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));

        int page = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));

        // Get the items per page from the request, default to 10 if not provided
        int pageSize = Integer.parseInt(request.getParameter("itemsPerPage") == null ? "10" : request.getParameter("itemsPerPage"));
        String status = request.getParameter("status");
        if (status == null) {
            status = "All"; // Default to "All" if status is not set
        }

        // Get all lessons based on course ID
        LessonDAO lessonDAO = new LessonDAO();
        List<Lesson> allLessons = lessonDAO.getLessonsByCourseId(courseId);
         if(!allLessons.isEmpty()){
        // List for filtered lessons
        List<Lesson> filteredLessons = new ArrayList<>();

        // Filtering lessons based on the status
        if (status == null || status.equalsIgnoreCase("All")) {
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

        // Check if there are no lessons to display
        boolean showEmpty = filteredLessons.isEmpty() && "Inactive".equalsIgnoreCase(status);
        request.setAttribute("showEmpty", showEmpty);

        // If there are lessons, proceed with pagination
        if (!showEmpty) {
            // Update total lessons after filtering
            int totalLessons = filteredLessons.size();
            // Calculate total pages
            int totalPages = (int) Math.ceil((double) totalLessons / pageSize);

            // Calculate the indices for pagination
            int fromIndex = (page - 1) * pageSize;
            if (fromIndex >= totalLessons) {
                fromIndex = totalLessons - 1; // Ensure fromIndex is valid
            }
            int toIndex = Math.min(fromIndex + pageSize, totalLessons);

            // Get paginated lessons
            List<Lesson> paginatedLessons = filteredLessons.subList(fromIndex, toIndex);

            // Pass the necessary attributes to the JSP
            request.setAttribute("lessons", paginatedLessons);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("courseId", courseId);
            request.setAttribute("itemsPerPage", pageSize); // Add this line to send itemsPerPage to JSP
            request.setAttribute("status", status); // Pass the status back to JSP
        }
         }
        RequestDispatcher dispatcher = request.getRequestDispatcher("lesson-list.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        String toggleStatus = request.getParameter("toggleStatus");
        String lessonIdParam = request.getParameter("lessonId");
        String status = request.getParameter("status");
         if (status == null||status.isBlank()||status.isEmpty()) {
            status = "All"; 
        }
        // Check if this is a status toggle request
        if (toggleStatus != null && lessonIdParam != null) {
            int lessonId = Integer.parseInt(lessonIdParam);
            LessonDAO lessonDAO = new LessonDAO();

            // Get the current lesson
            Lesson lesson = lessonDAO.getLessonById(lessonId);

            if (lesson != null) {
                // Toggle status based on the action specified in the form
                boolean newStatus = "activate".equalsIgnoreCase(toggleStatus);
                lesson.setStatus(newStatus);
                lessonDAO.updateLesson(lesson); // Assuming updateLesson handles the update in DB
            }

            // Redirect back to the lesson list after status change
            response.sendRedirect("lesson-list?courseId=" + courseId
                    + "&itemsPerPage=" + request.getParameter("itemsPerPage")
                    + "&page=" + request.getParameter("page") + "&status=" +status
            );

        } else {
            // If not a status change request, handle normally

            processRequest(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
