package com.wafflestory.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wafflestory.DTO.OrderListDTO;

@Repository
public interface OrdersRepo extends JpaRepository<OrderListDTO,Long>{

	@Query("SELECT s FROM OrderListDTO s WHERE s.timestamp BETWEEN :startOfDay AND :endOfDay")
    List<OrderListDTO> findOrdersInDateRange(Date startOfDay, Date endOfDay);
	
	@Query("SELECT s FROM OrderListDTO s WHERE s.timestamp BETWEEN :startOfWeek AND :endOfWeek")
	List<OrderListDTO> findOrdersInWeek(@Param("startOfWeek") Date startOfWeek, @Param("endOfWeek") Date endOfWeek);

	@Query("SELECT s FROM OrderListDTO s WHERE s.timestamp BETWEEN :startOfMonth AND :endOfMonth")
	List<OrderListDTO> findOrdersInMonth(@Param("startOfMonth") Date startOfMonth, @Param("endOfMonth") Date endOfMonth);

	@Query("SELECT s FROM OrderListDTO s WHERE s.timestamp BETWEEN :startOfYear AND :endOfYear")
	List<OrderListDTO> findOrdersInYear(@Param("startOfYear") Date startOfYear, @Param("endOfYear") Date endOfYear);

}
