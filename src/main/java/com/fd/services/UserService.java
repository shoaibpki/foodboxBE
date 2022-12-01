package com.fd.services;

import com.fd.entities.Users;
import com.fd.model.UserModel;

public interface UserService {
	
	Users saveUser(UserModel userModel);
}
