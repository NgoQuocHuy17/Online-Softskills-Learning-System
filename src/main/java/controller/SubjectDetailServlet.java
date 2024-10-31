package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.CourseDAO;
import view.CourseMediaDAO;

import java.io.IOException;
import java.util.List;
import model.Course;
import model.CourseContent;
import model.CourseMedia;
import model.User;
import view.CourseContentDAO;

@WebServlet(name = "SubjectDetailServlet", urlPatterns = {"/subjectDetail"})
public class SubjectDetailServlet extends HttpServlet {

    private final CourseDAO courseDAO = new CourseDAO();
    private final CourseMediaDAO courseMediaDAO = new CourseMediaDAO();
    private final CourseContentDAO courseContentDAO = new CourseContentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User loggedInUser = (User) request.getSession().getAttribute("user");
        if (loggedInUser == null) {
            // Redirect to "course" page if the user is not a Teacher or Admin
            response.sendRedirect("home");
            return;
        }

        // Check if the user is a teacher or admin
        if ("Teacher".equals(loggedInUser.getRole())) {
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            Course course = courseDAO.select(courseId);

            // Check if the logged-in user is the owner of the course
            if (course.getOwnerId() != loggedInUser.getId()) {
                // Redirect to SubjectList if the user is not the owner
                response.sendRedirect("SubjectList");
                return;
            }
        } else if (!"Admin".equals(loggedInUser.getRole())) {
            // If the user is neither a teacher nor an admin, redirect to home
            response.sendRedirect("home");
            return;
        }

        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "edit":
                    editCourseDetail(request, response);
                    break;
                case "remove":
                    removeSingleMedia(request, response);
                    break;
                default:
                    response.sendRedirect("SubjectList");
                    break;
            }
        } else {
            response.sendRedirect("SubjectList");
        }
    }

    private void editCourseDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));

        // Luôn lấy mediaList từ database
        List<CourseMedia> mediaList = courseMediaDAO.selectByCourseId(courseId);

        // Lấy các thông tin khác
        Course course = courseDAO.select(courseId);
        CourseContent content = courseContentDAO.select(courseId);
        int maxOrder = mediaList.size();

        // Đặt thuộc tính vào request để hiển thị trong JSP
        request.setAttribute("course", course);
        request.setAttribute("content", content != null ? content : new CourseContent(courseId, ""));
        request.setAttribute("mediaList", mediaList);
        request.setAttribute("maxOrder", maxOrder);

        // Chuyển tiếp sang trang JSP
        request.getRequestDispatcher("/editSubjectDetail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
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
                default:
                    response.sendRedirect("SubjectList");
                    break;
            }
        } else {
            response.sendRedirect("SubjectList");
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
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            Course course = courseDAO.select(courseId);

            if (course != null) {
                String title = request.getParameter("title");
                String description = request.getParameter("description");

                course.setTitle(title);
                course.setDescription(description);

                courseDAO.update(course);
            }

            List<CourseMedia> mediaList = (List<CourseMedia>) request.getSession().getAttribute("tempMediaList");
            if (mediaList != null) {
                for (CourseMedia media : mediaList) {
                    courseMediaDAO.update(media);
                }
                request.getSession().removeAttribute("tempMediaList");
            }

            response.sendRedirect("SubjectList");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Invalid course ID.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while updating the course details.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    private void removeSingleMedia(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String courseIdStr = request.getParameter("courseId");
        String mediaIdStr = request.getParameter("mediaId");

        if (courseIdStr == null || mediaIdStr == null) {
            response.sendRedirect("SubjectList");
            return;
        }

        try {
            int courseId = Integer.parseInt(courseIdStr);
            int mediaId = Integer.parseInt(mediaIdStr);

            courseMediaDAO.delete(mediaId);
            response.sendRedirect("subjectDetail?action=edit&courseId=" + courseId);
        } catch (NumberFormatException e) {
            response.sendRedirect("SubjectList");
        }
    }

    private void toggleCourseStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        Course course = courseDAO.select(courseId);
        String newStatus = course.getStatus().equals("Published") ? "Unpublished" : "Published";
        course.setStatus(newStatus);
        courseDAO.update(course);
        response.sendRedirect("SubjectList");
    }
}
