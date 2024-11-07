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
import java.util.List;
import model.Slider;
import view.SliderDAO;

/**
 *
 * @author Minh
 */
@WebServlet(name = "SliderListController", urlPatterns = {"/slider-list"})
public class SliderListController extends HttpServlet {

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

        // Lấy các tham số 'show' từ request
        String showId = request.getParameter("showId");
        String showTitle = request.getParameter("showTitle");
        String showImage = request.getParameter("showImage");
        String showBacklink = request.getParameter("showBacklink");
        String showStatus = request.getParameter("showStatus");

        SliderDAO sliderDAO = new SliderDAO();

        // Lấy số trang từ request (mặc định là 1 nếu không có tham số)
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        // Lấy pageSize từ request (mặc định là 5)
        int pageSize = 5;
        String pageSizeParam = request.getParameter("pageSize");
        if (pageSizeParam != null && !pageSizeParam.isEmpty()) {
            try {
                pageSize = Integer.parseInt(pageSizeParam);
            } catch (Exception e) {
                pageSize = 5;
            }
        }

        String statusFilter = request.getParameter("status");
        if (statusFilter == null || statusFilter.isEmpty()) {
            statusFilter = null;
        }

        String searchTerm = request.getParameter("searchTerm");
        if (searchTerm == null || searchTerm.isEmpty()) {
            searchTerm = null;
        }

        String sortBy = request.getParameter("sort");
        String sortOrder = request.getParameter("sortOrder");

        // In ra các giá trị lọc để kiểm tra
        System.out.println("Status Filter: " + statusFilter);

        // Lấy danh sách slider theo trang với các tham số lọc và tìm kiếm
        List<Slider> sliders = sliderDAO.getSlidersByPage(page, pageSize, statusFilter, searchTerm, sortBy, sortOrder);

        // Tính tổng số slider và số trang
        int totalSlider = sliderDAO.getTotalSliders(statusFilter, searchTerm);

        int totalPages = (int) Math.ceil(totalSlider / (double) pageSize);

        if (page > totalPages){
            page = totalPages;
        }
        
        if (sliders.isEmpty()) {
            request.setAttribute("message", "Không có slider hợp lệ");
        }

        request.setAttribute("showId", showId);
        request.setAttribute("showTitle", showTitle);
        request.setAttribute("showImage", showImage);
        request.setAttribute("showBacklink", showBacklink);
        request.setAttribute("showStatus", showStatus);

        // Gửi danh sách users và các thông tin phân trang đến trang JSP
        request.setAttribute("sliders", sliders);
        request.setAttribute("currentPage", page);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("status", statusFilter);
        request.setAttribute("searchTerm", searchTerm);
        request.setAttribute("sort", sortBy);
        request.setAttribute("sortOrder", sortOrder);
        request.getRequestDispatcher("/slider-list.jsp").forward(request, response);
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
    }
}
