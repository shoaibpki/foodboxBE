package com.fd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fd.entities.Users;
import com.fd.model.UserModel;
import com.fd.services.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;

	@PostMapping("/signup")
	ResponseEntity<String> saveUser(@RequestBody UserModel userModel) {
		
		userService.saveUser(userModel);
		return new ResponseEntity<String>("Registration Successfully", HttpStatus.OK);
	}
}
