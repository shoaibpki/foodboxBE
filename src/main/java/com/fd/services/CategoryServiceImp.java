package com.fd.services;

import java.time.Instant;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fd.entities.Cart;
import com.fd.entities.Category;
import com.fd.entities.CategoryItems;
import com.fd.entities.Sale;
import com.fd.entities.Users;
import com.fd.model.CartModel;
import com.fd.model.CategoryModel;
import com.fd.model.categoryItemModel;
import com.fd.repositories.CartRepository;
import com.fd.repositories.CategoryRepository;
import com.fd.repositories.SaleRepository;
import com.fd.repositories.UserRepository;
import com.fd.repositories.categoryItemsRepository;


@Service
@Transactional
public class CategoryServiceImp implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private categoryItemsRepository categoryItemsRepository;

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SaleRepository saleRepository;
	
	@Override
	public void saveCategory(CategoryModel categoryModel) {
		
		Category category = new Category();
		
		BeanUtils.copyProperties(categoryModel, category);
		categoryRepository.save(category);
	}

	@Override
	public void disabledCategory(Long id) {
		Category category = categoryRepository.findById(id).get();
		category.setDisabled(true);
		
		categoryRepository.save(category);
	}

	@Override
	public List<Category> allCategories() {
		List<Category> lCategory = categoryRepository.findByDisabled(false);
		return lCategory;
	}

	@Override
	public List<Category> allCategoriesAdmin() {
		List<Category> lCategory = categoryRepository.findAll();
		return lCategory;
	}

	@Override
	public List<CategoryItems> getCategoryById(Long id) {
		List<CategoryItems> ci = categoryItemsRepository.findBycategoryIdAndDisabled(id, false);
		return ci;
	}

	@Override
	public void saveCategoryItm(categoryItemModel categoryItemModel) {
		Category category = categoryRepository.findById(categoryItemModel.getCategoryId())
					.orElseThrow(() -> new RuntimeException("category not found!"));
		
		CategoryItems categoryItems = new CategoryItems();
		BeanUtils.copyProperties(categoryItemModel, categoryItems);
		categoryItems.setCategory(category);
		categoryItemsRepository.save(categoryItems);
		
	}

	@Override
	public void updateCategoryItm(categoryItemModel categoryItemModel) {
		CategoryItems categoryItems = categoryItemsRepository.findById(categoryItemModel.getId())
					.orElseThrow(() -> new RuntimeException("category not found!"));
		
		BeanUtils.copyProperties(categoryItemModel, categoryItems);
		
//		categoryItems.setCategory(category);
		categoryItemsRepository.save(categoryItems);
		
	}

	@Override
	public void disabledCItem(Long id) {
		CategoryItems categoryItems = categoryItemsRepository.findById(id).get();
		categoryItems.setDisabled(true);
		categoryItemsRepository.save(categoryItems);
	}

	@Override
	public CategoryItems getCategoryItemById(Long id) {
		CategoryItems categoryItem = categoryItemsRepository.findById(id).get();
		
		return categoryItem;
	}

	@Override
	public List<CategoryItems> allItemsForAdmin() {
		List<CategoryItems> categoryItems = categoryItemsRepository.findAll();
		
		return categoryItems;
	}

	@Override
	public List<CategoryItems> allItemsForUser() {
		
		List<CategoryItems> lCategoryItems = 
				categoryItemsRepository.findByDisabled(false);
		List<CategoryItems> filterItems = 
				lCategoryItems.stream().filter((data) -> data.getAvailableQty() > 0).toList();
		
		return filterItems;
	}

	@Override
	public void addToCard(CartModel cartModel) {
		Cart cart = new Cart();
		Users user = userRepository.findById(cartModel.getUserId())
				.orElseThrow(() -> new RuntimeException("User not found!"));
		CategoryItems citem = categoryItemsRepository.findById(cartModel.getItemId())
				.orElseThrow(() -> new RuntimeException("Invalid Item"));
		
		BeanUtils.copyProperties(cartModel, cart);
		
		cart.setSaleDate(Instant.now());
		if(cartModel.getId() != 0) {
			cart = cartRepository.findById(cartModel.getId()).get();
			cart.setQuantity(cartModel.getQuantity() + cart.getQuantity());
		}
		cart.setItem(citem);
		cart.setUser(user);
		
		cartRepository.save(cart);
	}

	@Override
	public List<Cart> getCartItemsByUser(Long userId) {
		List<Cart> cart = cartRepository.findByuserId(userId);
		return cart;
	}

	@Override
	public void DeleteItemFromCard(Long id) {
		cartRepository.deleteById(id);
	}

//	@Override
//	public void paymentConfirm(Long userId) {
//		Users user = userRepository.findById(userId)
//				.orElseThrow(() -> new RuntimeException("User not Found!"));
//		List<Cart> cart = cartRepository.findByuserId(userId);
//		cart.forEach(c -> {
//			
////			update sale after payment confirmation
//			Sale sale = new Sale();
//
////			update category_item available Quantity after sale
//			int qty = c.getItem().getAvailableQty();
//			c.getItem().setAvailableQty(qty - c.getQuantity());
//			sale.setItems(c.getItem());
//
//			sale.setPrice(c.getPrice());
//			sale.setQuantity(c.getQuantity());
//			sale.setUser(user);
//			sale.setSaleDate(c.getSaleDate());
//			saleRepository.save(sale);
//			
//		});
//		cartRepository.deleteAll(cart);
//	}

	@Override
	public void paymentConfirm(CartModel cartModel) {
		Cart cart = cartRepository.findById(cartModel.getId())
				.orElseThrow(()-> new RuntimeException("Cart Item not Found!"));
		BeanUtils.copyProperties(cartModel, cart);
		int qty = cart.getItem().getAvailableQty();
		cart.getItem().setAvailableQty(qty - cart.getQuantity());
		cartRepository.save(cart);

		Sale sale = new Sale();
		BeanUtils.copyProperties(cart, sale);
		sale.setId(0);
		sale.setItems(cart.getItem());
		saleRepository.save(sale);
		cartRepository.delete(cart);
	}
}
