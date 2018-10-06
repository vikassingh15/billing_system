package com.product.billing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.product.billing.dto.UserDTO;
import com.product.billing.model.User;
import com.product.billing.repository.UserRepository;
import com.product.billing.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

	@Override
	public List<User> getUsers() {
		return this.userRepository.findAll();
	}

	@Override
	public UserDTO getUserById(long id) {
		return new UserDTO(this.userRepository.getOne(id));
	}

	@Override
	public List<User> getUsers(int isDeleted) {
		return this.userRepository.findAllByIsDeleted(isDeleted);
	}
}
