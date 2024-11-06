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
import jakarta.servlet.http.HttpSession;
import view.RegistrationDAO;

/**
 *
 * @author hung6
 */
@WebServlet(name = "CancelRegistration", urlPatterns = {"/CancelRegistration"})
public class CancelRegistration extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the registration ID from the request
        String registrationIdParam = request.getParameter("id");
        int registrationId = Integer.parseInt(registrationIdParam);

        // Initialize the RegistrationDAO
        RegistrationDAO registrationDAO = new RegistrationDAO();

        // Call the method to delete the registration
        boolean isDeleted = registrationDAO.deleteRegistrationById(registrationId);

        // Optional: Set a success or failure message
        HttpSession session = request.getSession();
        if (isDeleted) {
            session.setAttribute("message", "Registration cancelled successfully.");
        } else {
            session.setAttribute("message", "Failed to cancel registration.");
        }

        // Redirect back to MyRegistrations.jsp or any other page
        response.sendRedirect("MyRegistrations.jsp");
    }
}
