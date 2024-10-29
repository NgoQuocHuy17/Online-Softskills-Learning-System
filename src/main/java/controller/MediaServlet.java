package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


@WebServlet("/media/*")
public class MediaServlet extends HttpServlet {
    private static final String IMG_PATH = "/assets/img/course/";
    private static final String VIDEO_PATH = "/assets/video/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mediaType = request.getParameter("type");  // 'image' hoặc 'video'
        String fileName = request.getPathInfo().substring(1); // Lấy tên file từ URL
        
        String filePath;
        if ("image".equals(mediaType)) {
            filePath = getServletContext().getRealPath(IMG_PATH + fileName);
            response.setContentType("image/jpeg"); // Cài đặt đúng kiểu MIME
        } else {
            filePath = getServletContext().getRealPath(VIDEO_PATH + fileName);
            response.setContentType("video/mp4"); // Cài đặt đúng kiểu MIME
        }

        File file = new File(filePath);
        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        try (FileInputStream fis = new FileInputStream(file); 
             ServletOutputStream os = response.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        }
    }
}
