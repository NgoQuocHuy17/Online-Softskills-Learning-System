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
import model.Course;
import model.User;
import view.CourseDAO;
import view.UserDAO;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import model.CourseContent;
import model.CourseMedia;
import view.CourseContentDAO;
import view.CourseMediaDAO;

/**
 *
 * @author Minh
 */
@WebServlet(name = "CourseDetailsController", urlPatterns = {"/course-details"})
public class CourseDetailsController extends HttpServlet {

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
        int id = Integer.parseInt(request.getParameter("courseId"));
        
        CourseDAO coursedao = new CourseDAO();
        Course course = coursedao.getCourseById(id);
        var courses = coursedao.select();
        
        CourseContentDAO courseContentDAO = new CourseContentDAO();
        CourseContent courseContent = courseContentDAO.select(id);
        
        CourseMediaDAO courseMediaDAO = new CourseMediaDAO();
        List<CourseMedia> courseMedias = courseMediaDAO.selectByCourseId(id);
        courseMedias.sort(Comparator.comparing(CourseMedia::getDisplayOrder));
        
        UserDAO userDao = new UserDAO();
        int authorId = course.getOwnerId();
        User author = userDao.getUserById(authorId);
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedCreatedAt = course.getCreatedAt().toLocalDateTime().format(dtf);
        String formattedUpdatedAt = course.getUpdatedAt().toLocalDateTime().format(dtf);
        
        request.setAttribute("courses", courses);
        request.setAttribute("courseDetail", course);
        request.setAttribute("courseContent", courseContent);
        request.setAttribute("courseMedias", courseMedias);
        request.setAttribute("author", author);
        request.setAttribute("formattedCreatedAt", formattedCreatedAt);
        request.setAttribute("formattedUpdatedAt", formattedUpdatedAt);
        request.getRequestDispatcher("course-details.jsp").forward(request, response);
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
