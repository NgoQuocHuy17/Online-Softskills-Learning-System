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

@WebServlet("/addMedia")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 1024 * 1024 * 5, // 5MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB tổng request
)
public class AddMediaServlet extends HttpServlet {

    // Cấu hình tương tự như trước, nhưng sẽ có thêm phần xử lý upload file
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Xử lý hiển thị form thêm media (giống như trước)
        String courseIdParam = request.getParameter("courseId");
        if (courseIdParam == null || courseIdParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing courseId");
            return;
        }

        int courseId = Integer.parseInt(courseIdParam);
        CourseDAO courseDAO = new CourseDAO();
        Course course = courseDAO.getCourseById(courseId);
        request.setAttribute("course", course);

        request.getRequestDispatcher("/addMedia.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mediaType = request.getParameter("mediaType");  // Lấy loại media (image hoặc video)
        String title = request.getParameter("title");          // Tiêu đề của media
        int displayOrder = Integer.parseInt(request.getParameter("displayOrder"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));

        // Xử lý file upload
        Part filePart = request.getPart("file"); // Nhận file từ form
        String fileName = getFileName(filePart); // Lấy tên file

        // Đường dẫn lưu trữ file
        String uploadPath;
        if ("image".equals(mediaType)) {
            uploadPath = getServletContext().getRealPath("/assets/img/course");
        } else if ("video".equals(mediaType)) {
            uploadPath = getServletContext().getRealPath("/assets/video");
        } else {
            throw new ServletException("Unknown media type: " + mediaType);
        }

        // Tạo thư mục lưu trữ nếu chưa có
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // Tạo thư mục nếu chưa tồn tại
        }

        // Ghi file vào server trong thư mục tương ứng
        filePart.write(uploadPath + File.separator + fileName);

        // Lưu thông tin media vào cơ sở dữ liệu
        CourseMedia media = new CourseMedia();
        media.setCourseId(courseId);
        media.setMediaType(mediaType);
        media.setFileName(fileName); // Tên file đã lưu
        media.setTitle(title);
        media.setDisplayOrder(displayOrder);

        CourseMediaDAO mediaDAO = new CourseMediaDAO();
        mediaDAO.insert(media);

        // Chuyển hướng về trang chi tiết khóa học
        response.sendRedirect("subjectDetail?courseId=" + courseId);
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
}
