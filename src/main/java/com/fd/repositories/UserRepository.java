package com.fd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fd.entities.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

}
