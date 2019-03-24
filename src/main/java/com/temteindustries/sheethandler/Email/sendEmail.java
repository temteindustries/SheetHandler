package com.temteindustries.sheethandler.Email;

import com.temteindustries.sheethandler.TemplateEmail.EmailTemplateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Service
public class sendEmail {

    private static String environment;

    @Autowired
    public void setUp(@Value("${environment}") String environment){
        this.environment = environment;
    }

    public Boolean sendMail(List<Object> guestList) {

        boolean msgSent = false;

        //Setting up configurations for the email connection to the Google SMTP server using TLS
        Properties props = new Properties();
        props.put("mail.smtp.host", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        //Establishing a session with required user details
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("temte.ind@gmail.com", "T-ind_123");
            }
        });
        try {
            //Creating a Message object to set the email content
            MimeMessage msg = new MimeMessage(session);
            //Storing the comma seperated values to email addresses
            String to = "forever.in.june.2019@gmail.com,jtemte3@gmail.com";
            /*Parsing the String with defualt delimiter as a comma by marking the boolean as true and storing the email
            addresses in an array of InternetAddress objects*/
            InternetAddress[] address = InternetAddress.parse(to, true);
            msg.setFrom(new InternetAddress("TemteWeddingSite@temteindustries.com"));
            //Setting the recepients from the address variable
            msg.setRecipients(Message.RecipientType.TO, address);

            if(environment.equals("dev")){
                msg.setSubject("Test RSVP (dev)");
            }else{
                msg.setSubject("New RSVP");
            }
            msg.setSentDate(new Date());
            String message = EmailTemplateObject.getHtml(guestList);
            msg.setContent(message, "text/html; charset=utf-8");
            msg.setHeader("XPriority", "1");
            Transport.send(msg);
            System.out.println("Mail has been sent successfully");
            msgSent = true;
        } catch (MessagingException mex) {
            System.out.println("Unable to send an email" + mex);
        }

        return msgSent;
    }

}
