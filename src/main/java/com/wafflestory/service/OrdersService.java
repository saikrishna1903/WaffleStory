package com.wafflestory.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wafflestory.DTO.OrderListDTO;
import com.wafflestory.repo.OrdersRepo;

@Service
public class OrdersService {

	@Autowired
	OrdersRepo ordersRepo;

	public void saveOrders(OrderListDTO ordersDTO) {
Calendar calendar = Calendar.getInstance();
        
        // Get the current date and time
        Date currentDateTime = calendar.getTime();
        ordersDTO.setTimestamp(currentDateTime);
		ordersRepo.save(ordersDTO);
	}

	public List<OrderListDTO> getAllOrders(Date date) {
		Date startOfDay = getStartOfDay(date); // 12 AM
		Date endOfDay = getEndOfNoon(date);
		return ordersRepo.findOrdersInDateRange(startOfDay, endOfDay);
	}

	private Date getStartOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();

		// Set the calendar to today's date
		calendar.setTime(new Date());

		// Set the time to 12:00 AM (Start of the Day)
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	// Helper method to get the end of noon (12 PM)
	private Date getEndOfNoon(Date date) {
		Calendar calendar = Calendar.getInstance();

		// Set the calendar to today's date
		calendar.setTime(new Date());

		// Set the time to 11:59:59.999 PM (End of the Day)
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);

		// Get the date with time set to 11:59:59.999 PM
		return calendar.getTime();
		
	}
	public Date getStartOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);  // Set to Monday
        calendar.set(Calendar.HOUR_OF_DAY, 0);  // Set to start of the day
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public Date getEndOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);  // Set to Sunday
        calendar.set(Calendar.HOUR_OF_DAY, 23);  // Set to end of the day
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public Date getStartOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);  // Set to the first day of the month
        calendar.set(Calendar.HOUR_OF_DAY, 0);  // Set to start of the day
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public Date getEndOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  // Set to last day of the month
        calendar.set(Calendar.HOUR_OF_DAY, 23);  // Set to end of the day
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public Date getStartOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);  // Set to January
        calendar.set(Calendar.DAY_OF_MONTH, 1);  // Set to the first day of the year
        calendar.set(Calendar.HOUR_OF_DAY, 0);  // Set to start of the day
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public Date getEndOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);  // Set to December
        calendar.set(Calendar.DAY_OF_MONTH, 31);  // Set to the last day of the year
        calendar.set(Calendar.HOUR_OF_DAY, 23);  // Set to end of the day
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }
	
	public List<Double> getAmounts(){
		Date currentDate = new Date();
		List<Double> amount = new ArrayList<>();
		List<OrderListDTO> l1 =ordersRepo.findOrdersInDateRange(getStartOfDay(currentDate), getEndOfNoon(currentDate));
		List<OrderListDTO> l2 =ordersRepo.findOrdersInWeek(getStartOfWeek(currentDate), getEndOfWeek(currentDate));
		List<OrderListDTO> l3 =ordersRepo.findOrdersInMonth(getStartOfMonth(currentDate), getEndOfMonth(currentDate));
		List<OrderListDTO> l4 =ordersRepo.findOrdersInYear(getStartOfYear(currentDate), getEndOfYear(currentDate));
		
		Double u1= l1.stream()
        .flatMap(order -> order.getItems().stream())  // Flatten the list of items
        .mapToDouble(item -> item.getPrice() * item.getQuantity())  // Multiply price by quantity
        .sum();
		Double u2= l2.stream()
		        .flatMap(order -> order.getItems().stream())  // Flatten the list of items
		        .mapToDouble(item -> item.getPrice() * item.getQuantity())  // Multiply price by quantity
		        .sum();
		Double u3= l3.stream()
		        .flatMap(order -> order.getItems().stream())  // Flatten the list of items
		        .mapToDouble(item -> item.getPrice() * item.getQuantity())  // Multiply price by quantity
		        .sum();
		Double u4= l4.stream()
		        .flatMap(order -> order.getItems().stream())  // Flatten the list of items
		        .mapToDouble(item -> item.getPrice() * item.getQuantity())  // Multiply price by quantity
		        .sum();
		amount.add(u1);
		amount.add(u2);
		amount.add(u3);
		amount.add(u4);
		return amount;
	}

}
