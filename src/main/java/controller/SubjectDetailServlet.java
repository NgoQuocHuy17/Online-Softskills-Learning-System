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
import model.User;
import view.CourseContentDAO;

@WebServlet(name = "SubjectDetailServlet", urlPatterns = {"/subject-details"})
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

        Course course = courseDAO.select(courseId);
        CourseContent content = courseContentDAO.select(courseId);
        List<CourseMedia> mediaList = courseMediaDAO.selectByCourseId(courseId); // Lấy từ database
        int maxOrder = mediaList.size();

        request.setAttribute("course", course);
        request.setAttribute("content", content != null ? content : new CourseContent(courseId, ""));
        request.setAttribute("mediaList", mediaList);
        request.setAttribute("maxOrder", maxOrder);

        request.getRequestDispatcher("subject-details.jsp").forward(request, response);
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
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        List<CourseMedia> mediaList = courseMediaDAO.selectByCourseId(courseId); 
        int mediaId = Integer.parseInt(request.getParameter("mediaId"));

        for (int i = 0; i < mediaList.size(); i++) {
            if (mediaList.get(i).getId() == mediaId) {
                int swapIndex = moveUp ? i - 1 : i + 1;
                if (swapIndex >= 0 && swapIndex < mediaList.size()) {
                    CourseMedia adjacentMedia = mediaList.get(swapIndex);
                    CourseMedia currentMedia = mediaList.get(i);

                    int tempOrder = currentMedia.getDisplayOrder();
                    currentMedia.setDisplayOrder(adjacentMedia.getDisplayOrder());
                    adjacentMedia.setDisplayOrder(tempOrder);

                    courseMediaDAO.update(currentMedia); // Cập nhật trong database
                    courseMediaDAO.update(adjacentMedia); // Cập nhật trong database
                }
                break;
            }
        }

        response.sendRedirect("subject-details?action=edit&courseId=" + courseId);
    }

    private void updateCourseDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            Course course = courseDAO.select(courseId);
            CourseContent content = courseContentDAO.select(courseId);

            if (course != null) {
                String title = request.getParameter("title");
                String description = request.getParameter("description");
                String category = request.getParameter("category");
                BigDecimal basicPackagePrice = new BigDecimal(request.getParameter("basicPackagePrice"));
                BigDecimal advancedPackagePrice = new BigDecimal(request.getParameter("advancedPackagePrice"));
                String status = request.getParameter("status");
                boolean isSponsored = request.getParameter("isSponsored") != null;

                // Cập nhật thông tin course
                course.setTitle(title);
                course.setDescription(description);
                course.setCategory(category);
                course.setBasicPackagePrice(basicPackagePrice);
                course.setAdvancedPackagePrice(advancedPackagePrice);
                course.setStatus(status);
                course.setSponsored(isSponsored);
                courseDAO.update(course);
            }
            if (content != null) {
                // Cập nhật nội dung content
                String contentText = request.getParameter("content");
                content.setContent(contentText);
                courseContentDAO.update(content);
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
            request.setAttribute("error", "Invalid input for numerical fields.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while updating the course details.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    private void removeSingleMedia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseIdStr = request.getParameter("courseId");
        String mediaIdStr = request.getParameter("mediaId");

        if (courseIdStr == null || mediaIdStr == null) {
            response.sendRedirect("SubjectList");
            return;
        }

        try {
            int courseId = Integer.parseInt(courseIdStr);
            int mediaId = Integer.parseInt(mediaIdStr);

            // Xóa media
            courseMediaDAO.delete(mediaId);

            // Sắp xếp lại thứ tự hiển thị
            courseMediaDAO.reorderDisplayOrderAfterRemoval(courseId);

            response.sendRedirect("subject-details?action=edit&courseId=" + courseId);
        } catch (NumberFormatException e) {
            response.sendRedirect("SubjectList");
        }
    }

    private void toggleCourseStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        Course course = courseDAO.select(courseId);
        String newStatus = course.getStatus().equals("Published") ? "Draft" : "Published";
        course.setStatus(newStatus);
        courseDAO.update(course);
        response.sendRedirect("SubjectList");
    }
}
