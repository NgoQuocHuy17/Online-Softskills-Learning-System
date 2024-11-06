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
import model.Registration;
import view.RegistrationDAO;

@WebServlet(name = "RegistrationsList", urlPatterns = {"/RegistrationsList"})
public class RegistrationsList extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private RegistrationDAO registrationDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        registrationDAO = new RegistrationDAO(); // Khởi tạo DAO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Lấy tất cả đăng ký từ DAO
        List<Registration> registrations = registrationDAO.getAllRegistrations();

        // Gửi danh sách đăng ký đến JSP
        request.setAttribute("registrations", registrations);
        
        // Chuyển hướng đến trang JSP
        request.getRequestDispatcher("RegistrationsList.jsp").forward(request, response);
    }
}