//package controller;
//
//import java.io.IOException;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import view.UserDAO;
//
//@WebServlet("/changepass")
//public class ChangePasswordController extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        String userLogin = (String) session.getAttribute("username");
//        String oldPassword = request.getParameter("oldpass");
//        String newPassword = request.getParameter("newpass");
//        String confirmPassword = request.getParameter("conpass");
//        String username = request.getParameter("username");
//         if(userLogin.isEmpty())
//        if (userLogin == null || !userLogin.equals(username)) {
//            request.setAttribute("message3", "Account name you just entered does not match the account name you used to log in.");
//            request.getRequestDispatcher("changepass.jsp").forward(request, response);
//            return;
//        }
//        
//        UserDAO ac = new UserDAO();
//        String storedPassword = "";
//        try {
//            storedPassword = ac.getPassByUser(username);
//        } catch (SQLException ex) {
//            Logger.getLogger(ChangePasswordController.class.getName()).log(Level.SEVERE, null, ex);
//            request.setAttribute("message3", "An error occurred while accessing the database.");
//            request.getRequestDispatcher("changepass.jsp").forward(request, response);
//            return; 
//        }
//
//        if (storedPassword.equals(oldPassword)) {
//            if (newPassword.equals(confirmPassword)) {
//                ac.updatePasswordbyusername(username, newPassword);
//                response.sendRedirect("success-200.html");
//            } else {
//                request.setAttribute("message3", "New Password and Confirm Password do not match.");
//                request.getRequestDispatcher("changepass.jsp").forward(request, response);
//            }
//        } else {
//            request.setAttribute("message3", "Old Password is incorrect.");
//            request.getRequestDispatcher("changepass.jsp").forward(request, response);
//        }
//    }
//
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }
//}
