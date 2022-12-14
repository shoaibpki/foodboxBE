package com.fd.services;

import java.util.List;

import com.fd.entities.Cart;
import com.fd.entities.Category;
import com.fd.entities.CategoryItems;
import com.fd.model.CartModel;
import com.fd.model.CategoryModel;
import com.fd.model.categoryItemModel;

public interface CategoryService {
	
	void saveCategory(CategoryModel categoryModel);
	void disabledCategory(Long id);
	
	List<Category> allCategories();
	List<CategoryItems> getCategoryById(Long id);
	
	void saveCategoryItm(categoryItemModel categoryItemModel);
	List<CategoryItems> allItemsForUser();
	void disabledCItem(Long id);
	CategoryItems getCategoryItemById(Long id);
	List<CategoryItems> allItemsForAdmin();
	
	void addToCard(CartModel cartModel);
	List<Cart> getCartItemsByUser(Long userId);
	void DeleteItemFromCard(Long id);
	void paymentConfirm(CartModel cardModel);
//	void updateCart(CartModel cardModel);
	List<Category> allCategoriesAdmin();
	
	void updateCategoryItm(categoryItemModel categoryItemModel);
	
}
