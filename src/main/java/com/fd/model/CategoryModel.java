package com.fd.model;

import java.util.List;

import com.fd.entities.CategoryItems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryModel {

	private long id;
	private String categoryName;
	private boolean disabled;
	List<CategoryItems> cItems;

}
