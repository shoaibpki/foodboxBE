package com.fd.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

	private Long id;
	
	private String name;
	private String email;
	private boolean disabled=false;
	private String role;
	private String password;

}
