package com.fd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fd.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

}
