package com.wafflestory.entities;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="waffletype")
public class WaffleTypes {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; 
    private int price;
    @ManyToOne
    @JoinColumn(name = "subSections_id")
    //@JsonIgnore
    @JsonIgnoreProperties(value = { "id","name","waffleTypes"})
    private SubSections subSections;
    
    // Many subtypes can belong to one waffle
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public SubSections getSubSections() {
		return subSections;
	}
	public void setSubSections(SubSections subSections) {
		this.subSections = subSections;
	}

}
