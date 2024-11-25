package com.wafflestory.service;

import java.io.IOException;

import com.wafflestory.DTO.EmailDetails;

public interface EmailService {

    // Method
    // To send a simple email
    String sendSimpleMail(EmailDetails details);

    // Method
    // To send an email with attachment
    String sendMailWithAttachment(EmailDetails details) throws IOException;
}