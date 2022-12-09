package com.fd.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fd.entities.Users;
import com.fd.model.UserModel;
import com.fd.repositories.UserRepository;

@Service
public class UserServiceImp implements UserService, UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Users saveUser(UserModel userModel) {
		Optional<Users> oUser = userRepository.findByEmail(userModel.getEmail());
		
		if(oUser.isPresent()) {
			throw new RuntimeException("Username already exist!");
		}
		Users user = new Users();
		// make password encrypted
		String encPass = passwordEncoder.encode(userModel.getPassword());
		userModel.setPassword(encPass);

		BeanUtils.copyProperties(userModel, user);
		userRepository.save(user);
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String email) 
			throws UsernameNotFoundException {
		Users user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("user already exist!"));
		
		User springUser = null;
		
		Set<GrantedAuthority> ga = new HashSet<GrantedAuthority>();
		ga.add( new SimpleGrantedAuthority(user.getRole()));
		
		springUser = new User(email, user.getPassword(), ga);
		return springUser;
	}

	@Override
	public void restpassword(String email, String newPassword) {
		Users user = userRepository.findByEmail(email)
				.orElseThrow(()-> new UsernameNotFoundException(email));
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);
	}

	@Override
	public Users getUserDetail(String email) {
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found"));
	}

	@Override
	public Users getUserByid(Long id) {
		Users user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User Not Found"));
		return user;
	}
}
