package com.fd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fd.entities.CategoryItems;

public interface categoryItemsRepository extends JpaRepository<CategoryItems, Long> {

}
