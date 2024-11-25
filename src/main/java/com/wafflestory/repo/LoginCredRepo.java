package com.wafflestory.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wafflestory.entities.UserDetailCred;

@Repository
public interface LoginCredRepo extends JpaRepository<UserDetailCred,Long>{

	UserDetailCred getByUsername(String string);

}
