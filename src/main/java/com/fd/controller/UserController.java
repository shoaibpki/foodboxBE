package com.fd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fd.model.UserModel;
import com.fd.services.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/signup")
	ResponseEntity<String> saveUser(@RequestBody UserModel userModel) {	
		userService.saveUser(userModel);
		return new ResponseEntity<String>("Registration Successfully", HttpStatus.OK);
	}
	
	@GetMapping("/login")
	ResponseEntity<String> userLogin(@RequestParam String username, 
			@RequestParam String password) {
		Authentication auth = 
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)); 
		return new ResponseEntity<String>("User signin successfully!",HttpStatus.OK);
	}
	
	@PostMapping("/resetpassword")
	public ResponseEntity<String> resetPassword(@RequestParam String email, @RequestParam String newPassword ){
		userService.restpassword(email, newPassword);
		return new ResponseEntity<String>("User Successfully Reset Password!",HttpStatus.OK);
	}

}
