package com.darwish.springcloud.service.impl;

import com.darwish.springcloud.entity.User;
import com.darwish.springcloud.repository.UserRepository;
import com.darwish.springcloud.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
}
