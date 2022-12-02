package com.fd.services;

import java.util.List;

import com.fd.entities.Category;
import com.fd.entities.CategoryItems;
import com.fd.model.CategoryModel;
import com.fd.model.categoryItemModel;

public interface CategoryService {
	
	Category saveCategory(CategoryModel categoryModel);
	void deletCategory(Long id);
	
	List<Category> allCategories();
	Category getCategoryById(Long id);
	
	CategoryItems saveCategoryItm(categoryItemModel categoryItemModel);
}
