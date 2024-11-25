package com.wafflestory.util;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wafflestory.DTO.EmailDetails;
import com.wafflestory.service.EmailService;
import com.wafflestory.service.OrdersService;

@Component
public class TaskSchedulerService {
	@Autowired
	OrdersService ordersService;
	@Autowired private EmailService emailService;
	
	@Scheduled(cron = "0 0 23 * * ?")
	public void emailTaskScheduler() throws IOException {
		Date currentDate = new Date();
		System.out.println("cron job started");
		ExcelGenerator excelGenerator =new ExcelGenerator(ordersService.getAllOrders(currentDate));
		excelGenerator.generateExcelFileToSystem();
		EmailDetails details = new EmailDetails();
		details.setRecipient("thoutisaikrishnacricket@gmail.com");
		details.setMsgBody("HI, This is Todays Sales Report. Date: "+currentDate);
		details.setSubject("Sales Report");
		emailService.sendSimpleMail(details);
		emailService.sendMailWithAttachment(details);
	}

}
