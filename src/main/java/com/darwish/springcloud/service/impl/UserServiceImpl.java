package com.darwish.springcloud.service.impl;

import com.darwish.springcloud.dto.UserDto;
import com.darwish.springcloud.entity.User;
import com.darwish.springcloud.mapper.UserMapper;
import com.darwish.springcloud.repository.UserRepository;
import com.darwish.springcloud.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);

        User savedUser = userRepository.saveAndFlush(user);

        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        UserDto userDto = UserMapper.mapToUserDto(optionalUser.get());
        return userDto;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        User updatedUser;
        if (!optionalUser.isEmpty()) {
            User existUser = optionalUser.get();
            existUser.setFirstName(user.getFirstName());
            existUser.setLastName(user.getLastName());
            existUser.setEmail(user.getEmail());
            updatedUser = userRepository.saveAndFlush(existUser);
        } else {
            log.info("The user doesn't exist, so updating failed");
            updatedUser = optionalUser.get();
        }
        return updatedUser;
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
