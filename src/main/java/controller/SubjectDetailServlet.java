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

    private void editCourseDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));

        // Lấy danh sách media từ session nếu có, nếu không lấy từ cơ sở dữ liệu và lưu vào session
        List<CourseMedia> mediaList = (List<CourseMedia>) request.getSession().getAttribute("tempMediaList");
        if (mediaList == null) {
            mediaList = courseMediaDAO.selectByCourseId(courseId);
            request.getSession().setAttribute("tempMediaList", mediaList);
        }

        // Lấy các thông tin khác
        Course course = courseDAO.select(courseId);
        CourseContent content = courseContentDAO.select(courseId);
        int maxOrder = mediaList.size();

        request.setAttribute("course", course);
        request.setAttribute("content", content != null ? content : new CourseContent(courseId, ""));
        request.setAttribute("mediaList", mediaList);
        request.setAttribute("maxOrder", maxOrder);

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

    private void updateMediaOrder(HttpServletRequest request, HttpServletResponse response, boolean moveUp) throws ServletException, IOException {
        List<CourseMedia> mediaList = (List<CourseMedia>) request.getSession().getAttribute("tempMediaList");
        int mediaId = Integer.parseInt(request.getParameter("mediaId"));

        // Tìm media cần thay đổi
        for (int i = 0; i < mediaList.size(); i++) {
            if (mediaList.get(i).getId() == mediaId) {
                // Xác định media liền kề để hoán đổi vị trí
                int swapIndex = moveUp ? i - 1 : i + 1;
                if (swapIndex >= 0 && swapIndex < mediaList.size()) {
                    CourseMedia adjacentMedia = mediaList.get(swapIndex);
                    CourseMedia currentMedia = mediaList.get(i);

                    // Hoán đổi thứ tự hiển thị
                    int tempOrder = currentMedia.getDisplayOrder();
                    currentMedia.setDisplayOrder(adjacentMedia.getDisplayOrder());
                    adjacentMedia.setDisplayOrder(tempOrder);

                    // Hoán đổi vị trí trong danh sách
                    mediaList.set(i, adjacentMedia);
                    mediaList.set(swapIndex, currentMedia);
                }
                break;
            }
        }

        // Cập nhật danh sách media trong session để lưu lại thứ tự preview
        request.getSession().setAttribute("tempMediaList", mediaList);
        response.sendRedirect("subjectDetail?action=edit&courseId=" + request.getParameter("courseId"));
    }

    private void updateCourseDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Lấy khóa học hiện tại dựa vào ID
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            Course course = courseDAO.select(courseId);

            if (course != null) {
                // Cập nhật thông tin khóa học từ form
                String title = request.getParameter("title");
                String description = request.getParameter("description");

                course.setTitle(title);
                course.setDescription(description);

                // Cập nhật thông tin khóa học vào cơ sở dữ liệu
                courseDAO.update(course);
            }

            // Lấy danh sách media tạm thời từ session
            List<CourseMedia> mediaList = (List<CourseMedia>) request.getSession().getAttribute("tempMediaList");
            if (mediaList != null) {
                // Cập nhật thứ tự hiển thị của media vào cơ sở dữ liệu
                for (CourseMedia media : mediaList) {
                    courseMediaDAO.update(media);
                }

                // Xóa danh sách media tạm thời khỏi session sau khi lưu
                request.getSession().removeAttribute("tempMediaList");
            }

            // Chuyển hướng về trang danh sách khóa học sau khi cập nhật thành công
            response.sendRedirect("subjectDetail?action=list");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // Xử lý lỗi và hiển thị thông báo lỗi nếu có vấn đề trong việc lấy hoặc cập nhật khóa học
            request.setAttribute("error", "Invalid course ID.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi chung và hiển thị thông báo lỗi
            request.setAttribute("error", "An error occurred while updating the course details.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
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
