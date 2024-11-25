package com.wafflestory.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import lombok.Data;


@Entity
@Data
@Table(name="users")
public class UserDetailCred {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	private String username;
	private String password;
	private String roles;
	
	public String getRoles() {
		return roles;
	}
	public void setRole(String roles) {
		this.roles = roles;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
