package com.fd.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartModel {

	private long id;
	private double price;
	private int quantity;
	private String image;
	private String saleDate;
	
	private long userId;
	private long itemId;

}
