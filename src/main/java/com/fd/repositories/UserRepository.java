package com.fd.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fd.entities.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
	
	Optional<Users> findByEmail(String email);

}
