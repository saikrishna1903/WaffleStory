package com.wafflestory.controller;

import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wafflestory.entities.UserDetailCred;
import com.wafflestory.service.JwtService;
import com.wafflestory.service.UserDetailService;

import io.jsonwebtoken.Jwt;

@RestController
public class UsersController {
	@Autowired
    private UserDetailService userService;
	
	@Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    // Get all users
    @GetMapping("/allUsers")
    public List<UserDetailCred> getAllUsers() {
    	System.out.println(SecurityContextHolder.getContext());
        return userService.getAllUsers();
    }

    // Add a new user
    @PostMapping("/addUser")
    public ResponseEntity<UserDetailCred> addNewUser(@RequestBody UserDetailCred user) {
    	UserDetailCred createdUser = userService.addUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // Delete a user by ID
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/generateToken")
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody UserDetailCred authRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok(jwtService.generateToken(authRequest.getUsername()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
    
}
