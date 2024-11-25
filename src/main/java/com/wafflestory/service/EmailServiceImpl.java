package com.wafflestory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.wafflestory.DTO.EmailDetails;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;

@Service
//Class
//Implementing EmailService interface
public class EmailServiceImpl implements EmailService {

 @Autowired private JavaMailSender javaMailSender;

 @Value("${spring.mail.username}") private String sender;

 // Method 1
 // To send a simple email
 public String sendSimpleMail(EmailDetails details)
 {

     // Try block to check for exceptions
     try {

         // Creating a simple mail message
         SimpleMailMessage mailMessage
             = new SimpleMailMessage();

         // Setting up necessary details
         mailMessage.setFrom(sender);
         mailMessage.setTo(details.getRecipient());
         mailMessage.setText(details.getMsgBody());
         mailMessage.setSubject(details.getSubject());

         // Sending the mail
         javaMailSender.send(mailMessage);
         return "Mail Sent Successfully...";
     }

     // Catch block to handle the exceptions
     catch (Exception e) {
    	 System.out.println(e);
         return "Error while Sending Mail";
     }
 }

 // Method 2
 // To send an email with attachment
 public String
 sendMailWithAttachment(EmailDetails details) throws IOException
 {
     // Creating a mime message
     MimeMessage mimeMessage
         = javaMailSender.createMimeMessage();
     MimeMessageHelper mimeMessageHelper;

     try {

         // Setting multipart as true for attachments to
         // be send
         mimeMessageHelper
             = new MimeMessageHelper(mimeMessage, true);
         mimeMessageHelper.setFrom(sender);
         mimeMessageHelper.setTo(details.getRecipient());
         mimeMessageHelper.setText(details.getMsgBody());
         mimeMessageHelper.setSubject(
             details.getSubject());
         
         String filePath = System.getProperty("java.io.tmpdir") + "/Sales_Report_";
         File file =new File(filePath,"generatedExcel.xlsx");
         
         ClassPathResource resource = new ClassPathResource("/excel/generatedExcel.xlsx");  // Path relative to resources
         if (file.exists()) {
        	 mimeMessageHelper.addAttachment(file.getName(), file);  // Attach the file
         } else {
             throw new IOException("Attachment not found in resources folder.");
         }

         // Sending the mail
         javaMailSender.send(mimeMessage);
         return "Mail sent Successfully";
     }

     // Catch block to handle MessagingException
     catch (MessagingException e) {

         // Display message when exception occurred
         return "Error while sending mail!!!";
     }
 }
}
