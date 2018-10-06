package com.product.billing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.billing.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
    User findByUsername(String username);
	List<User> findAllByIsDeleted(int isDeleted);

}