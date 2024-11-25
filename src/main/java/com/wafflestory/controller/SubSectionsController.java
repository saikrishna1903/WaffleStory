package com.wafflestory.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wafflestory.entities.SubSections;
import com.wafflestory.service.SubSectionService;

@RestController
public class SubSectionsController {
	@Autowired
    private SubSectionService subSectionService;

    // Get all subsections
    @GetMapping("/allSebSection")
    public List<SubSections> getAllSubSections() {
        return subSectionService.getAllSubSections();
    }
    
 // Get all subsections
    @GetMapping("/allSebSection/{id}")
    public List<SubSections> getAllSubSectionsByName(@PathVariable int id) {
        return subSectionService.getAllSubSectionsbyName(id);
    }

    // Add a new subsection
    @PostMapping("/addSebSection")
    public ResponseEntity<SubSections> addNewSubSection(@RequestBody SubSections subSection) {
        SubSections createdSubSection = subSectionService.addSubSection(subSection);
        return ResponseEntity.ok(createdSubSection);
    }

    // Delete a subsection by ID
    @DeleteMapping("/deleteSebSection/{id}")
    public ResponseEntity<Void> deleteSubSection(@PathVariable Long id) {
        subSectionService.deleteSubSection(id);
        return ResponseEntity.noContent().build();
    }
}
