package com.wafflestory.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="subsec")
public class SubSections {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
    private Long id;
	@JsonIgnore
	private String name;
    @ManyToOne
    @JoinColumn(name = "section_id")
    
    private Sections section;  // Many waffles can belong to one section

    @JsonIgnoreProperties(value = { "id","name"})
    //@JsonIgnore
    @OneToMany(mappedBy = "subSections", cascade = CascadeType.ALL)
    private List<WaffleTypes> waffleTypes;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Sections getSection() {
		return section;
	}

	public void setSection(Sections section) {
		this.section = section;
	}

	public List<WaffleTypes> getWaffleTypes() {
		return waffleTypes;
	}

	public void setWaffleTypes(List<WaffleTypes> waffleTypes) {
		this.waffleTypes = waffleTypes;
	}

}

