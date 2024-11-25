package com.wafflestory.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wafflestory.entities.WaffleTypes;

public interface WaffleTypesRepo extends JpaRepository<WaffleTypes,Long>{

}
