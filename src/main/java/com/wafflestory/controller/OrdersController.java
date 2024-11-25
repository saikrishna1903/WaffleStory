package com.wafflestory.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wafflestory.DTO.OrderListDTO;
import com.wafflestory.DTO.OrdersDTO;
import com.wafflestory.DTO.SalesDTO;
import com.wafflestory.service.OrdersService;

@RestController
public class OrdersController {
	
	@Autowired
	OrdersService ordersService;
	
	@PostMapping("/submitOrder")
	public ResponseEntity<Map<String, String>> addNewSection(@RequestBody OrderListDTO ordersDTO) {
	    System.out.println("Received orders: " + ordersDTO);
	 
	    if (ordersDTO.getItems() != null) {
	    	ordersService.saveOrders(ordersDTO);
	        for (OrdersDTO order : ordersDTO.getItems()) {
	            System.out.println("Order name: " + order.getName() + ", Price: " + order.getPrice()+ " quantity: " + order.getQuantity());
	        }
	    }
	    
	    Map<String, String> response = new HashMap<>();
	    response.put("message", "Order submitted successfully!");
	    
	    return ResponseEntity.ok(response);
	}

	@GetMapping("/getAllOrders")
	public List<OrderListDTO> getAllOrders(@RequestParam String date){
		Date parsedDate = parseDate(date);
		return ordersService.getAllOrders(parsedDate);
	}
	
	@GetMapping("/getSalesData")
    public SalesDTO getSalesData() {
        // For the sake of the example, we are returning hardcoded values
		List<Double> amounts= ordersService.getAmounts();
		Double daySales = amounts.get(0);   // Fetch from database
		Double weeklySales = amounts.get(1);  // Fetch from database
		Double monthlySales = amounts.get(2); // Fetch from database
		Double yearlySales = amounts.get(3);  // Fetch from database

        // Return as a SalesData object
        return new SalesDTO(daySales, weeklySales, monthlySales, yearlySales);
    }
	
	
	private Date parseDate(String dateStr) {
        try {
            return new java.text.SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (Exception e) {
            throw new RuntimeException("Invalid date format. Please use yyyy-MM-dd.");
        }
    }
}
