package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
        String newPassword = request.getParameter("password");
        String confPassword = request.getParameter("confPassword");
        User u = (User) session.getAttribute("user");
        String webemail = u.getEmail();
        RequestDispatcher dispatcher = null;
        try {
            if (newPassword != null && confPassword != null && newPassword.equals(confPassword)) {
                UserDAO ac = new UserDAO();
                ac.updatePassword(webemail, confPassword);
                dispatcher = request.getRequestDispatcher("success-200.html");
            } else {
               request.setAttribute("message1","NEW PASSWORD AND CONFIRM PASSWORD ARE NOT MATCH");
			
		   dispatcher=request.getRequestDispatcher("newPassword.jsp");
            }
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }  
}
