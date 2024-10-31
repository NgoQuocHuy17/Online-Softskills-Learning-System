/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
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
@WebServlet(name = "BlogDetailsController", urlPatterns = {"/blogdetails"})
public class BlogDetailsController extends HttpServlet {

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
        BlogPostDAO blogPostDAO = new BlogPostDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        BlogPost blogPost = blogPostDAO.select(id);
        
        UserDAO userDao = new UserDAO();
        User author = userDao.select(blogPost.getAuthorId());
    
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedCreatedAt = blogPost.getCreatedAt().format(dtf);
        String formattedUpdatedAt = blogPost.getUpdatedAt().format(dtf);
        
        
        
        CategoryDAO catDAO = new CategoryDAO();
        Category cat = catDAO.select(blogPost.getCategoryId());
        
        List<BlogPost> blogPosts = blogPostDAO.select();
        
        Comparator<BlogPost> com = Comparator.comparing(BlogPost::getCreatedAt).reversed();
        
        blogPosts.sort(com);
        request.setAttribute("blogPost", blogPost);
        request.setAttribute("author", author);
        request.setAttribute("blogPosts", blogPosts);
        request.setAttribute("category", cat);
        request.setAttribute("formattedCreatedAt", formattedCreatedAt);
        request.setAttribute("formattedUpdatedAt", formattedUpdatedAt);
        request.getRequestDispatcher("blog-details.jsp").forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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

}
