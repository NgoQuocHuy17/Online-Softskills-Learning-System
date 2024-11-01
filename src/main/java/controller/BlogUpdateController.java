/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.ServletContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import model.BlogPost;
import model.Category;
import view.BlogPostDAO;
import view.CategoryDAO;

/**
 *
 * @author Minh
 */
@WebServlet(name = "BlogUpdateController", urlPatterns = {"/blog-update"})
public class BlogUpdateController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
    
    // Method to retrieve thumbnail file names from the specified directory using NIO
    public List<String> retrieveThumbnails() throws IOException {
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
