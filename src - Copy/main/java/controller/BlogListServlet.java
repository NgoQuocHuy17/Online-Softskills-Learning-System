package controller;

import view.BlogPostDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/bloglist")
public class BlogListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BlogPostDAO blogPostDAO = new BlogPostDAO();

        int pageSize = 3;
        int pageNumber = 1; // Default to first page

        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                pageNumber = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                pageNumber = 1; // Fallback to first page if invalid number
            }
        }

        // Fetch categories and blog posts
        List<Category> categories = blogPostDAO.getCategories();
        List<BlogPost> blogPosts = blogPostDAO.getBlogPostsByPage(pageNumber, pageSize);
        int totalBlogPosts = blogPostDAO.getTotalBlogPostCount();
        int totalPages = (int) Math.ceil((double) totalBlogPosts / pageSize);

        request.setAttribute("categories", categories);
        request.setAttribute("blogPosts", blogPosts);
        request.setAttribute("currentPage", pageNumber);
        request.setAttribute("totalPages", totalPages);
        request.getRequestDispatcher("/bloglist.jsp").forward(request, response);
    }
}
