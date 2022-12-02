package com.fd.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fd.entities.Category;
import com.fd.entities.CategoryItems;
import com.fd.model.CategoryModel;
import com.fd.model.categoryItemModel;
import com.fd.repositories.CategoryRepository;
import com.fd.repositories.categoryItemsRepository;


@Service
@Transactional
public class CategoryServiceImp implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private categoryItemsRepository categoryItemsRepository;

	@Override
	public Category saveCategory(CategoryModel categoryModel) {
		
		Category category = new Category();
		
		BeanUtils.copyProperties(categoryModel, category);
		categoryRepository.save(category);
		return category;
	}

	@Override
	public void deletCategory(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Category> allCategories() {
		List<Category> lCategory = categoryRepository.findAll();
		
		return lCategory;
	}

	@Override
	public Category getCategoryById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryItems saveCategoryItm(categoryItemModel categoryItemModel) {

		CategoryItems categoryItems = new CategoryItems();
		BeanUtils.copyProperties(categoryItemModel, categoryItems);
		categoryItemsRepository.save(categoryItems);
		
		return categoryItems;
	}
	
}
