package com.fd.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String email;
	private boolean disabled;
	private String role;
	private String password;
	
	@OneToMany(mappedBy = "user")
	List<Cart> cartItems = new ArrayList<>();

	@OneToMany(mappedBy = "user")
	List<Sale> saleItems = new ArrayList<>();
}
