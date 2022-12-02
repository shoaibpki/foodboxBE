package com.fd.model;

import java.util.ArrayList;
import java.util.List;

import com.fd.entities.Category;
import com.fd.entities.Users;

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
	private long categoryId;
//	private List<Users> users = new ArrayList<>();

}
