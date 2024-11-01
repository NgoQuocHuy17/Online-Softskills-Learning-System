package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import model.Lesson;
import model.LessonContent;
import view.LessonDAO;
import view.LessonContentDAO;

@WebServlet(name = "LessonActionController", urlPatterns = {"/LessonActionController"})
@MultipartConfig
public class LessonActionController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("update".equals(action)) {
            updateLesson(request, response);
        } else if ("updateContent".equals(action)) {
            updateLessonContent(request, response);
        } else if ("deleteContent".equals(action)) {
            deleteLessonContent(request, response);
        } else if ("addContent".equals(action)) {
            addLessonContent(request, response);
        }
    }

    private void updateLesson(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int lessonId = Integer.parseInt(request.getParameter("lessonId"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        LessonDAO lessonDAO = new LessonDAO();
        Lesson lesson = lessonDAO.getLessonById(lessonId);
        if (lesson != null) {
            lesson.setTitle(title);
            lesson.setDescription(description);
            lessonDAO.updateLesson(lesson);
        }

        response.sendRedirect("LessonDetailController?lessonID=" + lessonId);
    }

    private void updateLessonContent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int contentId = Integer.parseInt(request.getParameter("id"));
        LessonContentDAO contentDAO = new LessonContentDAO();
        LessonContent content = contentDAO.getContentById(contentId);

        if (content != null) {
            String contentType = content.getContentType();

            String contentDescription = request.getParameter("contentdes");
            content.setContentDescription(contentDescription);

            // Define the absolute path to save files locally
            String uploadPath = "D:/SWP_NHOM_4/target/OnlineSoftskillsLearningSystem-1.0-SNAPSHOT/assets/img";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir(); // Create the directory if it doesn’t exist
            }

            // Handle different content types
            if ("image".equals(contentType) || "pdf".equals(contentType)) {
                Part filePart = request.getPart("file"); // Adjust to match the form's file input name
                if (filePart != null && filePart.getSize() > 0) {
                    String originalFileName = filePart.getSubmittedFileName();

                    // Generate a unique file name to avoid collisions
                    String uniqueFileName = originalFileName;
                    String filePath = uploadPath + File.separator + uniqueFileName;

                    filePart.write(filePath);

                    // Update content URL with a relative path for accessibility
                    content.setContentURL(uniqueFileName);
                }
            } else if ("text".equals(contentType)) {
                String textContent = request.getParameter("textContent");
                content.setTextContent(textContent);
            } else if ("quiz".equals(contentType) || "video".equals(contentType)) {
                String contentURL = request.getParameter("contentURL");
                content.setContentURL(contentURL);
            }

            // Update the content in the database
            contentDAO.updateContentById(content);
        }

        int lessonId = Integer.parseInt(request.getParameter("lessonId"));
        response.sendRedirect("LessonDetailController?lessonID=" + lessonId);
    }

    private void deleteLessonContent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int contentId = Integer.parseInt(request.getParameter("id"));
        LessonContentDAO contentDAO = new LessonContentDAO();
        contentDAO.deleteContentById(contentId);

        int lessonId = Integer.parseInt(request.getParameter("lessonId"));
        response.sendRedirect("LessonDetailController?lessonID=" + lessonId);
    }

    private void addLessonContent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int lessonId = Integer.parseInt(request.getParameter("lessonId"));
        String contentType = request.getParameter("contentType");
        String contentDescription = request.getParameter("contentDescription");

        LessonContentDAO contentDAO = new LessonContentDAO();

        // Get the maximum order for the current lesson
        int maxOrder = contentDAO.getMaxOrderForLesson(lessonId);

        LessonContent content = new LessonContent(0, "", "", "", "", "", "", maxOrder + 1); // Set order to maxOrder + 1

        content.setLessonId(lessonId);
        content.setContentType(contentType);
        content.setContentDescription(contentDescription);

        // Set content details based on content type
        if ("image".equals(contentType) || "pdf".equals(contentType)) {
            Part filePart = request.getPart("file");
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = filePart.getSubmittedFileName();
                String uploadPath = "D:/SWP_NHOM_4/target/OnlineSoftskillsLearningSystem-1.0-SNAPSHOT/assets/img";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String filePath = uploadPath + File.separator + fileName;
                filePart.write(filePath);
                content.setContentURL(fileName);
            }
        } else if ("video".equals(contentType) || "quiz".equals(contentType)) {
            String contentURL = request.getParameter("contentURL");
            content.setContentURL(contentURL);
        } else if ("text".equals(contentType)) {
            String textContent = request.getParameter("textContent");
            content.setTextContent(textContent);
        }

        // Add content to the database
        contentDAO.addContent(content);

        // Redirect to lesson details page
        response.sendRedirect("LessonDetailController?lessonID=" + lessonId);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Lesson Action Controller";
    }
}
