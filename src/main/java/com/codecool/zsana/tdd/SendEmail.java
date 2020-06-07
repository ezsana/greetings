package com.codecool.zsana.tdd;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {

    public static void sendEmail(String recipients,
                                String subject, String content, String from) throws MessagingException {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "localhost");
        props.put("mail.smtp.ssl.enable", true);

        Session session = Session.getInstance(props);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
        message.setSubject(subject);
        message.setText(content);

        Transport.send(message, "", "");
    }
}
