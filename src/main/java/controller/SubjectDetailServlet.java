package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CourseDetail;
import view.CourseDAO;
import view.CourseMediaDAO;

import java.io.IOException;
import java.util.List;
import model.Course;
import model.CourseMedia;

@WebServlet(name = "SubjectDetailServlet", urlPatterns = {"/subjectDetail"})
public class SubjectDetailServlet extends HttpServlet {

    private final CourseDAO courseDAO = new CourseDAO();
    private final CourseMediaDAO courseMediaDAO = new CourseMediaDAO();

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

        // Lấy danh sách media liên quan đến khóa học
        List<CourseMedia> mediaList = courseMediaDAO.selectByCourseId(courseId);

        // Truyền course và mediaList sang JSP
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

        // Lấy thông tin từ form cho Course
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        // Cập nhật Course
        Course course = courseDAO.select(courseId);
        course.setTitle(title);
        course.setDescription(description);
        courseDAO.update(course);

        // Cập nhật CourseMedia nếu có
        String[] mediaIds = request.getParameterValues("mediaId");

        if (mediaIds != null) {
            String[] mediaTitles = request.getParameterValues("mediaTitle");
            String[] mediaTypes = request.getParameterValues("mediaType");
            String[] fileNames = request.getParameterValues("fileName");
            String[] mediaContents = request.getParameterValues("mediaContent");
            String[] isThumbnails = request.getParameterValues("isThumbnail");
            String[] displayOrders = request.getParameterValues("displayOrder");

            for (int i = 0; i < mediaIds.length; i++) {
                String mediaId = mediaIds[i];
                CourseMedia media = new CourseMedia();

                // Nếu mediaId là "new", tức là media mới được thêm
                if (mediaId.equals("new")) {
                    media.setCourseId(courseId);  // Gán courseId cho media mới
                } else {
                    // Nếu media đã tồn tại, lấy từ database
                    media = courseMediaDAO.select(Integer.parseInt(mediaId));
                }

                // Cập nhật các thông tin media
                media.setTitle(mediaTitles[i]);
                media.setMediaType(mediaTypes[i]);
                media.setFileName(fileNames[i]);
                media.setContent(mediaContents[i]);
                media.setIsThumbnail(isThumbnails != null && isThumbnails[i] != null);
                media.setDisplayOrder(Integer.parseInt(displayOrders[i]));

                // Nếu mediaId là "new", chèn bản ghi mới
                if (mediaId.equals("new")) {
                    courseMediaDAO.insert(media);
                } else {
                    courseMediaDAO.update(media);  // Cập nhật bản ghi media hiện có
                }
            }
        }

        // Sau khi cập nhật, chuyển hướng về danh sách
        response.sendRedirect("subjectDetail?action=list");
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
