package com.wafflestory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wafflestory.entities.Sections;
import com.wafflestory.entities.SubSections;
import com.wafflestory.repo.SectionsRepo;
import com.wafflestory.repo.SubSectionsRepo;

@Service
public class SubSectionService {

    @Autowired
    private SubSectionsRepo subSectionRepository;

    // Get all subsections
    public List<SubSections> getAllSubSections() {
        return subSectionRepository.findAll();
    }
    
    public List<SubSections> getAllSubSectionsbyName(int id) {
    	System.out.println(id);
        return subSectionRepository.findAllBySectionId(id);
    }

    // Add a new subsection
    public SubSections addSubSection(SubSections subSection) {
        return subSectionRepository.save(subSection);
    }

    // Delete a subsection by ID
    public void deleteSubSection(Long id) {
        Optional<SubSections> subSection = subSectionRepository.findById(id);
        if (subSection.isPresent()) {
            subSectionRepository.delete(subSection.get());
        } else {
            throw new RuntimeException("SubSection not found with id: " + id);
        }
    }
}
