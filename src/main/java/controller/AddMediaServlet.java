/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CourseMedia;
import view.CourseMediaDAO;

import java.io.IOException;
import model.Course;
import view.CourseDAO;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import java.io.File;
import model.User;

@WebServlet("/add-media")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 1024 * 1024 * 5, // 5MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB tổng request
)
public class AddMediaServlet extends HttpServlet {

    private final CourseDAO courseDAO = new CourseDAO();

    // Cấu hình tương tự như trước, nhưng sẽ có thêm phần xử lý upload file
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Xử lý hiển thị form thêm media (giống như trước)

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

        String courseIdParam = request.getParameter("courseId");
        if (courseIdParam == null || courseIdParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing courseId");
            return;
        }

        int courseId = Integer.parseInt(courseIdParam);
        CourseDAO courseDAO = new CourseDAO();
        Course course = courseDAO.getCourseById(courseId);
        request.setAttribute("course", course);

        request.getRequestDispatcher("/add-media.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mediaType = request.getParameter("mediaType");  // Loại media
        String title = request.getParameter("title");          // Tiêu đề của media
        int courseId = Integer.parseInt(request.getParameter("courseId"));

        // Xử lý file upload
        Part filePart = request.getPart("file"); // Nhận file từ form
        String fileName = getFileName(filePart); // Lấy tên file

        if (!isValidFileType(fileName, mediaType)) {
            request.setAttribute("error", "File không đúng định dạng với loại media đã chọn.");
            request.setAttribute("course", new CourseDAO().getCourseById(courseId)); // Cập nhật course
            request.getRequestDispatcher("/add-media.jsp").forward(request, response);
            return;
        }

        // Đường dẫn lưu trữ file
        String uploadPath;
        switch (mediaType) {
            case "image":
                uploadPath = getServletContext().getRealPath("/assets/img/course");
                break;
            case "video":
                uploadPath = getServletContext().getRealPath("/assets/video/course");
                break;
            default:
                throw new ServletException("Unknown media type: " + mediaType);
        }

        // Ghi file vào server trong thư mục tương ứng
        filePart.write(uploadPath + File.separator + fileName);

        // Lấy displayOrder lớn nhất hiện tại và tăng thêm 1
        CourseMediaDAO mediaDAO = new CourseMediaDAO();
        int maxDisplayOrder = mediaDAO.getMaxDisplayOrder(courseId);

        // Lưu thông tin media vào cơ sở dữ liệu
        CourseMedia media = new CourseMedia();
        media.setCourseId(courseId);
        media.setMediaType(mediaType);
        media.setFileName(fileName); // Tên file đã lưu
        media.setTitle(title);
        media.setDisplayOrder(maxDisplayOrder + 1); // Tự động thiết lập displayOrder

        mediaDAO.insert(media);

        // Chuyển hướng về trang chi tiết khóa học
        response.sendRedirect("subject-details?action=edit&courseId=" + courseId);
    }

// Phương thức lấy tên file upload từ Part
    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                return item.substring(item.indexOf('=') + 2, item.length() - 1);
            }
        }
        return "";
    }

// Phương thức kiểm tra tính hợp lệ của định dạng file
    private boolean isValidFileType(String fileName, String mediaType) {
        String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
        if ("image".equals(mediaType)) {
            return fileExtension.equals("jpg") || fileExtension.equals("jpeg") || fileExtension.equals("png") || fileExtension.equals("jfif");
        } else if ("video".equals(mediaType)) {
            return fileExtension.equals("mp4") || fileExtension.equals("avi");
        }
        return false;
    }

}
