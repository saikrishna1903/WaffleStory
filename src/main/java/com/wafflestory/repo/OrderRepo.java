package com.wafflestory.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wafflestory.DTO.OrderListDTO;
import com.wafflestory.DTO.OrdersDTO;

@Repository
public interface OrderRepo extends JpaRepository<OrdersDTO,Long>{

}
