package controller;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.UserDAO;

@WebServlet("/forgotPassword")
public class ForgotPasswordController extends HttpServlet {

    private static final long OTP_VALIDITY_PERIOD = 15 * 60 * 1000;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long generationTime = System.currentTimeMillis();
        String email = request.getParameter("input");
        UserDAO dao = new UserDAO();
        try {
            if (!dao.isEmailExist(email)) {
                throw new Exception();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ForgotPasswordController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            request.setAttribute("message2", "EMAIL IS NOT IN DATABASE");
            RequestDispatcher dispatcher = request.getRequestDispatcher("forgotPassword.jsp");
            dispatcher.forward(request, response);
            return;
        }

        RequestDispatcher dispatcher = null;
        int otpvalue = 0;
        HttpSession mySession = request.getSession();

        if (email != null && !email.equals("")) {
            // sending otp
            Random rand = new Random();
            otpvalue = rand.nextInt(900000) + 100000;

            String to = email;
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP host, e.g., smtp.gmail.com
            props.put("mail.smtp.port", "587");              // SMTP port
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("ngoquochuyvn2004@gmail.com", "neqv kair sjxa ccxb");
                }
            });
            // compose message
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress("ngoquochuyvn2004@gmail.com")); // change accordingly
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                message.setSubject("OTP for Password Reset");
                message.setText("\nYour OTP is: " + otpvalue
                        + ". Please note that your OTP will expired in 15 minutes ");
                // send message
                Transport.send(message);
                System.out.println("Message sent successfully");
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            dispatcher = request.getRequestDispatcher("EnterOtp.jsp");
            request.setAttribute("message", "OTP is sent to your email id");
            mySession.setAttribute("otp", otpvalue);
            mySession.setAttribute("email", email);
            mySession.setAttribute("otpGenerationTime", generationTime);
            dispatcher.forward(request, response);

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
