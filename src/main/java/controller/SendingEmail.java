package controller;

import jakarta.websocket.Session;
import java.net.PasswordAuthentication;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendingEmail {

    private String userEmail;
    private String myHash;

    public SendingEmail(String userEmail, String myHash) {
        this.userEmail = userEmail;
        this.myHash = myHash;
    }

    public void sendMail() {
        String email = "emailsender1124@gmail.com";
        String pword = "PHH01012004";

        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, pword);
            }
        });
        
        try {
            MimeMessage message  = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
            message.setSubject("Email verification link");
            message.setText("Verification link . . .");
            message.setText("Your Verification Link :: "+"http//localhost9999/OnlineSoftSkillsLearningSystem/ActivateAccount?key1="+userEmail+"&key2="+myHash);
            Transport.send(message);
            
        } catch (Exception e) {
            System.out.println("SendingEmail.java file");
        }

    }

}
