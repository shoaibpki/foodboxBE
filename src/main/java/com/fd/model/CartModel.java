package com.fd.model;

import java.time.LocalDate;

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
	private LocalDate saleDate;

}
