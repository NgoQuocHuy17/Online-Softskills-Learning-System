/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.ServletContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.BlogPost;
import view.BlogPostDAO;
import view.CategoryDAO;
import view.UserDAO;

/**
 *
 * @author Minh
 */
@MultipartConfig
@WebServlet(name = "CreatePostController", urlPatterns = {"/blog-create"})
public class CreatePostController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String UPLOAD_DIR = "uploads";

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        var categories = new CategoryDAO().select();
        var authors = new UserDAO().select();
        var thumbnails = retrieveThumbnails();
        
        request.setAttribute("thumbnails", thumbnails);
        request.setAttribute("categories", categories);
        request.setAttribute("authors", authors);
        request.getRequestDispatcher("blog-create.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String categoryId = request.getParameter("categoryId");
        String authorId = request.getParameter("authorId");
        String status = request.getParameter("status");
        String existingThumbnail = request.getParameter("existingThumbnail");

        // Handle file upload if a new file is provided
        Part filePart = request.getPart("thumbnail");
        String fileName = null;
        if (filePart != null && filePart.getSize() > 0) {
            fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            filePart.write(uploadPath + File.separator + fileName);
        }

        // Create a new BlogPost object or persist data (logic not included)
        BlogPost newPost = new BlogPost();
        newPost.setTitle(title);
        newPost.setContent(content);
        newPost.setCategoryId(Integer.parseInt(categoryId));
        newPost.setAuthorId(Integer.parseInt(authorId));
        newPost.setStatus(status);
        newPost.setThumbnailUrl(fileName != null ? UPLOAD_DIR + "/" + fileName : existingThumbnail);
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setUpdatedAt(LocalDateTime.now());
        // Save the post to a database or other storage (this requires implementation)
        // Redirect to the blog list or confirmation page
        new BlogPostDAO().insert(newPost);

        response.sendRedirect("blog-list");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private List<String> retrieveThumbnails() throws IOException {
        ServletContext context = getServletContext();
        String THUMBNAIL_DIRECTORY = context.getRealPath("/assets/img/blog");
        List<String> thumbnailList = new ArrayList<>();
        Path thumbnailDir = Paths.get(THUMBNAIL_DIRECTORY);

        // Check if the directory exists
        if (Files.exists(thumbnailDir) && Files.isDirectory(thumbnailDir)) {
            // Use a Directory Stream to list files
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(thumbnailDir, "*.{jpg,png,jpeg}")) {
                for (Path path : directoryStream) {
                    // Add the file name to the list
                    thumbnailList.add(path.getFileName().toString());
                }
            }
        }
        return thumbnailList;
    }
    
}
