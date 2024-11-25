package com.wafflestory.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wafflestory.DTO.EmailDetails;
import com.wafflestory.DTO.OrderListDTO;
import com.wafflestory.service.EmailService;
import com.wafflestory.service.OrdersService;
import com.wafflestory.util.ExcelGenerator;

import jakarta.servlet.http.HttpServletResponse;

@RestController
//Class
public class EmailController {

 @Autowired private EmailService emailService;
 
 @Autowired
	OrdersService ordersService;

 // Sending a simple Email
 @PostMapping("/getmail")
 public String
 sendMail(@RequestBody EmailDetails details)
 {
     String status
         = emailService.sendSimpleMail(details);

     return status;
 }

 // Sending email with attachment
 @PostMapping("/sendMailWithAttachment")
 public String sendMailWithAttachment(
     @RequestBody EmailDetails details) throws IOException
 {
     String status
         = emailService.sendMailWithAttachment(details);

     return status;
 }
 
 @GetMapping("/export-to-excel")
 public void exportIntoExcelFile(HttpServletResponse response) throws IOException {
     response.setContentType("text/csv");
     Date currentDate = new Date();

     String headerKey = "Content-Disposition";
     String headerValue = "attachment; filename=WaffleStory" + currentDate.getDate() + ".xlsx";
     response.setHeader(headerKey, headerValue);

     List<OrderListDTO> ordersList = ordersService.getAllOrders(currentDate);
     ExcelGenerator generator = new ExcelGenerator(ordersList);
     generator.generateExcelFile(response);
 }
}
