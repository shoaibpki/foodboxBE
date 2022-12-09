package com.fd.services;

import com.fd.entities.Users;
import com.fd.model.UserModel;

public interface UserService {
	
	public Users saveUser(UserModel userModel);

	public void restpassword(String username, String newPassword);

	public Users getUserDetail(String email);

	public Users getUserByid(Long id);
}
