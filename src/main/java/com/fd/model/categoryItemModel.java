package com.fd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class categoryItemModel {

	private long id;
	private String itemName;
	private String image;
	private double price;
	private boolean disabled;
	private int availableQty;
	private String itemDescription;
	private long categoryId;
//	private List<Users> users = new ArrayList<>();

}
