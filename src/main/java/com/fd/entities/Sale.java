package com.fd.entities;


import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "saleId")
	private long id;
	
	private double price;
	private int quantity;
	private String payMode;
	private Instant saleDate;

	@JsonIgnore
	@ManyToOne
	private CategoryItems Items;
	
	@JsonIgnore
	@ManyToOne
	private Users user;
}
