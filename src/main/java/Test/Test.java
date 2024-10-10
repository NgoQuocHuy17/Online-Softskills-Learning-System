package Test;

import SendingMail.SendingEmail;

public class Test {
    public static void main(String[] args) {
        SendingEmail emailSender = new SendingEmail();

        // Khai báo cứng thông tin email
        String to = "hung9a112004@gmail.com"; // Thay thế bằng địa chỉ email người nhận
        String subject = "Test Email Subject";
        String body = "This is a test email sent from the SendingEmail class.";

        // Gửi email
        emailSender.sendEmail(to, subject, body);
    }
}
