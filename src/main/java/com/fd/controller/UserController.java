package com.fd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fd.entities.Category;
import com.fd.entities.CategoryItems;
import com.fd.entities.Users;
import com.fd.model.CategoryModel;
import com.fd.model.UserModel;
import com.fd.model.categoryItemModel;
import com.fd.services.CategoryService;
import com.fd.services.UserService;

@RequestMapping("/foodbox/")
@CrossOrigin(origins =  "*")
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	CategoryService categoryService;

	@PostMapping("signup")
	ResponseEntity<Users> saveUser(@RequestBody UserModel userModel) {	
		Users user =  userService.saveUser(userModel);
		return new ResponseEntity<Users>(user, HttpStatus.OK);
	}
	
	@GetMapping("login")
	ResponseEntity<String> userLogin(@RequestParam String username, 
			@RequestParam String password) {
		Authentication auth = 
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		return new ResponseEntity<String>(auth.getName(),HttpStatus.OK);
	}
	
	@PostMapping("resetpassword")
	public ResponseEntity<String> resetPassword(@RequestParam String email, @RequestParam String newPassword ){
		userService.restpassword(email, newPassword);
		return new ResponseEntity<String>("User Successfully Reset Password!",HttpStatus.OK);
	}
	
	@GetMapping("user/{id}")
	public ResponseEntity<Users> getUserByid(@PathVariable Long id){
		Users user = userService.getUserByid(id);
		
		return new ResponseEntity<Users>(user, HttpStatus.OK);
	}
	
//	get user detail
	@GetMapping("getuser/{email}")
	public ResponseEntity<Users> getUserDetail(@PathVariable String email){
		Users user = userService.getUserDetail(email);
		return new ResponseEntity<Users>(user, HttpStatus.OK);
	}

//	add and update category
	@PostMapping("category")
	public ResponseEntity<String> addCategory(@RequestBody CategoryModel categoryModel){
		categoryService.saveCategory(categoryModel);
		
		return new ResponseEntity<String>("Save Category successfully!", HttpStatus.OK);
	}
	
//	add category items
	@PostMapping("manage/product")
	public ResponseEntity<String> addCategoryItem(@RequestBody categoryItemModel categoryItemModel){
		categoryService.saveCategoryItm(categoryItemModel);
		
		return new ResponseEntity<String>("Save Category Item successfully", HttpStatus.OK);
	}
	
//	update categoryItems
	@PutMapping("manage/product")
	public ResponseEntity<String> updateCategoryItm(@RequestBody categoryItemModel categoryItemModel){
		categoryService.updateCategoryItm(categoryItemModel);
		return new ResponseEntity<String>("update Category Item successfully", HttpStatus.OK);
	}

//	for admin category items
	@GetMapping("manage/product")
	public ResponseEntity<List<CategoryItems>> itemsListforAdmin(){
		List<CategoryItems> listItems = categoryService.allItemsForAdmin();
		
		return new ResponseEntity<List<CategoryItems>>(listItems, HttpStatus.OK);
	}
	
//	get all categories for admin
	@GetMapping("manage/product/categories")
	public ResponseEntity<List<Category>> allCategoriesAdmin(){
		List<Category> categories = categoryService.allCategoriesAdmin();
		return new ResponseEntity<List<Category>>(categories,HttpStatus.OK);
	}

}
