package com.darwish.springcloud.service;

import com.darwish.springcloud.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    User getUserById(Long id);

    List<User> getAllUsers();

    User updateUser(User user);
}
