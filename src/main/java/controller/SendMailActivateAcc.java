//package controller;
//
//import jakarta.websocket.Session;
//import java.net.PasswordAuthentication;
//import java.util.Properties;
//import jakarta.mail.*;
//import jakarta.mail.internet.InternetAddress;
//import jakarta.mail.internet.MimeMessage;
//
//public class SendingEmail {
//
//    private String userEmail;
//    private String myHash;
//
//    public SendingEmail(String userEmail, String myHash) {
//        this.userEmail = userEmail;
//        this.myHash = myHash;
//    }
//
//    public void sendMail() {
//        String email = "emailsender1124@gmail.com";
//        String pword = "PHH01012004";
//
//        Properties properties = new Properties();
//
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        properties.put("mail.smtp.port", "587");
//        
//        Session session = Session.getDefaultInstance(properties, new jakarta.mail.Authenticator(){
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(email, pword);
//            }
//        });
//        
//        try {
//            MimeMessage message  = new MimeMessage((MimeMessage) session);
//            message.setFrom(new InternetAddress(email));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
//            message.setSubject("Email verification link");
//            message.setText("Verification link . . .");
//            message.setText("Your Verification Link :: "+"http//localhost9999/OnlineSoftSkillsLearningSystem/ActivateAccount?key1="+userEmail+"&key2="+myHash);
//            Transport.send(message);
//            
//        } catch (Exception e) {
//            System.out.println("SendingEmail.java file");
//        }
//    }
//}
package controller;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMailActivateAcc {

    private String userEmail;
    private String myHash;

    // Constructor
    public SendMailActivateAcc(String userEmail, String myHash) {
        this.userEmail = userEmail;
        this.myHash = myHash;
    }

    public void sendMail() {
        // Replace with your sender email and password
        String email = "ngoquochuyvn2004@gmail.com";
        String pword = "neqv kair sjxa ccxb";  // Externalize this in a config file/environment variables in a production setup

        // Set mail server properties
        Properties properties = new Properties();
      properties.put("mail.smtp.host", "smtp.gmail.com"); // SMTP host, e.g., smtp.gmail.com
            properties.put("mail.smtp.port", "587");              // SMTP port
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");

        // Create session with authentication
        Session session = Session.getInstance(properties, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, pword);
            }
        });

        try {
            // Create a new email message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
            message.setSubject("Email verification link");

            // Corrected URL in the email body
            String verificationLink = "http://localhost:9999/OnlineSoftSkillsLearningSystem/ActivateAccount?key1=" + userEmail + "&key2=" + myHash;
            message.setText("Your Verification Link: " + verificationLink);

            // Send the email
            Transport.send(message);
            System.out.println("Verification email sent successfully!");

        } catch (MessagingException e) {
            System.out.println("Error occurred while sending email");
            e.printStackTrace();  // Print the full stack trace for debugging
        }
    }
}
