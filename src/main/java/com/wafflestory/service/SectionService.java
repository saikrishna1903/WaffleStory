package com.wafflestory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wafflestory.entities.Sections;
import com.wafflestory.repo.SectionsRepo;

@Service
public class SectionService {

	@Autowired
	SectionsRepo sectionRepository;
	
	public List<Sections> getAllSections() {
        return sectionRepository.findAll();
    }

    // Add a new section
    public Sections addSection(Sections section) {
        return sectionRepository.save(section);
    }

    // Delete a section by ID
    public void deleteSection(Long id) {
        Optional<Sections> section = sectionRepository.findById(id);
        if (section.isPresent()) {
            sectionRepository.delete(section.get());
        } else {
            throw new RuntimeException("Section not found with id: " + id);
        }
    }
}
