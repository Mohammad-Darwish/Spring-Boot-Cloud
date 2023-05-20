package com.darwish.springcloud.service;

import com.darwish.springcloud.dto.UserDto;
import com.darwish.springcloud.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long id);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUserById(Long id);
}
