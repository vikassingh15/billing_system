package com.product.billing.service;

import java.util.List;

import com.product.billing.dto.UserDTO;
import com.product.billing.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
    
    List<User> getUsers(int isDeleted);
    
    List<User> getUsers();

    UserDTO getUserById(long id);
}
