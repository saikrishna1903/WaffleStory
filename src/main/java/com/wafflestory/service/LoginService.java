package com.wafflestory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wafflestory.entities.UserDetailCred;
import com.wafflestory.repo.LoginCredRepo;
import com.wafflestory.util.UserInfoDetails;

@Service
public class LoginService implements UserDetailsService{
	
	@Autowired
	LoginCredRepo loginCredRepo;

	 @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        UserDetailCred userDetail = loginCredRepo.getByUsername(username); 
	        if(userDetail!=null) {
	        	UserInfoDetails userInfoDetails = new UserInfoDetails(userDetail);
	        	return userInfoDetails;
	        }else {
	        	throw new UsernameNotFoundException("User not found: " + username);
	        }
	    }
	
}
