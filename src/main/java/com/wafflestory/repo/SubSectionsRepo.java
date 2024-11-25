package com.wafflestory.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wafflestory.entities.SubSections;

public interface SubSectionsRepo extends JpaRepository<SubSections,Long>{

	List<SubSections> findByName(String name);

	List<SubSections> findAllById(long id);

	List<SubSections> findAllBySectionId(int id);


}
