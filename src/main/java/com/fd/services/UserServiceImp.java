package com.fd.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fd.entities.Users;
import com.fd.model.UserModel;
import com.fd.repositories.UserRepository;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public Users saveUser(UserModel userModel) {
		Users user = new Users();
		
		// make password encrypted
		String encPass = passwordEncoder.encode(userModel.getPassword());
		userModel.setPassword(encPass);
		
		BeanUtils.copyProperties(userModel, user);
		userRepository.save(user);
		return user;
	}
}
