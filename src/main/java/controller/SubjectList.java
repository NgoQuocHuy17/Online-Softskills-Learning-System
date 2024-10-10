/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;
import model.Course;
import view.CourseDAO;

/**
 *
 * @author ngoqu
 */
@WebServlet(name = "SubjectList", urlPatterns = {"/SubjectList"})
public class SubjectList extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        CourseDAO c = new CourseDAO();

        // Pagination parameters
        int itemsPerPage = 5; // Set the number of items you want per page
        String pageParam = request.getParameter("page");
        String searchTitle = request.getParameter("searchTitle"); // Get search input
        String status = request.getParameter("status"); // Get status filter
        int currentPage = (pageParam != null) ? Integer.parseInt(pageParam) : 1;

        // Get all courses
        List<Course> allCourses = c.getAllCourses();
        List<String> S = c.getAllStatuses();
        request.setAttribute("statuses", S);

        // Filter courses by search title
        if (searchTitle != null && !searchTitle.isEmpty()) {
            allCourses = c.getSubjectByTitle(searchTitle);
        }

        // Filter courses by status
        if (status != null && !status.equals("All")) {
            allCourses = allCourses.stream()
                    .filter(course -> course.getStatus().equals(status))
                    .toList();
        }

        // Pagination logic
        int totalCourses = allCourses.size();
        int totalPages = (int) Math.ceil((double) totalCourses / itemsPerPage);
        int startIndex = (currentPage -1 ) * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, totalCourses);

        // Get the sublist for the current page
        List<Course> paginatedCourses = allCourses.subList(startIndex, endIndex);

        request.setAttribute("courses", paginatedCourses);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("searchTitle", searchTitle); // Set the search title for the form
        request.setAttribute("status", status); // Set the selected status for the filter

        RequestDispatcher dispatcher = request.getRequestDispatcher("subjectList.jsp");
        dispatcher.forward(request, response);
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
