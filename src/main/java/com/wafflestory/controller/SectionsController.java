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

import com.wafflestory.entities.Sections;
import com.wafflestory.service.SectionService;

@RestController
public class SectionsController {
	
	@Autowired
	SectionService sectionService;
	
	@GetMapping("/allSections")
    public List<Sections> getAllSections() {
        return sectionService.getAllSections();
    }

    // Add a new section
    @PostMapping("/addSection")
    public ResponseEntity<Sections> addNewSection(@RequestBody Sections section) {
        Sections createdSection = sectionService.addSection(section);
        return ResponseEntity.ok(createdSection);
    }

    // Delete a section by ID
    @DeleteMapping("/deleteSection/{id}")
    public ResponseEntity<Void> deleteSection(@PathVariable Long id) {
        sectionService.deleteSection(id);
        return ResponseEntity.noContent().build();
    }

}
