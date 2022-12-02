package com.fd.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CategoryItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String itemName;
	private String image;
	private double price;
	private boolean disabled;
	private int availableQty;
	
	@ManyToOne
	private Category category;
	
	@ManyToMany(mappedBy = "cItems")
	private List<Users> users = new ArrayList<>();
	
}
