package com.fd.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private long id;
	
	private String itemName;
	private String itemDescription;
	private String image;
	private double price;
	private boolean disabled;
	private int availableQty;
	
	@JsonIgnore
	@ManyToOne
	private Category category;
	
	@OneToMany 
	@JoinColumn(name = "item_id")
	private List<Cart> CartItems = new ArrayList<>();
	
	@OneToMany(mappedBy = "Items")
	List<Sale> saleItems = new ArrayList<>();
}
