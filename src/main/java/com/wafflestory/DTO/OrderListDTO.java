package com.wafflestory.DTO;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class OrderListDTO {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@OneToMany(cascade = CascadeType.ALL)
    private List<OrdersDTO> items;

    private Date timestamp;
    
    private String currentOrderTime;
	
	public String getCurrentOrderTime() {
		return currentOrderTime;
	}

	public void setCurrentOrderTime(String currentOrderTime) {
		this.currentOrderTime = currentOrderTime;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<OrdersDTO> getItems() {
		return items;
	}

	public void setItems(List<OrdersDTO> items) {
		this.items = items;
	}

	public OrderListDTO(List<OrdersDTO> items) {
		super();
		this.items = items;
	}

	public OrderListDTO() {
		super();
	}
}
