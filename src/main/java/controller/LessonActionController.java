package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

        } else if ("getContent".equals(action)) {
            getContentDataById(request, response);
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

            // Handle different content types
            if ("image".equals(contentType) || "pdf".equals(contentType)|| "video".equals(contentType)) {
                Part filePart = request.getPart("file");
                if (filePart != null && filePart.getSize() > 0) {
                    // Convert file content to byte array
                    byte[] fileData = readInputStreamToByteArray(filePart.getInputStream());
                    content.setMediaData(fileData);

                    String fileName = getFileName(filePart);
                    content.setContentURL(fileName);
                }
            } else if ("text".equals(contentType)) {
                String textContent = request.getParameter("textContent");
                content.setTextContent(textContent);
            } else if ("quiz".equals(contentType) ) {
                String contentURL = request.getParameter("contentURL");
                content.setContentURL(contentURL);
            }

            // Update the content in the database
            contentDAO.updateContentById(content);
        }

        int lessonId = Integer.parseInt(request.getParameter("lessonId"));
        response.sendRedirect("LessonDetailController?lessonID=" + lessonId);
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String token : contentDisposition.split(";")) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 2, token.length() - 1);
            }
        }
        return null; // Filename not found
    }

    private void deleteLessonContent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int contentId = Integer.parseInt(request.getParameter("id"));
        LessonContentDAO contentDAO = new LessonContentDAO();
        contentDAO.deleteContentById(contentId);

        int lessonId = Integer.parseInt(request.getParameter("lessonId"));
        response.sendRedirect("LessonDetailController?lessonID=" + lessonId);
    }

    private void getContentDataById(HttpServletRequest request, HttpServletResponse response) {
        int contentId = Integer.parseInt(request.getParameter("id"));
        LessonContentDAO contentDAO = new LessonContentDAO();

        // Fetch the content data and type
        byte[] contentData = contentDAO.getContentDataById(contentId);
        String contentType = contentDAO.getContentTypeById(contentId); // Ensure this method exists and works

        if (contentData != null && contentType != null) {
            response.setContentType(contentType);
            response.setContentLength(contentData.length);

            // Add Content-Disposition header for PDF
            response.setHeader("Content-Disposition", "inline; filename=\"content.pdf\""); // Change filename as necessary

            try (OutputStream os = response.getOutputStream()) {
                os.write(contentData);
                os.flush();
            } catch (IOException e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void addLessonContent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int lessonId = Integer.parseInt(request.getParameter("lessonId"));
        String contentType = request.getParameter("contentType");
        String contentDescription = request.getParameter("contentDescription");

        LessonContentDAO contentDAO = new LessonContentDAO();

        // Get the maximum order for the current lesson
        int maxOrder = contentDAO.getMaxOrderForLesson(lessonId);

        LessonContent content = new LessonContent();

        content.setLessonId(lessonId);
        content.setContentType(contentType);
        content.setContentDescription(contentDescription);
        content.setOrderInLesson(maxOrder + 1);

        // Set content details based on content type
        if ("video".equals(contentType) ||"image".equals(contentType) || "pdf".equals(contentType)) {
            Part filePart = request.getPart("file");
            if (filePart != null && filePart.getSize() > 0) {
                // Convert file content to byte array
                byte[] fileData = readInputStreamToByteArray(filePart.getInputStream());
                content.setMediaData(fileData);
            }
        } else if ( "quiz".equals(contentType)) {
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

    // Helper method to convert InputStream to byte array
    private byte[] readInputStreamToByteArray(InputStream inputStream) throws IOException {
        try (ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
            int bytesRead;
            byte[] data = new byte[1024];
            while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, bytesRead);
            }
            return buffer.toByteArray();
        }
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
