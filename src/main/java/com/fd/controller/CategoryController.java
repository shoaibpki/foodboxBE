package com.fd.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fd.entities.Cart;
import com.fd.entities.Category;
import com.fd.entities.CategoryItems;
import com.fd.model.CartModel;
import com.fd.services.CategoryService;

@RequestMapping("/foodbox/")
@CrossOrigin(origins =  "*")
@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	
//	category list
	@GetMapping("category")
	public ResponseEntity<List<Category>> getAllCategories(){
		List<Category> lCategory = categoryService.allCategories();
		
		return new ResponseEntity<List<Category>>(lCategory, HttpStatus.OK);
	}

//	list of items by Category
	@GetMapping("{id}")
	public ResponseEntity<List<CategoryItems>> getCategoryById(@PathVariable Long id){
		List<CategoryItems> lCategory = categoryService.getCategoryById(id);
		
		return new ResponseEntity<List<CategoryItems>>(lCategory, HttpStatus.OK);
	}

//	for user list category items
	@GetMapping("/productview")
	public ResponseEntity<List<CategoryItems>> itemsList(){
		List<CategoryItems> listItems = categoryService.allItemsForUser();
		
		return new ResponseEntity<List<CategoryItems>>(listItems, HttpStatus.OK);
	}
	
//	get category item by id
	@GetMapping("categoryitem/{id}")
	public ResponseEntity<CategoryItems> getCategoryItemById(@PathVariable Long id){
		CategoryItems categoryItem =  categoryService.getCategoryItemById(id);
		
		return new ResponseEntity<CategoryItems>(categoryItem, HttpStatus.OK);
	}

	//	add items into the cart
	@PostMapping("cart/addtocart")
	public void addToCard(@RequestBody CartModel cartModel){
		categoryService.addToCard(cartModel);
	}
	
//	delete items from the cart
	@DeleteMapping("categoryitem/delete/{id}")
	public void DeleteItemFromCard(@PathVariable Long id){
		categoryService.DeleteItemFromCard(id);
	}
	
//	on payment confirmation data move from cart to sale table
	@PostMapping("paymentconfirm/{uid}/{mode}")
	public ResponseEntity<String> paymentConfirm(@PathVariable(name = "uid") Long userId, @PathVariable(name = "mode") String payMode){
		categoryService.paymentConfirm(userId, payMode);
		
		return new ResponseEntity<String>("Thank you for Choosing FooxBox, keep in touch!", HttpStatus.OK);
	}
	
//	get items from the cart by user
	@GetMapping("card/show/{userid}")
	public ResponseEntity<List<Cart>> getCartItems(@PathVariable Long userid){
		List<Cart> cartList = categoryService.getCartItemsByUser(userid);
		
		return new ResponseEntity<List<Cart>>(cartList, HttpStatus.OK);
	}
}
