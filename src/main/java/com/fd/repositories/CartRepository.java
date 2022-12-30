package com.fd.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fd.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	
	List<Cart> findByuserId(Long usrId);
	
	Integer countByuserId(Long userId);
	
	
	
}
