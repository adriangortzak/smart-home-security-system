package org.gortz.alarm.model.Notifications;

import org.gortz.alarm.model.Notification;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by jimmy on 4/29/16.
 */
public class Mail implements Notification {
    final static String username = "II1302Group13@gmail.com";
    final static String password = "DcPfop-T";
    private final String recipient;


    //TODO gör om, ska vara username och Password. Lägg även till stöd i databas som uppdaeras i settings.

    public Mail(String recipient){
        this.recipient = recipient;
    }
    @Override
    public void sendMessage(String title, String message) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message emailMessage = new MimeMessage(session);
            emailMessage.setFrom(new InternetAddress("II1302Group13@gmail.com"));
            emailMessage.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipient));
            emailMessage.setSubject(title);
            emailMessage.setText(message);

            Transport.send(emailMessage);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
