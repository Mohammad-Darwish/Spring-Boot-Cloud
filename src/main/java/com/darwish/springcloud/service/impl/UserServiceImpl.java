package com.darwish.springcloud.service.impl;

import com.darwish.springcloud.dto.UserDto;
import com.darwish.springcloud.entity.User;
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
        // convert user dto to jpa user
        User user = new User(
            userDto.getId(),
            userDto.getFirstName(),
            userDto.getLastName(),
            userDto.getEmail()
        );
        User savedUser = userRepository.saveAndFlush(user);

        // convert jpa user to user dto
        return new UserDto(
            savedUser.getId(),
            savedUser.getFirstName(),
            savedUser.getLastName(),
            savedUser.getEmail()
        );
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.get();
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
