package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.CourseDAO;
import view.CourseMediaDAO;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import model.Course;
import model.CourseContent;
import model.CourseMedia;
import view.CourseContentDAO;

@WebServlet(name = "SubjectDetailServlet", urlPatterns = {"/subjectDetail"})
public class SubjectDetailServlet extends HttpServlet {

    private final CourseDAO courseDAO = new CourseDAO();
    private final CourseMediaDAO courseMediaDAO = new CourseMediaDAO();
    private final CourseContentDAO courseContentDAO = new CourseContentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "edit":
                editCourseDetail(request, response);
                break;
            case "list":
            default:
                listCourseDetails(request, response);
                break;
        }
    }

    private void listCourseDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Course> courses = courseDAO.selectAll();
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("/subjectList.jsp").forward(request, response);
    }

    private void editCourseDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        Course course = courseDAO.select(courseId);
        List<CourseMedia> mediaList = courseMediaDAO.selectByCourseId(courseId);

        request.setAttribute("course", course);
        request.setAttribute("mediaList", mediaList);
        request.getRequestDispatcher("/editSubjectDetail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "update":
                updateCourseDetail(request, response);
                break;
            case "toggleStatus":
                toggleCourseStatus(request, response);
                break;
        }
    }

    private void updateCourseDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        Course course = courseDAO.select(courseId);

        // Update Course data from the form
        course.setTitle(request.getParameter("title"));
        course.setDescription(request.getParameter("description"));
        course.setCategory(request.getParameter("category"));
        course.setBasicPackagePrice(new BigDecimal(request.getParameter("basicPackagePrice")));
        course.setAdvancedPackagePrice(new BigDecimal(request.getParameter("advancedPackagePrice")));
        course.setStatus(request.getParameter("status"));
        course.setSponsored(request.getParameter("isSponsored") != null);
        courseDAO.update(course);

        // Update Media details
        updateMediaDetails(request, courseId);

        updateContentDetails(request, courseId);

        // Redirect after processing
        response.sendRedirect("subjectDetail?action=list");
    }

    private void updateMediaDetails(HttpServletRequest request, int courseId) {
        String[] mediaIds = request.getParameterValues("mediaId");
        String[] mediaTitles = request.getParameterValues("mediaTitle");
        String[] mediaTypes = request.getParameterValues("mediaType");
        String[] fileNames = request.getParameterValues("fileName");
        String[] displayOrders = request.getParameterValues("displayOrder");

        if (mediaIds != null) {
            for (int i = 0; i < mediaIds.length; i++) {
                String mediaId = mediaIds[i];
                CourseMedia media;

                // Determine if it's a new or existing media entry
                if ("new".equals(mediaId)) {
                    media = new CourseMedia();
                    media.setCourseId(courseId);
                } else {
                    media = courseMediaDAO.select(Integer.parseInt(mediaId));
                }

                // Update media details
                media.setTitle(mediaTitles[i]);
                media.setMediaType(mediaTypes[i]);
                media.setFileName(fileNames[i]);
                media.setDisplayOrder(Integer.parseInt(displayOrders[i]));

                // Insert new media or update existing
                if ("new".equals(mediaId)) {
                    courseMediaDAO.insert(media);
                } else {
                    courseMediaDAO.update(media);
                }
            }
        }
    }

    private void updateContentDetails(HttpServletRequest request, int courseId) {
        String[] contentIds = request.getParameterValues("contentId");
        String[] contents = request.getParameterValues("content");

        if (contentIds != null) {
            for (int i = 0; i < contentIds.length; i++) {
                String contentId = contentIds[i];
                CourseContent content;

                // Nếu contentId là "new" thì thêm mới, nếu không thì cập nhật content hiện có
                if ("new".equals(contentId)) {
                    content = new CourseContent();
                    content.setCourseId(courseId);
                } else {
                    content = courseContentDAO.select(Integer.parseInt(contentId)); // Lấy content hiện tại
                }

                // Cập nhật nội dung cho content
                content.setContent(contents[i]);

                // Chèn mới hoặc cập nhật content dựa trên giá trị contentId
                if ("new".equals(contentId)) {
                    courseContentDAO.insert(content); // Thêm mới
                } else {
                    courseContentDAO.update(content); // Cập nhật content đã có
                }
            }
        }
    }

    private void toggleCourseStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        Course course = courseDAO.select(courseId);
        String newStatus = course.getStatus().equals("Published") ? "Unpublished" : "Published";
        course.setStatus(newStatus);
        courseDAO.update(course);
        response.sendRedirect("subjectDetail?action=list");
    }
}
