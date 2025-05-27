package com.vsu.java_email.controller;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class EmailController
{
    private final JavaMailSender mailSender;  // create private variable

    public EmailController(JavaMailSender mailSender)
    {
        this.mailSender = mailSender;  //  create constructor
    }
        @RequestMapping("/send-email")  // create endpoint
        public String sendEmail()       // crate method
        {
            try {
                SimpleMailMessage message = new SimpleMailMessage();  // create object

                message.setFrom("vishalubale1506@gmail.com"); // call the method
                message.setTo("ubalevishal2019@gmail.com");
                message.setSubject("Simple test mail from VU");  // mail subject
                message.setText("This is sample email body for my first email"); // all mail descriptions

                mailSender.send(message);
                return "success!";  // return message
            }
            catch (Exception e)
            {
                return e.getMessage();
            }
        }
        // 2nd EndPoint

       @RequestMapping("/send-email-with-attachment")  // create endpoint
         public String sendEmailWithAttachment()       // crate method
      {
         try {

             //MimeMessage is part of the JavaMail API and supports complex email types like HTML, attachments, images
            MimeMessage message = mailSender.createMimeMessage();

             MimeMessageHelper helper = new MimeMessageHelper(message,true);

            helper.setFrom("vishalubale1506@gmail.com"); // call the method
            helper.setTo("ubalevishal2019@gmail.com");
            helper.setSubject("Java email with attachment | From VU");  // mail subject
            helper.setText("Please find the attached documents below"); // all mail descriptions
            helper.addAttachment("vishal.jpeg", new File("C:\\Users\\DC\\OneDrive\\Documents\\Attachment\\vishal.jpeg")); //provide the path
            helper.addAttachment("Vishal Ubale.pdf", new File("C:\\Users\\DC\\OneDrive\\Documents\\Attachment\\Vishal Ubale.pdf"));

            mailSender.send(message);
            return "success!";  // return message
        }
        catch (Exception e)
        {
            return e.getMessage();
        }
    }
}

