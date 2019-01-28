package org.esteco.anniversary.notification;

import org.esteco.anniversary.mail.Email;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

public class Transporter {

    public static void transport(Email email) throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email.getFrom(), "");
            }
        };

        Session session = Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);
        //set message headers
        msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
        msg.addHeader("format", "flowed");
        msg.addHeader("Content-Transfer-Encoding", "8bit");
        msg.setFrom(new InternetAddress("jaiswal@esteco.com", "Admin"));
        msg.setReplyTo(InternetAddress.parse("jaiswal@esteco.com", false));
        msg.setSubject(email.getSubject(), "UTF-8");
        Multipart multipart = new MimeMultipart("related");
        MimeBodyPart bodyPart = new MimeBodyPart();
        //body
        bodyPart.setContent("<h1> Happy Birthday </h1> <img src=\"cid:image\">", "text/html");
        multipart.addBodyPart(bodyPart);
        //image
        bodyPart = new MimeBodyPart();
        bodyPart.setDataHandler(new DataHandler(new FileDataSource("C:\\Git\\esteco-anniversary-notifications\\notification\\src\\main\\resources\\birthday.png")));
        bodyPart.setHeader("Content-ID", "<image>");
        multipart.addBodyPart(bodyPart);

        msg.setContent(multipart);
        msg.setSentDate(new Date());
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo(), false));
        System.out.println("Message is ready");
        Transport.send(msg);
    }

    public static void main(String args[]) throws Exception {
        transport(Email.builder().subject("Happy Birthday").from("jaiswal@esteco.com").to("jaiswal@esteco.com").build());
    }
}
