package com.wafflestory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wafflestory.DTO.WaffleTypeDTO;
import com.wafflestory.config.SecurityConfig;
import com.wafflestory.entities.WaffleTypes;
import com.wafflestory.service.WaffleTypesService;

@RestController
public class WaffleTypesController {
	 @Autowired
	    private WaffleTypesService waffleTypeService;

	    // Get all waffle types
	    @GetMapping("/allWaffleTypes")
	    public List<WaffleTypes> getAllWaffleTypes() {	    	
	        return waffleTypeService.getAllWaffleTypes();
	    }
	    
	    @GetMapping("/allWaffleTypes/{id}")
	    public List<WaffleTypeDTO> getAllWaffleTypesById(@PathVariable Long id) {	    	
	        return waffleTypeService.getAllWaffleTypesById(id);
	    }
	    

	    // Add a new waffle type
	    @PostMapping("/addWaffleTypes")
	    public ResponseEntity<WaffleTypes> addNewWaffleType(@RequestBody WaffleTypes waffleType) {
	    	WaffleTypes createdWaffleType = waffleTypeService.addWaffleType(waffleType);
	        return ResponseEntity.ok(createdWaffleType);
	    }

	    // Delete a waffle type by ID
	    @DeleteMapping("/deleteWaffleType/{id}")
	    public ResponseEntity<Void> deleteWaffleType(@PathVariable Long id) {
	        waffleTypeService.deleteWaffleType(id);
	        return ResponseEntity.noContent().build();
	    }
	}
