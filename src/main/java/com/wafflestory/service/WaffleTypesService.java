package com.wafflestory.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wafflestory.DTO.WaffleTypeDTO;
import com.wafflestory.entities.WaffleTypes;
import com.wafflestory.repo.WaffleTypesRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WaffleTypesService {

    @Autowired
    private WaffleTypesRepo waffleTypeRepository;

    // Get all waffle types
    public List<WaffleTypes> getAllWaffleTypes() {
        return waffleTypeRepository.findAll();
    }

    // Add a new waffle type
    public WaffleTypes addWaffleType(WaffleTypes waffleType) {
        return waffleTypeRepository.save(waffleType);
    }

    // Delete a waffle type by ID
    public void deleteWaffleType(Long id) {
        Optional<WaffleTypes> waffleType = waffleTypeRepository.findById(id);
        if (waffleType.isPresent()) {
            waffleTypeRepository.delete(waffleType.get());
        } else {
            throw new RuntimeException("WaffleType not found with id: " + id);
        }
    }

	public List<WaffleTypeDTO> getAllWaffleTypesById(Long id) {
		List<WaffleTypes> waffs = getAllWaffleTypes();
		List<WaffleTypeDTO> allWaffleDTOs = waffs.stream()
                .map(waffle -> new WaffleTypeDTO(waffle.getSubSections().getId(), waffle.getName(), waffle.getPrice()))
                .collect(Collectors.toList());
		List<WaffleTypeDTO> waffleDTOs = allWaffleDTOs.stream().filter(waffle -> waffle.getId() == id && waffle.getPrice()!=0).toList();
		return waffleDTOs;
	}
}
