package org.gortz.alarm.model.Notifications;

import org.gortz.alarm.model.Database;
import org.gortz.alarm.model.Databases.Mysql;
import org.gortz.alarm.model.Loggers.Logger;
import org.gortz.alarm.model.Notification;
import org.gortz.alarm.model.Setting.Settings;

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
    private String username;
    private String password;
    private final String recipient;
    private Database mySql;
    private Settings set;

    public Mail(String recipient){
        this.recipient = recipient;
        set = Settings.getInstance();
        mySql = new Mysql(set.getDbUsername(),set.getDbPassword());
        username = mySql.getServerSettingString("email_username");
        password = mySql.getServerSettingString("email_password");

    }

    /**
     * Sends a message with a title to selected email recipient
     * @param title subject of message
     * @param message content of message
     */
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
            emailMessage.setFrom(new InternetAddress(username));
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
