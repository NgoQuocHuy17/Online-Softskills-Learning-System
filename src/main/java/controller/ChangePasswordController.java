package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import view.UserDAO;

@WebServlet("/changepass")
public class ChangePasswordController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        RequestDispatcher dispatcher = null;

        // Check if user session exists
        if (u == null) {
            request.setAttribute("message1", "User session not found. Please log in again.");
            dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
            return;
        }

        String oldPassword = request.getParameter("oldpassword");
        String newPassword = request.getParameter("password");
        String confPassword = request.getParameter("confPassword");
        String webemail = u.getEmail();

        // Check if old password matches
        if (!oldPassword.equals(u.getPassword())) {
            request.setAttribute("message1", "Old password is incorrect.");
            dispatcher = request.getRequestDispatcher("change-password.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Check if new password and confirm password match
        if (!newPassword.equals(confPassword)) {
            request.setAttribute("message1", "New password and confirm password do not match.");
            dispatcher = request.getRequestDispatcher("change-password.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Update the password
        UserDAO userDAO = new UserDAO();
        userDAO.updatePassword(webemail, newPassword);
        request.setAttribute("message1", "Password successfully updated.");

        // Redirect to home page
        dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);
    }
}


