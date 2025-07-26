/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanSach.util;

import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author ADMIN
 */
public class EmailUtil {

    public static void sendOTP(String toEmail, String otp) throws MessagingException, IOException {

        Properties props = new Properties();
        try (var stream = EmailUtil.class.getClassLoader().getResourceAsStream("email.properties")) {
            if (stream == null) {
                throw new IOException("Cannot find email.properties in classpath");
            }
            props.load(stream);
        }
        String username = props.getProperty("mail.username");
        String password = props.getProperty("mail.password");

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        message.setSubject("Mã OTP để đặt lại mật khẩu");
        message.setText("Mã OTP của bạn là: " + otp + "\nVui lòng sử dụng mã này để đặt lại mật khẩu. Mã có hiệu lực trong 5 phút.");

        Transport.send(message);
    }

}
