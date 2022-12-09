package com.fd.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fd.entities.CategoryItems;

public interface categoryItemsRepository extends JpaRepository<CategoryItems, Long> {

	List<CategoryItems> findByDisabled(boolean disable);
	
	List<CategoryItems> findBycategoryId(Long cid);

}
