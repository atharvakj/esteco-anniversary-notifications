package org.esteco.anniversary.notification;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.esteco.anniversary.mail.Email;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.util.Date;
import java.util.Properties;

public class Transporter {

    public static void transport(Email email) {
        Properties props = getProperties();

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
        try {
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            msg.setFrom(new InternetAddress("jaiswal@esteco.com", "Admin"));
            msg.setReplyTo(InternetAddress.parse("jaiswal@esteco.com", false));
            msg.setSubject(email.getSubject(), "UTF-8");
            Multipart multipart = new MimeMultipart("related");
            MimeBodyPart bodyPart = new MimeBodyPart();
            //body
            bodyPart.setContent("<img src=\"cid:image\">", "text/html");
            multipart.addBodyPart(bodyPart);
            //image
            bodyPart = new MimeBodyPart();
            bodyPart.setDataHandler(new DataHandler(new FileDataSource(getAttachment(email))));
            bodyPart.setHeader("Content-ID", "<image>");
            multipart.addBodyPart(bodyPart);

            msg.setContent(multipart);
            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo(), false));
            System.out.println("Message is ready");
            Transport.send(msg);
        } catch (Exception e) {
            Logger.getLogger(Transporter.class.getName()).log(Level.ERROR, "Could not send email to " + email.getTo() + " for type " + email.getType(), e);
        }
    }

    private static File getAttachment(Email email) throws IOException {
        File attachment = new File("./" + email.getType() + ".jpg");
        if (!attachment.exists()) {
            InputStream resourceAsStream = Transporter.class.getClassLoader().getResourceAsStream(email.getType() + ".jpg");
            OutputStream outputStream = new FileOutputStream(attachment);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = resourceAsStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }
        return attachment;
    }

    private static Properties getProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        return props;
    }

    public static void main(String args[]) {
        transport(Email.builder().subject("Congratulations Vivek for completing 35 yrs at Esteco").to("honule@esteco.com").from("jaiswal@esteco.com").type("Anniversary").build());
    }
}
