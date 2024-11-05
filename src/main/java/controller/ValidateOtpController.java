package controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ValidateOtp")
public class ValidateOtpController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final long OTP_VALIDITY_PERIOD = 15 * 60 * 1000;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int value = Integer.parseInt(request.getParameter("otp"));
        
        HttpSession session = request.getSession();
        int otp = (int) session.getAttribute("otp");
        Long generationTime = (Long) session.getAttribute("otpGenerationTime");

        long currentTime = System.currentTimeMillis();

        RequestDispatcher dispatcher = null;

        if (value == otp && (currentTime - generationTime) <= OTP_VALIDITY_PERIOD) {

            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("status", "success");
            dispatcher = request.getRequestDispatcher("new-password.jsp");
            dispatcher.forward(request, response);

        } else {
            request.setAttribute("message", "wrong otp or Otp is expired");

            dispatcher = request.getRequestDispatcher("enter-otp.jsp");
            dispatcher.forward(request, response);

        }

    }

}
