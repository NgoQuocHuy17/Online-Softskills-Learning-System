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
            case "remove":
                removeSingleMedia(request, response);
                break;
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
        CourseContent content = courseContentDAO.select(courseId); // Lấy content đơn
        List<CourseMedia> mediaList = courseMediaDAO.selectByCourseId(courseId); // Lấy media list

        request.setAttribute("course", course);
        request.setAttribute("content", content != null ? content : new CourseContent(courseId, ""));
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
            case "moveUp":
                updateMediaOrder(request, response, true); // Truyền true để tăng thứ tự
                break;
            case "moveDown":
                updateMediaOrder(request, response, false); // Truyền false để giảm thứ tự
                break;
        }
    }

    private void updateMediaOrder(HttpServletRequest request, HttpServletResponse response, boolean moveUp)
            throws ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int mediaId = Integer.parseInt(request.getParameter("mediaId"));
        courseMediaDAO.updateDisplayOrder(mediaId, moveUp); // Sử dụng phương thức mới

        response.sendRedirect("subjectDetail?action=edit&courseId=" + courseId);
    }

    private void updateCourseDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        Course course = courseDAO.select(courseId);

        // Cập nhật các trường của Course từ form
        course.setTitle(request.getParameter("title"));
        course.setDescription(request.getParameter("description"));
        course.setCategory(request.getParameter("category"));
        course.setBasicPackagePrice(new BigDecimal(request.getParameter("basicPackagePrice")));
        course.setAdvancedPackagePrice(new BigDecimal(request.getParameter("advancedPackagePrice")));
        course.setStatus(request.getParameter("status"));
        course.setSponsored(request.getParameter("isSponsored") != null);
        courseDAO.update(course);

        // Lấy content từ form (cho phép rỗng)
        String newContent = request.getParameter("content");

        // Chèn mới hoặc cập nhật content
        CourseContent content = courseContentDAO.select(courseId);
        if (content == null) {
            // Nếu chưa có, chèn một dòng content mới
            content = new CourseContent(courseId, newContent);
            courseContentDAO.insert(content);
        } else {
            // Nếu đã có, cập nhật content
            content.setContent(newContent);
            courseContentDAO.update(content);
        }

        // Xử lý media như trước (không thay đổi gì ở phần này)
        String[] mediaIds = request.getParameterValues("mediaId");
        String[] mediaTitles = request.getParameterValues("mediaTitle");
        String[] mediaTypes = request.getParameterValues("mediaType");

        if (mediaIds != null) {
            for (int i = 0; i < mediaIds.length; i++) {
                String mediaId = mediaIds[i];
                CourseMedia media;

                if ("new".equals(mediaId)) {
                    media = new CourseMedia();
                    media.setCourseId(courseId);
                } else {
                    media = courseMediaDAO.select(Integer.valueOf(mediaId));
                }

                media.setTitle(mediaTitles[i]);
                media.setMediaType(mediaTypes[i]);

                if ("new".equals(mediaId)) {
                    courseMediaDAO.insert(media);
                } else {
                    courseMediaDAO.update(media);
                }
            }
        }

        response.sendRedirect("subjectDetail?action=list");
    }

    private void removeSingleMedia(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String courseIdStr = request.getParameter("courseId");
        String mediaIdStr = request.getParameter("mediaId");

        // Nếu không có courseId hoặc mediaId, về trang list
        if (courseIdStr == null || mediaIdStr == null) {
            response.sendRedirect("subjectDetail?action=list");
            return;
        }

        try {
            int courseId = Integer.parseInt(courseIdStr);
            int mediaId = Integer.parseInt(mediaIdStr);

            courseMediaDAO.delete(mediaId);
            response.sendRedirect("subjectDetail?action=edit&courseId=" + courseId);
        } catch (NumberFormatException e) {
            // Nếu parse số bị lỗi, về trang list
            response.sendRedirect("subjectDetail?action=list");
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
