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
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.BlogPost;
import model.Category;
import model.User;
import view.BlogPostDAO;
import view.CategoryDAO;
import view.UserDAO;

/**
 *
 * @author Minh
 */
@MultipartConfig
@WebServlet(name = "BlogUpdateController", urlPatterns = {"/blog-update"})
public class BlogUpdateController extends HttpServlet {

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
        int id = Integer.parseInt(request.getParameter("id"));
        
        BlogPostDAO blogPostDAO = new BlogPostDAO();
        BlogPost post = blogPostDAO.select(id);
        List<Category> categories = new CategoryDAO().select();
        int page = 1;
        String pagePara = request.getParameter("page");
        if (pagePara != null) {
            page = Integer.parseInt(pagePara);
        }
        
        List<String> thumbnails = retrieveThumbnails();
        List<User> authors = new UserDAO().select();
        
        request.setAttribute("authors", authors);
        request.setAttribute("thumbnails", thumbnails);
        request.setAttribute("categories", categories);
        request.setAttribute("page", page);
        request.setAttribute("post", post);
        request.getRequestDispatcher("blog-update.jsp").forward(request, response);
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
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String existingThumbnail = request.getParameter("existingThumbnail"); // Existing thumbnail option
        Part newThumbnailPart = request.getPart("thumbnail"); // New thumbnail upload
        
        String thumbnailUrl; // This will hold the final thumbnail URL
        
        if (newThumbnailPart != null && newThumbnailPart.getSize() > 0) {
            // Handle new thumbnail upload
            String fileName = new File(newThumbnailPart.getSubmittedFileName()).getName();
            File uploads = new File(getServletContext().getRealPath(THUMBNAIL_DIRECTORY));
            uploads.mkdirs(); // Create the directory if it doesn't exist
            File file = new File(uploads, fileName);

            // Copy the uploaded file to the target location
            Files.copy(newThumbnailPart.getInputStream(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            thumbnailUrl = "assets/img/blog/" + fileName; // Update this based on your URL structure
        } else {
            // Use the existing thumbnail if no new one was uploaded
            thumbnailUrl = existingThumbnail;
        }
        
        int page = 1;
        String pagePara = request.getParameter("page");
        if (pagePara != null){
            page = Integer.parseInt(pagePara);
        }
        
        
        BlogPostDAO blogPostDAO = new BlogPostDAO();
        BlogPost blogPost = blogPostDAO.select(id);
        
        blogPost.setTitle(title);
        blogPost.setContent(content);
        blogPost.setCategoryId(categoryId);
        blogPost.setThumbnailUrl(thumbnailUrl);
        blogPost.setUpdatedAt(LocalDateTime.now());
        
        blogPostDAO.update(blogPost);
        response.sendRedirect("blog-list?page="+page);
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
    
    private String THUMBNAIL_DIRECTORY = "/assets/img/blog";
    
    // Method to retrieve thumbnail file names from the specified directory using NIO
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
